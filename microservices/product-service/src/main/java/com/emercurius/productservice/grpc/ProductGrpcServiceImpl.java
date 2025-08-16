package com.emercurius.productservice.grpc;

import com.emercurius.commonlibs.dto.product.ProductRequestDTO;
import com.emercurius.productservice.mapper.ProductMapper;
import com.emercurius.productservice.services.ProductService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ProductGrpcServiceImpl extends ProductGrpcServiceGrpc.ProductGrpcServiceImplBase {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public void getProductById(ProductIdRequest request, StreamObserver<ProductGrpcResponseDTO> responseObserver) {
        try{
            var product = productService.getProductById(request.getId());
            responseObserver.onNext(productMapper.toGrpcDTO(product));
            responseObserver.onCompleted();
        }
        catch (Exception e){
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error getProductById: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getStockQuantity(ProductIdRequest request, StreamObserver<StockQuantityResponse> responseObserver) {
        try{
            var product = productService.getProductById(request.getId());
            StockQuantityResponse response = StockQuantityResponse.newBuilder()
                    .setStockQuantity(product.stockQuantity())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error get stock quantity: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void updateStockQuantity(StockQuantityUpdateRequest request, StreamObserver<StockQuantityResponse> responseObserver) {
        try {
            var product = productService.getProductById(request.getProductId());
            var updatedStock = product.stockQuantity() - request.getQuantity();

            if (updatedStock >= 0) {
                var updatedProduct = ProductRequestDTO.builder().stockQuantity(updatedStock).build();
                var productUpdated = productService.updateProduct(request.getProductId(), updatedProduct);
                StockQuantityResponse response = StockQuantityResponse.newBuilder()
                        .setStockQuantity(productUpdated.stockQuantity())
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription(String.format(
                                "Insufficient stock. Current stock: %d, requested quantity: %d",
                                product.stockQuantity(),
                                request.getQuantity()))
                        .asRuntimeException());
            }
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error updating stock quantity: " + e.getMessage())
                    .asRuntimeException());
        }
    }

}
