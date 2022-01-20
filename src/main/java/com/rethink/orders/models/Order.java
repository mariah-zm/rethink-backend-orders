package com.rethink.orders.models;

import com.rethink.orders.models.enums.OrderStatus;
import com.rethink.orders.models.enums.OrderStatusEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@TypeDef(name = "order_status", typeClass = OrderStatusEnumType.class)
@Table(name = "\"order\"")
public class Order {

    @Id
    private Long id;
    private Integer supplierId;
    private LocalDateTime orderDate;

    @Type(type = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Transient
    private List<OrderItem> items;

    public Order(Long id) {
        this.id = id;
    }
}