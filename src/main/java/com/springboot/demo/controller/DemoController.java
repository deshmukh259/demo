package com.springboot.demo.controller;

import com.springboot.demo.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {



    @Autowired
    private OrderService orderService;

    @Transactional
    @PostMapping("/api/v1/order")
    public Orders create(@RequestBody Orders orders){

        System.out.println(orders);
       return orderService.save(orders);

    }

    @GetMapping("/api/v1/order")
    public List<Orders> list(){
        return orderService.fetchAll();
    }

}
