package com.example.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping("/")
//    @ResponseBody
    String index() {
        return "index";
    }

    @RequestMapping("/registry")
    String registry(){
        return "registry";
    }
}
