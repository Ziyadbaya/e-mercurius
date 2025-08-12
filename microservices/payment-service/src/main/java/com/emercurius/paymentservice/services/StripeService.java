package com.emercurius.paymentservice.services;

import com.emercurius.commonlibs.dtos.PaymentRequestDTO;
import com.emercurius.commonlibs.dtos.PaymentResponseDTO;
import com.emercurius.commonlibs.dtos.ProductResponseDTO;
import com.emercurius.commonlibs.dtos.StripeResponse;
import com.emercurius.commonlibs.enumerations.PaymentMethod;
import com.emercurius.commonlibs.enumerations.PaymentStatus;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StripeService {

    private final PaymentService paymentService;

    @Transactional
    public StripeResponse checkoutStripe(List<ProductResponseDTO> products) {
        try {
            // Validate the product list
            if (products == null || products.isEmpty()) {
                return new StripeResponse("ERROR", "Product list cannot be empty", null, null);
            }

            // Validate each product in the list
            for (ProductResponseDTO product : products) {
                if (product.price() == null) {
                    return new StripeResponse("ERROR", "Price cannot be null for product: " + product.name(), null, null);
                }
                if (product.name() == null || product.name().isBlank()) {
                    return new StripeResponse("ERROR", "Product name cannot be empty for product: " + product.name(), null, null);
                }
            }

            // Build line items for each product
            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
            for (ProductResponseDTO product : products) {
                // Build product data
                SessionCreateParams.LineItem.PriceData.ProductData.Builder productDataBuilder =
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(product.name());

                // Only set description if it is non-null and non-blank
                if (product.description() != null && !product.description().isBlank()) {
                    productDataBuilder.setDescription(product.description());
                }

                SessionCreateParams.LineItem.PriceData.ProductData productData = productDataBuilder.build();

                // Build price data (unit amount is in cents for Stripe)
                SessionCreateParams.LineItem.PriceData priceData =
                        SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("USD")
                                .setUnitAmountDecimal(
                                        product.price().multiply(BigDecimal.valueOf(100)) // Stripe expects cents
                                )
                                .setProductData(productData)
                                .build();

                // Build line item
                SessionCreateParams.LineItem lineItem =
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(5L) // Adjust quantity as needed
                                .setPriceData(priceData)
                                .build();

                lineItems.add(lineItem);
            }

            // Create a payment request (you may need to adjust this logic based on your requirements)
            PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(
                    products.stream()
                            .map(ProductResponseDTO::price)
                            .reduce(BigDecimal.ZERO, BigDecimal::add), // Sum of all product prices
                    PaymentMethod.STRIPE,
                    1L, // Adjust userId as needed
                    1L  // Adjust orderId as needed
            );

            PaymentResponseDTO paymentResponseDTO = paymentService.createPayment(paymentRequestDTO);

            // Create checkout session
            SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8084/api/stripe/success?session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl("http://localhost:8084/api/stripe/cancel?session_id={CHECKOUT_SESSION_ID}")
                    .setCustomerEmail("ziyadbaya2000@gmail.com")
                    .putMetadata("paymentId", String.valueOf(paymentResponseDTO.id()));

            // Add all line items to the session
            for (SessionCreateParams.LineItem lineItem : lineItems) {
                paramsBuilder.addLineItem(lineItem);
            }

            Session session = Session.create(paramsBuilder.build());

            return new StripeResponse("SUCCESS", "Payment session created", session.getId(), session.getUrl());

        } catch (StripeException e) {
            return new StripeResponse("ERROR", e.getMessage(), null, null);
        }
    }

    public void updatePaymentStatus(long paymentId, PaymentStatus paymentStatus) {
        paymentService.updatePaymentStatus(paymentId, paymentStatus);
    }

}
