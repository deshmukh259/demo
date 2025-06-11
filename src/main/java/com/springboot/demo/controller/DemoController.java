package com.springboot.demo.controller;

import com.springboot.demo.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  @Transactional
    @PutMapping("/api/v1/order")
    public Orders update(@RequestBody Orders orders){

        System.out.println(orders);
       return orderService.update(orders);

    }

    @GetMapping("/api/v1/order")
    public List<Orders> list(){
        return orderService.fetchAll();
    }

}
