package com.emercurius.orderservice.grpc.client;

import com.emercurius.productservice.grpc.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductGrpcClient {

    @GrpcClient("product-service")
    private ProductGrpcServiceGrpc.ProductGrpcServiceBlockingStub blockingStub;

    public StockQuantityResponse updateQuantity(String productId, int quantity) {
        return blockingStub.updateStockQuantity(StockQuantityUpdateRequest.newBuilder()
                .setProductId(productId)
                .setQuantity(quantity)
                .build());
    }
}
