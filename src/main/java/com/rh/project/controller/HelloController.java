package com.rh.project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class HelloController {

    @GetMapping("/hola")
    public String saludar() {
        return "¡Hola desde Spring Boot!";
    }

}
