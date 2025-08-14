package com.emercurius.paymentservice.controllers;

import com.emercurius.commonlibs.dto.payment.PaymentRequestDTO;
import com.emercurius.commonlibs.dto.payment.PaymentResponseDTO;
import com.emercurius.commonlibs.enums.PaymentStatus;
import com.emercurius.paymentservice.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(
            @Valid @RequestBody PaymentRequestDTO paymentRequestDto) {
        PaymentResponseDTO response = paymentService.createPayment(paymentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long id) {
        PaymentResponseDTO response = paymentService.getPaymentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> updatePayment(@PathVariable(name = "id") Long id,
            @RequestBody PaymentRequestDTO paymentRequestDto) {
        PaymentResponseDTO response = paymentService.updatePayment(id,paymentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<PaymentResponseDTO> updatePaymentStatus(@PathVariable(name = "id") Long id, @PathVariable(name = "status") String status) {
        PaymentResponseDTO responseDTO = paymentService.updatePaymentStatus(id, PaymentStatus.valueOf(status));
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

}
