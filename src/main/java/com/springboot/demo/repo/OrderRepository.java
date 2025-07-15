package com.springboot.demo.repo;


import com.springboot.demo.controller.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Orders, Long> {


}
