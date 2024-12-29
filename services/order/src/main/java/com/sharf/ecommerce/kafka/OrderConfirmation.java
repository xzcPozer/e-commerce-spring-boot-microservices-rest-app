package com.sharf.ecommerce.kafka;

import com.sharf.ecommerce.customer.CustomerResponse;
import com.sharf.ecommerce.order.PaymentMethod;
import com.sharf.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
