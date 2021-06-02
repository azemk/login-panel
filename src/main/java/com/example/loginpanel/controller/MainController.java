package com.example.loginpanel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/firstMethod")
    public String firstMethod(){
        return "FIRST METHOD";
    }

    @GetMapping("/secondMethod")
    public String secondMethod(){
        return "SECOND METHOD";
    }

    @GetMapping("/thirdMethod")
    public String thirdMethod(){
        return "THIRD METHOD";
    }

    @GetMapping("/fourthMethod")
    public String fourthMethod(){
        return "FOURTH METHOD ";
    }
}
