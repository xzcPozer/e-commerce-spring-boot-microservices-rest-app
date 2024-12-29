package com.sharf.ecommerce.order;

import com.sharf.ecommerce.customer.CustomerClient;
import com.sharf.ecommerce.exception.BusinessException;
import com.sharf.ecommerce.kafka.OrderConfirmation;
import com.sharf.ecommerce.kafka.OrderProducer;
import com.sharf.ecommerce.orderline.OrderLineRequest;
import com.sharf.ecommerce.orderline.OrderLineService;
import com.sharf.ecommerce.payment.PaymentClient;
import com.sharf.ecommerce.payment.PaymentRequest;
import com.sharf.ecommerce.product.ProductClient;
import com.sharf.ecommerce.product.PurchaseRequest;
import com.sharf.ecommerce.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(String authToken, OrderRequest request) {
        // find client
        var customer = this.customerClient.findCustomerById(authToken, request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided ID"));

        // subtracts quantity from product table
        var purchasedProducts = this.productClient.purchaseProducts(request.products(), authToken);

        // get amount
        BigDecimal amount = purchasedProducts
                .stream()
                .map(resp -> resp.price()
                        .multiply(BigDecimal.valueOf(resp.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // create order for client
        var order = this.repository.save(mapper.toOrder(request, amount));

        // create order line for quantity of product
        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentRequest(
                amount,
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        // create payment
        paymentClient.requestOrderPayment(authToken, paymentRequest);

        // send message for kafka
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        amount,
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
    }
}
