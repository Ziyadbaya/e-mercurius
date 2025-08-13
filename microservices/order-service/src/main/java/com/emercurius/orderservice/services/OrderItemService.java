package com.emercurius.orderservice.services;

import com.emercurius.commonlibs.dto.order.OrderItemRequestDTO;
import com.emercurius.commonlibs.dto.order.OrderItemResponseDTO;
import com.emercurius.commonlibs.exceptions.EntityNotFoundException;
import com.emercurius.orderservice.entities.OrderItem;
import com.emercurius.orderservice.mapper.OrderItemMapper;
import com.emercurius.orderservice.repositories.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemResponseDTO findOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem not found with id " + id));
        return orderItemMapper.toDTO(orderItem);
    }


    public OrderItemResponseDTO createOrderItem(OrderItemRequestDTO orderItemRequestDTO) {
        OrderItem orderItem = orderItemMapper.toEntity(orderItemRequestDTO);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDTO(savedOrderItem);
    }

}
