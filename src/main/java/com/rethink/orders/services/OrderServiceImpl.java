package com.rethink.orders.services;

import com.rethink.orders.exceptions.OrderFailedException;
import com.rethink.orders.models.Order;
import com.rethink.orders.models.OrderItem;
import com.rethink.orders.models.enums.OrderStatus;
import com.rethink.orders.repositories.OrderItemRepository;
import com.rethink.orders.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final RequestService requestService;

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(RequestService requestService, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.requestService = requestService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Order createOrder(List<Integer> productIds) throws OrderFailedException {
        Long nextId = orderRepository.count();
        Order order = new Order(nextId);
        Long orderId = order.getId();
        order.setStatus(OrderStatus.received);
        orderRepository.save(order);

        try {
            requestService.sendOrderPostRequest(orderId, true);

            for (Integer id : productIds) {
                OrderItem item = new OrderItem(orderId, id, 30);
                requestService.sendOrderLineRequest(item);
                orderItemRepository.save(item);
            }

            requestService.sendOrderPostRequest(orderId, false);
            order.setOrderDate(LocalDateTime.now());
            order.setSupplierId(12345); // default supplier

            orderRepository.save(order);
            return order;
        } catch (IOException e) {
            throw new OrderFailedException("Order creation unsuccessful");
        }
    }

}