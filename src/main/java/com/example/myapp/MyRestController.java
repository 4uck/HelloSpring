package com.example.myapp;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MyRestController {

    @Autowired
    private UsersRepository repository;

    @RequestMapping("/auth")
    public Token auth(@RequestHeader(value = "login", required = true) String login,
                      @RequestHeader(value = "password", required = true) String password){

        User user = repository.findByLogin(login);

        if (user.getPassword().equals(password))
            return new Token();

        return null;
    }

//    @RequestMapping("/checkUser")
//    public Token checkUser(@RequestHeader(value = "login", required = true) String login,
//                      @RequestHeader(value = "password", required = true) String password){
//
//        System.out.println("/////////////////////////");
//        System.out.println("/////////////////////////");
//        System.out.println("/////////////////////////");
//        System.out.println(login);
//        System.out.println(password);
//        System.out.println("/////////////////////////");
//        System.out.println("/////////////////////////");
//        System.out.println("/////////////////////////");
//
//        return new Token();
//    }

    @RequestMapping(value = "/checkUser", consumes = {"application/json;charset=UTF-8"})
    public ResponseEntity checkUser(@RequestBody User user){

        System.out.println("////////////////////////");
        System.out.println("////////////////////////");
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        System.out.println("//////////////////////");
        System.out.println("//////////////////////");

        if (repository.existsByLoginAndPassword(user.getLogin(), user.getPassword())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

//
//    @RequestMapping("/reg")
//    public Token reg(@RequestHeader(value = "login", required = true) String login,
//                     @RequestHeader(value = "password", required = true) String password){
//
//        repository.save(new User(login, password));
//
//        return new Token();
//    }

//    @RequestMapping(value = "/reg", consumes = {"application/json;charset=UTF-8"})
//    public @ResponseBody User reg(@RequestBody User user){
//
//        System.out.println("////////////////////////");
//        System.out.println("////////////////////////");
//        System.out.println(user.getLogin());
//        System.out.println(user.getPassword());
//        System.out.println("//////////////////////");
//        System.out.println("//////////////////////");
//
//        return user;
//    }
}
