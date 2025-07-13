package com.springboot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo2Conttroller {


    @GetMapping("/hello")
    public String hello(){
        return "Helllo !!";
    }
}
