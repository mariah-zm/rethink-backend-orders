package com.rethink.orders.repositories;

import com.rethink.orders.models.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
}
