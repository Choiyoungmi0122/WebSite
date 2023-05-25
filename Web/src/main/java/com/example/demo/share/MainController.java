package com.example.demo.share;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// @Controller 애너테이션을 적용하면 현재 클래스가 스프링부트의 컨트롤러가 됨
@Controller
public class MainController {
    @GetMapping("/")
    public String root() {
    	return "home";
    }
}