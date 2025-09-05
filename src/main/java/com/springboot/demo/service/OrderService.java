package com.springboot.demo.service;

import com.springboot.demo.entity.Orders;
import com.springboot.demo.repo.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final CacheManager cacheManager;
    private final OrderRepository orderRepository;
    private final QueueService queueService;

    @CachePut(cacheNames = "orders", key = "#orders.id")
    public Orders save(Orders orders) {
        Orders save = orderRepository.save(orders);
        log.info("saved order " + orders);
        return orders;
    }

    @CacheEvict(cacheNames = "orders", key = "#orders.id")
    public Orders update(Orders orders) {

        Orders byId = orderRepository.findById(orders.getId())
                .orElseThrow(() -> new RuntimeException("order not found"));
        ;

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
    public String getOrder(long id) {
        return getOrders(id).getItemName();
    }

    private Orders getOrders(long id) {
        System.out.println(" from db id = " + id);
        return orderRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public boolean clearCache() {
        Cache orders = cacheManager.getCache("orders");
        orders.clear();
        return true;
    }

    public String process(String startDate, String endDate, String[] instance) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate stDate = LocalDate.parse(startDate,format);
        LocalDate edDate = LocalDate.parse(endDate,format);
//wt about future dates ?

        if((stDate.equals(endDate) || stDate.isBefore(edDate)) && instance.length > 0){
            boolean b  = true;

            for(int i = 0; i < instance.length; i++) {
                LocalDate stInternalDate = LocalDate.parse(startDate,format);
                while (stInternalDate.equals(edDate) || stInternalDate.isBefore(edDate)) {
                     String entry = String.format("%s|%s",stInternalDate.format(format),instance[i]);
                    queueService.add(entry);
                    stInternalDate = stInternalDate.plusDays(1);
                }
            }

        return "trriggred";
        }else {
            return "Wring dates";
        }

    }
}
