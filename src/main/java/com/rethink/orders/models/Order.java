package com.rethink.orders.models;

import com.rethink.orders.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {

    @Id
    private Integer id;
    private Integer supplierId;
    private LocalDateTime orderDate;
    private LocalDate expectedArrival;
    private OrderStatus status;
    private double cost;

}