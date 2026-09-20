package com.example.demo.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/admin")
    public String home() {
        return "index";
    }

    @GetMapping("/authors-ui")
    public String authors() {
        return "authors";
    }

    @GetMapping("/members-ui")
    public String members() {
        return "members";
    }
}