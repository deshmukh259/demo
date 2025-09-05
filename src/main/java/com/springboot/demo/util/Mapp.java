package com.springboot.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapp {

    public static void main(String[] args) {

        List<UA> v = new ArrayList();
        v.add(new UA(1));
        v.add(new UA(2));

        System.out.println("v = " + v);
        List<Object> ds = v.stream().map(UA::getId).collect(Collectors.toList());

        System.out.println("ds = " + ds);
        System.out.println(v);




    }
}
