package com.emercurius.productservice.grpc;

import com.emercurius.commonlibs.dto.product.ProductRequestDTO;
import com.emercurius.productservice.mapper.ProductMapper;
import com.emercurius.productservice.services.ProductService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ProductGrpcServiceImpl extends ProductGrpcServiceGrpc.ProductGrpcServiceImplBase {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public void getProductById(ProductIdRequest request, StreamObserver<ProductResponseDTO> responseObserver) {
        var product = productService.getProductById(request.getId());
        responseObserver.onNext(productMapper.toGrpcDTO(product));
        responseObserver.onCompleted();
    }

    @Override
    public void getStockQuantity(ProductIdRequest request, StreamObserver<StockQuantityResponse> responseObserver) {
        var product =productService.getProductById(request.getId());
        StockQuantityResponse response = StockQuantityResponse.newBuilder()
                .setStockQuantity(product.stockQuantity())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateStockQuantity(StockQuantityUpdateRequest request, StreamObserver<StockQuantityResponse> responseObserver) {
        var updatedProduct = ProductRequestDTO.builder().stockQuantity(request.getNewQuantity()).build();
        var product = productService.updateProduct(request.getProductId(), updatedProduct);
        StockQuantityResponse response = StockQuantityResponse.newBuilder()
                .setStockQuantity(product.stockQuantity())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
