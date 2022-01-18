package com.rethink.orders.controllers;

import com.rethink.orders.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @GetMapping("/{supplierId}")
    public @ResponseBody Product getProduct(@PathVariable int productId) {
        // TODO Implement method
        return null;
    }

}