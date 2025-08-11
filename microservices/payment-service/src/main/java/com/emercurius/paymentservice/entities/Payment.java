package com.emercurius.paymentservice.entities;

import com.emercurius.commonlibs.entities.BaseEntity;
import com.emercurius.commonlibs.enumerations.PaymentMethod;
import com.emercurius.commonlibs.enumerations.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "payment")
public class Payment extends BaseEntity {

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Builder.Default()
    private PaymentStatus status = PaymentStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "user_id")
    private long userId;

}
