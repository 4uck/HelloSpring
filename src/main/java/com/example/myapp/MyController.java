package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
public class MyController {

    @Autowired
    private AccountRepository repository;

    @RequestMapping("/")
//    @ResponseBody
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
