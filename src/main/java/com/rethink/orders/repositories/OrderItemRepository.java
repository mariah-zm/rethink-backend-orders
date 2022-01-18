package com.rethink.orders.repositories;

import com.rethink.orders.models.OrderItem;
import com.rethink.orders.models.OrderItemId;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemId> {
}
