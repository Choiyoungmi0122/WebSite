
package com.example.demo.share;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {


    @GetMapping("/")
    public String root() {
        return "Home";
    }
    @GetMapping("Home")
    public String home() {
    	return "Home";
    }
    @GetMapping("JoinMain")
    public String joinMainPage() {
    	return "JoinMain";
    }
    @GetMapping("LoginMain")
    public String loginMainPage() {
    	return "LoginMain";
    }
    @GetMapping("JoinComplete")
    public String joinCompletePage() {
    	return "JoinComplete";
    }
}
