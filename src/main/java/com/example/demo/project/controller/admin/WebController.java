package com.example.demo.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/admin")
    public String home() {
        return "admin/index";
    }

}