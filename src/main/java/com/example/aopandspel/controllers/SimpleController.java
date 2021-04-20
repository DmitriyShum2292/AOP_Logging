package com.example.aopandspel.controllers;

import com.example.aopandspel.aop.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class SimpleController {

    @GetMapping
    @Loggable
    public String getMain(Model model){
        return "main.html";
    }
}
