package com.example.myapp;

import com.example.myapp.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
public class MyRestController {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private MyCustomRepositoryImpl myCustomRepositoryImpl;

    @RequestMapping(value = "/addUser", consumes = {"application/json;charset=UTF-8"})
    public ResponseEntity addUser(@RequestBody Account account){

        System.out.println("////////////////////////");
        System.out.println("////////////////////////");
        System.out.println(account.getLogin());
        System.out.println(account.getPassword());
        System.out.println("//////////////////////");
        System.out.println("//////////////////////");

        if (repository.existsByLogin(account.getLogin())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

         account.setPassword(encoder.encode(account.getPassword()));

        repository.save(account);

        return new ResponseEntity(HttpStatus.OK);
    }

    enum  MyState{
        PAUSE, START
    }

    @RequestMapping(value = "/timestamps/STOP", method = RequestMethod.GET)
    public @ResponseBody TimeStampResponse getTime(
            @RequestHeader(value = "Authorization") String token){

        String login = TokenAuthenticationService.getLoginName(token);

        Account account = repository.findByLogin(login);

        Long timeMill = getTimeMill(account.getTimestamp());

        TimeStampResponse response = new TimeStampResponse(timeMill);

        myCustomRepositoryImpl.unsetMethod(login);

        return response;
    }

    @RequestMapping(value = "/timestamps/{state}", method = RequestMethod.GET)
    public ResponseEntity timestamps(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("state") MyState state){

        String login = TokenAuthenticationService.getLoginName(token);

        Date now = new Date();

        myCustomRepositoryImpl.pushMethod(
                login,
                new TimeStamp(now.getTime(), state.ordinal()));

        return new ResponseEntity(HttpStatus.OK);
    }

    private Long getTimeMill(List<TimeStamp> timeStamps){

//        for (TimeStamp e: timeStamps) {
//            System.out.println("state: " + e.getState() + " time: " + e.getTime());
//        }
//
//        System.out.println("cur time: " + new Date().getTime());
//
//        System.out.println();
//        System.out.println();
//        System.out.println();

        Long l = 0l;

        TimeStamp previousTimestamp = timeStamps.get(0);

        for (TimeStamp stamp: timeStamps) {

            int union = stamp.getState()^previousTimestamp.getState();

            if (union == 1){

//                Long curL = (stamp.getTime()-previousTimestamp.getTime())*previousTimestamp.getState();
//
//                System.out.println(
//                        "("+stamp.getTime()+" - "+previousTimestamp.getTime()+") * "+previousTimestamp.getState()
//                );

                l += (stamp.getTime()-previousTimestamp.getTime())*previousTimestamp.getState();
                previousTimestamp = stamp;
            }
        }

        if (previousTimestamp.getState() == 1)
            l += new Date().getTime()-previousTimestamp.getTime();

        return l;
    }

//    @RequestMapping(value = "/reg", consumes = {"application/json;charset=UTF-8"})
//    public @ResponseBody Account reg(@RequestBody Account account){
//
//        System.out.println("////////////////////////");
//        System.out.println("////////////////////////");
//        System.out.println(account.getLogin());
//        System.out.println(account.getPassword());
//        System.out.println("//////////////////////");
//        System.out.println("//////////////////////");
//
//        return account;
//    }


    @RequestMapping("/getName")
    public @ResponseBody String getName(
            @RequestHeader(value = "Authorization", required = true) String token
    ){
        return TokenAuthenticationService.getLoginName(token);
    }

    @RequestMapping("/users")
    public @ResponseBody String getUsers(
            @RequestHeader(value = "Authorization", required = true) String token
    ) {

        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }
}
