package com.example.demo.share;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo.share","com.example.demo.calender"})
public class SubController{

    public static void main(String[] args) {
        SpringApplication.run(SubController.class, args);
    }
}