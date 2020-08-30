package com.lishuai.spring.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TestController {


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    public static void main(String[] args) {
        SpringApplication.run(TestController.class, args);
    }
}
