package com.sharf.ecommerce.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    @NaturalId
    private String email;
    private String street;
    private String zipCode;
}
