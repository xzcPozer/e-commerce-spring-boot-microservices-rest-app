package com.sharf.ecommerce.customer;

public record CustomerResponse(
        Integer id,
        String firstname,
        String lastname,
        String email,
        String street,
        String zipCode
) {
}
