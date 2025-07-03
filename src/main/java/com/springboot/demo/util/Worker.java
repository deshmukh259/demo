package com.springboot.demo.util;

public interface Worker<T> {


    boolean doWork(T t, int j);

}
