package com.emercurius.paymentservice.mapper;

import com.emercurius.commonlibs.dtos.PaymentRequestDTO;
import com.emercurius.commonlibs.dtos.PaymentResponseDTO;
import com.emercurius.paymentservice.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public abstract class PaymentMapper {

    public abstract Payment toEntity(PaymentRequestDTO requestDTO);

    public abstract PaymentResponseDTO toDTO(Payment payment);
}
