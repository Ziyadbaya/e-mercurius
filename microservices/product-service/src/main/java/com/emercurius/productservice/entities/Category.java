package com.emercurius.productservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "categories")
public class Category {

    @Id
    private String id;
    private String name;
    private String description;

}
