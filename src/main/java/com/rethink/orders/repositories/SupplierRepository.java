package com.rethink.orders.repositories;

import com.rethink.orders.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Product, Integer> {
}
