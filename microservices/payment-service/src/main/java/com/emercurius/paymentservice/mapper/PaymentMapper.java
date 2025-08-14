package com.emercurius.paymentservice.mapper;

import com.emercurius.commonlibs.dto.payment.PaymentRequestDTO;
import com.emercurius.commonlibs.dto.payment.PaymentResponseDTO;
import com.emercurius.paymentservice.entities.Payment;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public abstract class PaymentMapper {

    @Mapping(target = "status", ignore = true)
    public abstract Payment toEntity(PaymentRequestDTO requestDTO);

    public abstract PaymentResponseDTO toDTO(Payment payment);

    @Mapping(target = "status", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Payment partialUpdate(PaymentRequestDTO requestDTO, @MappingTarget Payment payment);
}
