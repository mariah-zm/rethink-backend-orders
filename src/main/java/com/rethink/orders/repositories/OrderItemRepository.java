package com.rethink.orders.repositories;

import com.rethink.orders.models.OrderItem;
import com.rethink.orders.models.OrderItemId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemId> {

    List<OrderItem> findAllByIdOrderId(Long orderId);

}
