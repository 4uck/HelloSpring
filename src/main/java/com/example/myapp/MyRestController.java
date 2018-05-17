package com.example.myapp;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
public class MyRestController {


//    @RequestMapping("/reg")
//    public Token reg(@RequestHeader(value = "login", required = true) String login,
//                     @RequestHeader(value = "password", required = true) String password){
//
//        repository.save(new User(login, password));
//
//        return new Token();
//    }

//    @RequestMapping(value = "/reg", consumes = {"multipart/form-data"})
//    public @ResponseBody User reg(
//            @RequestPart("login") @Valid String login,
//            @RequestPart("password") @Valid @NotNull String password
//    ){
//
//        User user = new User(login, password);
//
//        System.out.println("////////////////////////");
//        System.out.println("////////////////////////");
//        System.out.println(login);
//        System.out.println(password);
//        System.out.println("//////////////////////");
//        System.out.println("//////////////////////");
//
//        return user;
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/reg1")
    public Token reg1(@RequestBody JSONPObject obj){

        System.out.println("////////////////////////");
        System.out.println("////////////////////////");
        System.out.println(obj.toString());
        System.out.println("//////////////////////");
        System.out.println("//////////////////////");

        return new Token();
    }
}
