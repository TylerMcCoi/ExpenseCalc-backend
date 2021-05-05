package com.example.demo.login;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/login")
@AllArgsConstructor

public class LoginController {
    @GetMapping
    public String login() {
        return "login";
    }
}
