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

        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        password = encoder.encode(password);

        if (repository.existsByLoginAndPassword(email, password)) {

        }



        Account account = new Account(email, password);

        System.out.println("////////////////////////");
        System.out.println("////////////////////////");
        System.out.println(email);
        System.out.println(password);
        System.out.println("//////////////////////");
        System.out.println("//////////////////////");

        repository.save(account);

        return index();
    }

    @RequestMapping(value = "/authrequest", consumes = {"multipart/form-data"})
    public @ResponseBody
    Account authRequest(
            @RequestPart("email") @Valid @NotNull String email,
            @RequestPart("password") @Valid @NotNull String password
    ) {


        if (repository.existsByLoginAndPassword(email, password))
            return new Account(email, password);

        return new Account("not", "found");
    }
}
