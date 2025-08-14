package com.emercurius.productservice.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    @Builder.Default
    private String currency = "USD";
    private Integer stockQuantity;
    @DBRef
    private Category category;
    private List<Image> images;
    private List<String> tags;
    private boolean active;
    @Builder.Default
    private List<ProductReview> reviews = new ArrayList<>();
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Transient
    public Double getAverageRating() {
        return reviews.stream()
                .mapToDouble(ProductReview::getRating)
                .average()
                .orElse(0.0);
    }

    @Transient
    public Integer getReviewCounter() {
        return reviews.size();
    }

    public void addReview(ProductReview productReview) {
        reviews.add(productReview);
    }

}
