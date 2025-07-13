package com.springboot.demo.service;

import com.springboot.demo.controller.Orders;
import com.springboot.demo.repo.OrderRepository;
import jakarta.persistence.LockModeType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final CacheManager cacheManager;
    private final OrderRepository orderRepository;

    @CachePut(cacheNames = "orders",key = "#orders.id")
    public Orders save(Orders orders) {
        Orders save = orderRepository.save(orders);
        log.info("saved order "+orders);
        return orders;
    }
    @CacheEvict(cacheNames = "orders", key = "#orders.id")
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

    @Cacheable(cacheNames = "orders", key = "#id")
    public Orders getOrder(long id) {
        return orderRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public boolean clearCache(){
        Cache orders = cacheManager.getCache("orders");
        orders.clear();
        return true;
    }
}
