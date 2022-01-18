package com.rethink.orders.services;

import com.rethink.orders.models.OrderItem;

import java.io.IOException;

public interface RequestService {

    void sendOrderPostRequest(Long orderId, boolean isCreate) throws IOException;

    void sendOrderLineRequest(OrderItem orderItem) throws IOException;

}
