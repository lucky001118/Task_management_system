package com.lucky.controller;

import com.lucky.model.Task;
import com.lucky.model.TaskStatus;
import com.lucky.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/tasks")
    public ResponseEntity<String> getAllTasks() {
        return new ResponseEntity<>("welcome to task controller", HttpStatus.OK);
    }

}
