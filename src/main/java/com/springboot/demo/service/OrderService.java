package com.springboot.demo.service;

import com.springboot.demo.controller.Orders;
import com.springboot.demo.repo.OrderRepository;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }
    public Orders update(Orders orders) {

        Orders byId = orderRepository.findById(orders.getId())
                .orElseThrow(()->new RuntimeException("order not found"));;

        byId.setConfig(orders.getConfig());
        byId.setItemName(orders.getItemName());



        return byId;
      //  return orderRepository.save(byId);
    }

    public List<Orders> fetchAll() {
        List<Orders> all = orderRepository.findAll();
        return all;
    }
}
