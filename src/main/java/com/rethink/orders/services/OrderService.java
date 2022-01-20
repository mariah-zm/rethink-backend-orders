package com.rethink.orders.services;

import com.rethink.orders.exceptions.OrderFailedException;
import com.rethink.orders.exceptions.OrderNotFoundException;
import com.rethink.orders.models.Order;
import com.rethink.orders.models.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderStatus getOrderStatus(Long orderId) throws OrderNotFoundException;

    void updateOrderStatus(Long orderId, OrderStatus status) throws OrderNotFoundException;

    Order createOrder(List<Integer> productIds) throws OrderFailedException;

    Order getOrder(Long orderId) throws OrderNotFoundException;
}