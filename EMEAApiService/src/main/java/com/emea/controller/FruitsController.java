package com.emea.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/fruits")
public class FruitsController {

    @RequestMapping("/hello")
    public String home() {
      return "Hello " ;
    }
}
