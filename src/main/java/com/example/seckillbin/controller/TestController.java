package com.example.seckillbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/demo")
public class TestController {

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","lb");
        return "hello";
    }
}

