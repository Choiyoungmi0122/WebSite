package com.insight;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "Home";
	}
	
	@GetMapping("/introduce")
	public String Introduce() {
        return "user_info";
    }
	
	@GetMapping("/joinrule")
	public String Joinrule() {
        return "join_rule";
    }
}
