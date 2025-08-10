package com.emercurius.authservice.entities;

import com.emercurius.commonlibs.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Address extends BaseEntity {

    private String street;
    private String city;
    private String country;
    private String postalCode;
    @Column(name = "user_id")
    private long userId;

}
