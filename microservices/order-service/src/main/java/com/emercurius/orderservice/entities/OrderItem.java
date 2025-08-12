package com.emercurius.orderservice.entities;

import com.emercurius.commonlibs.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItem extends BaseEntity {

    private Long amount;

    private Long quantity;

    private String productName;

    @Column(name = "order_id")
    private Long orderId;

    private Long userId;

    private String productId;

}
