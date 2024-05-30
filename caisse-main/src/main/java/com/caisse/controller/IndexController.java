package com.caisse.controller;

//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;



@RestController
@RequestMapping("/")
public class IndexController {
/*
    @GetMapping(path = "/")
    public HashMap index() {
        // get a successful user login
        OAuth2User user = ((OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new HashMap(){{
            put("hello ", user.getAttribute("name"));

            put("your email is ", user.getAttribute("email"));
        }};
    }


    @GetMapping(path = "/unauthenticated")
    public HashMap unauthenticatedRequests() {
        return new HashMap(){{
            put("this is", "unauthenticated endpoint");
        }};
    }
/*
@GetMapping(path ="/manager")
public String managerHello() {
    return "Hello, Manager!";
}

    @GetMapping("/cashier")
    public String cashierHello() {
        return "Hello, Cashier!";
    }

    @GetMapping("/unauthenticated")
    public String unauthenticated() {
        return "Unauthenticated endpoint";
    }

    @GetMapping("/other")
    public String other() {
        return "Other endpoint";
    }
    @GetMapping(path = "/")
    public HashMap index() {
        // get a successful user login
        OAuth2User user = ((OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new HashMap(){{
            put("hello ", user.getAttribute("name"));

            put("your email is ", user.getAttribute("email"));
        }};
    }*/
}
