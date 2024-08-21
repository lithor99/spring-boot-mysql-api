package com.ishop.li.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ishop.li.model.Order;
import com.ishop.li.repository.OrderRepository;

import io.micrometer.common.lang.NonNull;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @SuppressWarnings("null")
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable @NonNull Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    @PostMapping()
    public Order createUser(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
