package com.example.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

//    @RequestMapping(method = RequestMethod.POST, value = "/reg")
//    public String reg(@RequestBody User user){
//
//        System.out.println("////////////////////////");
//        System.out.println("////////////////////////");
//        System.out.println(user.getLogin());
//        System.out.println(user.getPassword());
//        System.out.println("//////////////////////");
//        System.out.println("//////////////////////");
//
//        return "index";
//    }
}
