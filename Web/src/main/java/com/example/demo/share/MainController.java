package com.example.demo.share;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/share")
    @ResponseBody
    public String index() {
        return "index";
    }
    @GetMapping("/")
    public String root() {
        return "home";
    }
    @GetMapping("/JoinMain")
    public String joinMainPage() {
    	return "JoinMain";
    }
    @GetMapping("LoginMain")
    public String loginMainPage() {
    	return "LoginMain";
    }
}
