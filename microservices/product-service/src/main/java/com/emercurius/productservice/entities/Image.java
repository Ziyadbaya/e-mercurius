package com.emercurius.productservice.entities;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    private String url;
    private String altText;
    private boolean mainImage;

}
