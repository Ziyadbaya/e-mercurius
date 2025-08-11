package com.emercurius.paymentservice.services;

import com.emercurius.commonlibs.dtos.PaymentRequestDTO;
import com.emercurius.commonlibs.dtos.PaymentResponseDTO;
import com.emercurius.commonlibs.enumerations.PaymentStatus;
import com.emercurius.paymentservice.entities.Payment;
import com.emercurius.commonlibs.exceptions.EntityNotFoundException;
import com.emercurius.paymentservice.mapper.PaymentMapper;
import com.emercurius.paymentservice.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentResponseDTO getPaymentById(long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id " + id));
        return paymentMapper.toDTO(payment);
    }

    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDto) {
        Payment payment = paymentMapper.toEntity(paymentRequestDto);
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toDTO(savedPayment);
    }

    public PaymentResponseDTO updatePayment(long id, PaymentRequestDTO paymentRequestDto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id " + id));
        Payment updatedPayment = paymentMapper.partialUpdate(paymentRequestDto, payment);
        paymentRepository.save(updatedPayment);
        return paymentMapper.toDTO(updatedPayment);
    }

    public PaymentResponseDTO updatePaymentStatus(long id, PaymentStatus paymentStatus) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id " + id));
        payment.setStatus(paymentStatus);
        paymentRepository.save(payment);
        return paymentMapper.toDTO(payment);
    }

}
