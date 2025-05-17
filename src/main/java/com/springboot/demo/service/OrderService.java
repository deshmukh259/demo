package com.springboot.demo.service;

import com.springboot.demo.controller.Orders;
import com.springboot.demo.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }

    public List<Orders> fetchAll() {
        List<Orders> all = orderRepository.findAll();
        return all;
    }
}
