package com.emercurius.paymentservice.controllers;

import com.emercurius.commonlibs.dtos.ProductResponseDTO;
import com.emercurius.commonlibs.dtos.StripeResponse;
import com.emercurius.commonlibs.enumerations.PaymentStatus;
import com.emercurius.paymentservice.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/stripe")
@RequiredArgsConstructor
public class StripeController {

    private final StripeService stripeService;
    @Value("${frontend.success.url:http://localhost:4200/payment-success}")
    private String frontendSuccessUrl;

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkout(@RequestBody List<ProductResponseDTO> productResponseDTO) throws StripeException {
        StripeResponse stripeResponse = stripeService.checkoutStripe(productResponseDTO);
        return ResponseEntity.ok(stripeResponse);
    }

    @GetMapping("/success")
    public RedirectView paymentSuccess(@RequestParam("session_id") String sessionId) throws StripeException {
        Session session = Session.retrieve(sessionId);

        if ("paid".equals(session.getPaymentStatus())) {
            String paymentId = session.getMetadata().get("paymentId");
            if (paymentId != null) {
                stripeService.updatePaymentStatus(Long.parseLong(paymentId), PaymentStatus.APPROVED);
            }
            String redirectUrl = frontendSuccessUrl + "?session_id=" + sessionId + "&payment_id=" + paymentId;

            return new RedirectView(redirectUrl);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error verifying payment");
        }
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> paymentCancel(@RequestParam("session_id") String sessionId) throws StripeException {
        Session session = Session.retrieve(sessionId);

        String paymentId = session.getMetadata().get("paymentId");
        if (paymentId != null) {
            stripeService.updatePaymentStatus(Long.parseLong(paymentId), PaymentStatus.FAILED);
        }

        return ResponseEntity.ok("Payment cancelled");
    }

}
