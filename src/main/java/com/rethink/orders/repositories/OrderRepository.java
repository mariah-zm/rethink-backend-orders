package com.rethink.orders.repositories;

import com.rethink.orders.models.StockItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<StockItem, Integer> {

}
