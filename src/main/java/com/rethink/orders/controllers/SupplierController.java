package com.rethink.orders.controllers;

import com.rethink.orders.models.Delivery;
import com.rethink.orders.models.StockItem;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/stock")
@RestController
public class StockController {

    @GetMapping("/{productId}")
    public @ResponseBody StockItem getStockItem(@PathVariable int productId) {
        // TODO Implement method
        return null;
    }

    @PostMapping("/delivery")
    public @ResponseBody String receiveDelivery(@RequestBody Delivery delivery) {
        // TODO Implement method
        return "Stock updated with delivery items";
    }

}