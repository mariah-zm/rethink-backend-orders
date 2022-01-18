package com.rethink.orders.services;

import com.rethink.orders.exceptions.OrderFailedException;
import com.rethink.orders.models.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(List<Integer> productIds) throws OrderFailedException;

}