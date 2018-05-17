package com.example.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @RequestMapping(value = "/reg", consumes = {"multipart/form-data"})
    public @ResponseBody User reg(
            @RequestPart("email") @Valid String email,
            @RequestPart("password") @Valid @NotNull String password
    ){

        User user = new User(email, password);

        System.out.println("////////////////////////");
        System.out.println("////////////////////////");
        System.out.println(email);
        System.out.println(password);
        System.out.println("//////////////////////");
        System.out.println("//////////////////////");

        return user;
    }
}
