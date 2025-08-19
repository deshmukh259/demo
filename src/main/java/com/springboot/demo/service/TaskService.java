package com.springboot.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TaskService {


    public String backFill(String date,String instance){
        String format = String.format("date = %s | instance = %s done", date, instance);
        System.out.println(format);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }
        return format;
    }
}
