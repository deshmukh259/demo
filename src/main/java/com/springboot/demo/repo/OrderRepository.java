package com.springboot.demo.repo;


import com.springboot.demo.controller.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {



}
