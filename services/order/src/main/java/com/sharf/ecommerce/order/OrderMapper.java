package com.sharf.ecommerce.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request, BigDecimal totalAmount){
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(totalAmount)
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
