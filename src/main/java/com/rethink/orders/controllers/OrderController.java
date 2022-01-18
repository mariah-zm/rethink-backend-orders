package com.rethink.orders.controllers;

import com.rethink.orders.exceptions.OrderFailedException;
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

    @GetMapping("/{orderId}/status")
    public @ResponseBody
    OrderStatus getStatus(@PathVariable Integer orderId) {
        return OrderStatus.received;
    }

    @PostMapping("/{orderId}/status")
    public @ResponseBody String updateStatus(@PathVariable Integer orderId, @RequestBody OrderStatus orderStatus) {
        return "Status updated";
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