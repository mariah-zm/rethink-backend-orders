package com.rethink.orders.repositories;

import com.rethink.orders.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<Product, Integer> {
}
