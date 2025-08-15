package com.emercurius.productservice.grpc;

import com.emercurius.commonlibs.exceptions.EntityNotFoundException;
import com.emercurius.productservice.entities.Product;
import com.emercurius.productservice.mapper.ProductMapper;
import com.emercurius.productservice.mapper.ProductReviewMapper;
import com.emercurius.productservice.repositories.ProductRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ProductGrpcServiceImpl extends ProductGrpcServiceGrpc.ProductGrpcServiceImplBase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductReviewMapper productReviewMapper;

    @Override
    public void getProductById(ProductIdRequest request, StreamObserver<ProductResponseDTO> responseObserver) {
        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        responseObserver.onNext(productMapper.toGrpcDTO(product));
        responseObserver.onCompleted();
    }

    @Override
    public void getStockQuantity(ProductIdRequest request, StreamObserver<StockQuantityResponse> responseObserver) {
        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        StockQuantityResponse response = StockQuantityResponse.newBuilder()
                .setStockQuantity(product.getStockQuantity())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateStockQuantity(StockQuantityUpdateRequest request, StreamObserver<StockQuantityResponse> responseObserver) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.setStockQuantity(request.getNewQuantity());
        productRepository.save(product);
        StockQuantityResponse response = StockQuantityResponse.newBuilder()
                .setStockQuantity(product.getStockQuantity())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
