package com.hust.myblog;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/i")
    public String index() {
        return "/index.html";
    }

    @RequestMapping(value = "/welcome")
    public String home() {
        return "views/welcome";
    }
}
