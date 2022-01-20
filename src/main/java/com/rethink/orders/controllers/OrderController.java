package com.rethink.orders.controllers;

import com.rethink.orders.exceptions.OrderFailedException;
import com.rethink.orders.exceptions.OrderNotFoundException;
import com.rethink.orders.models.Order;
import com.rethink.orders.models.enums.OrderStatus;
import com.rethink.orders.services.InventoryService;
import com.rethink.orders.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    private final InventoryService inventoryService;

    public OrderController(OrderService orderService, InventoryService inventoryService) {
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{orderId}")
    public @ResponseBody
    Order getOrder(@PathVariable Long orderId) {
        try {
            return orderService.getOrder(orderId);
        } catch (OrderNotFoundException e) {
            return null;
        }
    }

    @GetMapping("/{orderId}/status")
    public @ResponseBody
    OrderStatus getStatus(@PathVariable Long orderId) {
        try {
            return orderService.getOrderStatus(orderId);
        } catch (OrderNotFoundException e) {
            return OrderStatus.cancelled;
        }
    }

    @PostMapping("/{orderId}/status")
    public @ResponseBody String updateStatus(@PathVariable Long orderId, @RequestParam OrderStatus orderStatus) {
        try {
            orderService.updateOrderStatus(orderId, orderStatus);
            return "Status updated";
        } catch (OrderNotFoundException e) {
            return "Could not update status. " + e.getMessage();
        }
    }

    @PostMapping("")
    public String createOrder() {
        try {
            List<Integer> productsToOrder = inventoryService.getLowStock();
            if (!productsToOrder.isEmpty()) {
                Order order = orderService.createOrder(productsToOrder);
                return "Created order with id " + order.getId();
            } else {
                return "No products to order";
            }
        } catch (IOException e) {
            return "Failed to fetch low stock from inventory service";
        } catch (OrderFailedException e) {
            return e.getMessage();
        }
    }

}