package com.emercurius.orderservice.grpc.client;

import com.emercurius.productservice.grpc.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public StockQuantityUpdateListResponse updateListStockQuantity(List<Pair<String, Integer>> productQuantities) {
        var stockQuantityUpdateListRequestBuilder = StockQuantityUpdateListRequest.newBuilder();
        productQuantities.forEach(productQuantity ->
                stockQuantityUpdateListRequestBuilder.addQuantityRequestList(StockQuantityUpdateRequest.newBuilder()
                        .setProductId(productQuantity.a)
                        .setQuantity(productQuantity.b)
                        .build()));
        return blockingStub.updateListStockQuantity(stockQuantityUpdateListRequestBuilder.build());
    }

}
