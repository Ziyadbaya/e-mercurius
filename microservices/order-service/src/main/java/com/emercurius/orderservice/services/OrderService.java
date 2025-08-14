package com.emercurius.orderservice.services;

import com.emercurius.commonlibs.dto.order.OrderRequestDTO;
import com.emercurius.commonlibs.dto.order.OrderResponseDTO;
import com.emercurius.commonlibs.exceptions.EntityNotFoundException;
import com.emercurius.orderservice.entities.Order;
import com.emercurius.orderservice.entities.OrderItem;
import com.emercurius.orderservice.mapper.OrderItemMapper;
import com.emercurius.orderservice.mapper.OrderMapper;
import com.emercurius.orderservice.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = orderMapper.toEntity(orderRequestDTO);

        orderRequestDTO.items().forEach(itemDTO -> {
            OrderItem item = orderItemMapper.toEntity(itemDTO);
            order.addItem(item);
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
