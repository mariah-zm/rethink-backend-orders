package com.rethink.orders.controllers;

import com.rethink.orders.models.enums.OrderStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderController {

    @GetMapping("/{orderId}/status")
    public @ResponseBody
    OrderStatus getStatus(@PathVariable Integer orderId) {
        return OrderStatus.RECEIVED;
    }

    @PostMapping("/{orderId}/status")
    public @ResponseBody String updateStatus(@PathVariable Integer orderId, @RequestBody OrderStatus orderStatus) {
        return "Status updated";
    }

}