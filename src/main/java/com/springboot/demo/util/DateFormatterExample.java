package com.springboot.demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatterExample {
    public static void main(String[] args) {
        String inputDate = "2025-11-11";

        // Parse input string to LocalDate
        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-M-d"));
       LocalDate endDate = LocalDate.parse("2025-11-12", DateTimeFormatter.ofPattern("yyyy-M-d"));

       if(date.isBefore(endDate) || date.isEqual(endDate)){
           System.out.println("yes endDate = " + endDate);
       }else {
           System.out.println("no date = " + date);
       }
        // Format it to yyyy-MM-dd
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println(formattedDate);  // Output: 2025-08-05
    }
}
