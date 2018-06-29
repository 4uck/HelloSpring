package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public final class MyController {

    @Autowired
    private AccountRepository repository;

    @RequestMapping("/")
    String index() {
        return "views/index";
    }

    @RequestMapping("/registry")
    String registry() {
        return "views/registry";
    }

    @RequestMapping("/home")
    String home() {
        return "views/home";
    }

}
