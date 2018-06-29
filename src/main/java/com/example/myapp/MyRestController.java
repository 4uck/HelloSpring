package com.example.myapp;

import com.example.myapp.security.TokenAuthenticationService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
public final class MyRestController {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountCustomRepositoryImpl myCustomRepositoryImpl;

    @RequestMapping(value = "/addUser", consumes = {"application/json;charset=UTF-8"})
    public ResponseEntity addUser(
            final @RequestBody Account account
    ) {

        System.out.println("////////////////////////");
        System.out.println("////////////////////////");
        System.out.println(account.getLogin());
        System.out.println(account.getPassword());
        System.out.println("//////////////////////");
        System.out.println("//////////////////////");

        if (repository.existsByLogin(account.getLogin())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        account.setPassword(encoder.encode(account.getPassword()));
        repository.save(account);

        return new ResponseEntity(HttpStatus.OK);
    }

    enum  MyState {
        PAUSE, START
    }

    /**
     * this method return total time for user, and clear all timestamps for user.
     * @param token jwt toker
     * @return json object(general time in hours, minutes, secundes. And general time in milliseconds)
     */
    @RequestMapping(value = "/timestamps/STOP", method = RequestMethod.GET)
    public @ResponseBody TimeStampResponse getTime(
            final @RequestHeader(value = "Authorization") String token
    ) {

        String login = TokenAuthenticationService.getLoginName(token);
        Account account = repository.findByLogin(login);

        Long timeMill = getTimeMill(account.getTimestamp());
        myCustomRepositoryImpl.unsetMethod(login);

        TimeStampResponse response = new TimeStampResponse(timeMill);

        return response;
    }

    /**
     * this method add timestamp for user.
     * @param token jwt token
     * @param state current state(start, pause)
     * @return status code
     */
    @RequestMapping(value = "/timestamps/{state}", method = RequestMethod.GET)
    public ResponseEntity timestamps(
            final @RequestHeader(value = "Authorization") String token,
            final @PathVariable("state") MyState state
    ) {
        String login = TokenAuthenticationService.getLoginName(token);
        Date now = new Date();

        myCustomRepositoryImpl.pushMethod(
                login,
                new TimeStamp(now.getTime(), state.ordinal()));

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * this methods calculates total time with all starts and pauses.
     * @param timeStamps list timestamps
     * @return total time per milliseconds
     */
    private Long getTimeMill(final List<TimeStamp> timeStamps) {

        Long l = 0L;

        TimeStamp previousTimestamp = timeStamps.get(0);

        for (TimeStamp stamp: timeStamps) {

            int union = stamp.getState() ^ previousTimestamp.getState();

            if (union == 1) {
                l += (stamp.getTime() - previousTimestamp.getTime()) * previousTimestamp.getState();
                previousTimestamp = stamp;
            }
        }

        if (previousTimestamp.getState() == 1)
            l += new Date().getTime() - previousTimestamp.getTime();

        return l;
    }

    @RequestMapping("/getName")
    public @ResponseBody String getName(
            final @RequestHeader(value = "Authorization", required = true) String token
    ) {
        return TokenAuthenticationService.getLoginName(token);
    }
}
