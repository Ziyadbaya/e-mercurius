package com.emercurius.orderservice.services;

import com.emercurius.commonlibs.dto.order.OrderRequestDTO;
import com.emercurius.commonlibs.dto.order.OrderResponseDTO;
import com.emercurius.commonlibs.exceptions.EntityNotFoundException;
import com.emercurius.orderservice.entities.Order;
import com.emercurius.orderservice.entities.OrderItem;
import com.emercurius.orderservice.grpc.client.ProductGrpcClient;
import com.emercurius.orderservice.mapper.OrderItemMapper;
import com.emercurius.orderservice.mapper.OrderMapper;
import com.emercurius.orderservice.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ProductGrpcClient productGrpcClient;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = orderMapper.toEntity(orderRequestDTO);
        List<Pair<String, Integer>> productQuantities = new ArrayList<>();
        orderRequestDTO.items().forEach(itemDTO -> {
            OrderItem item = orderItemMapper.toEntity(itemDTO);
            productQuantities.add(new Pair<>(item.getProductId(), item.getQuantity()));
            order.addItem(item);
        });
        productGrpcClient.checkListStockQuantity(productQuantities).getStockAvailabilityList().forEach(stockAvailability -> {
            if(!stockAvailability.getIsAvailable()) {
                throw new EntityNotFoundException("Insufficient stock for product id " + stockAvailability.getProductId());
            }
        });
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
        return orderMapper.toDTO(order);
    }

}
