package com.springboot.demo.controller;

import com.springboot.demo.entity.Orders;
import com.springboot.demo.service.OrderService;
import com.springboot.demo.service.QueueService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
@RequestMapping("/api/v1")
public class DemoController {


    @Autowired
    private OrderService orderService;


    @Transactional
    @PostMapping("/order")
    public Orders create(@RequestBody Orders orders) {

        System.out.println(orders);
        return orderService.save(orders);

    }

    @Transactional
    @PutMapping("/order")
    public Orders update(@RequestBody Orders orders) {

        System.out.println(orders);
        return orderService.update(orders);

    }

    @GetMapping("/orders")
    public List<Orders> list() {
        return orderService.fetchAll();
    }

    @GetMapping("/orders/{id}")
    public Orders order(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/clear-cache")
    public boolean clearCache() {
        return orderService.clearCache();
    }

    @GetMapping("/date")
    public String process(@RequestParam String startDate, @RequestParam String endDate, @RequestParam  String []instance){

        System.out.println("startDate = " + startDate);
        System.out.println("endDate = " + endDate);
        System.out.println("instance = " + instance);
        return orderService.process(startDate,endDate,instance);
    }
    @Autowired
    private  QueueService queueService;
    @GetMapping("/queue")
    public LinkedBlockingQueue getQ(){
        return queueService.getQ();
    }
}