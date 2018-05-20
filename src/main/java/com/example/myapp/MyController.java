package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
public class MyController {

    @Autowired
    private UsersRepository repository;

    @RequestMapping("/")
//    @ResponseBody
    String index() {
        return "index";
    }

    @RequestMapping("/registry")
    String registry() {
        return "registry";
    }

    @RequestMapping(value = "/registryrequest", consumes = {"multipart/form-data"})
    public String registryRequest(
            @RequestPart("email") @Valid @NotNull String email,
            @RequestPart("password") @Valid @NotNull String password,
            @RequestPart("confPass") @Valid @NotNull String confPass
    ) {

        if (repository.existsByLoginAndPassword(email, password)) {

        }

        User user = new User(email, password);

        System.out.println("////////////////////////");
        System.out.println("////////////////////////");
        System.out.println(email);
        System.out.println(password);
        System.out.println("//////////////////////");
        System.out.println("//////////////////////");

        repository.save(user);

        return index();
    }

    @RequestMapping(value = "/authrequest", consumes = {"multipart/form-data"})
    public @ResponseBody
    User authRequest(
            @RequestPart("email") @Valid @NotNull String email,
            @RequestPart("password") @Valid @NotNull String password
    ) {


        if (repository.existsByLoginAndPassword(email, password))
            return new User(email, password);

        return new User("not", "found");
    }
}
