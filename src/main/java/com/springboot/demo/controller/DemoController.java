package com.springboot.demo.controller;

import com.springboot.demo.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DemoController {



    @Autowired
    private OrderService orderService;

    @Transactional
    @PostMapping("order")
    public Orders create(@RequestBody Orders orders){

        System.out.println(orders);
       return orderService.save(orders);

    }

  @Transactional
    @PutMapping("order")
    public Orders update(@RequestBody Orders orders){

        System.out.println(orders);
       return orderService.update(orders);

    }

    @GetMapping("orders")
    public List<Orders> list(){
        return orderService.fetchAll();
    }

    @GetMapping("orders/{id}")
    public Orders order(@PathVariable int id){
        return orderService.getOrder(id);
    }

    @GetMapping("clear-cache")
    public boolean clearCache(){
        return orderService.clearCache();
    }
}
