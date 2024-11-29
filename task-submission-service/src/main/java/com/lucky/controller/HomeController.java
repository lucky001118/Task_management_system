package com.lucky.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/submission")
    public ResponseEntity<String> homecontroller(){
        return new ResponseEntity<>("Welcome the the submission service of the task managemet service", HttpStatus.OK);
    }
}
