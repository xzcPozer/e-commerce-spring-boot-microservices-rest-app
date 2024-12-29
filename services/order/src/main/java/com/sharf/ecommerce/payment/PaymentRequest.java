package com.sharf.ecommerce.payment;

import com.sharf.ecommerce.customer.CustomerResponse;
import com.sharf.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
