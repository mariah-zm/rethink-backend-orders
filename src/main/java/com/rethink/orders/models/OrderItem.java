package com.rethink.orders.models;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {

    @EmbeddedId
    private OrderItemId id;
    private int quantity;

    public OrderItem(Long productId, Integer orderId, int quantity) {
        this.id = new OrderItemId(productId, orderId);
        this.quantity = quantity;
    }

}