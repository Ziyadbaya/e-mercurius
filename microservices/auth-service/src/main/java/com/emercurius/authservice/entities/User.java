package com.emercurius.authservice.entities;

import com.emercurius.commonlibs.entities.BaseEntity;
import com.emercurius.commonlibs.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import javax.management.DescriptorKey;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "users")
public class User extends BaseEntity {

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<Address> address;

    @OneToOne
    @JoinColumn(name = "activeAddress", referencedColumnName = "id", insertable = false, updatable = false)
    private Address activeAddress;

}
