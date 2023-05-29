package com.example.demo.share;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.notice.NoticeService;
import com.example.demo.table.Notice;

@Controller
public class MainController {  
	
	@GetMapping("/")
	public String root() {
		return "Home";
	}
}
