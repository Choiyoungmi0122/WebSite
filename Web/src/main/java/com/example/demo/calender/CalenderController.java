package com.example.demo.calender;

import jakarta.validation.Valid;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.table.UserInfo;

import lombok.RequiredArgsConstructor;

@RequestMapping("/Calender")
@RequiredArgsConstructor
@Controller

public class CalenderController {
	//@RequiredArgsConstructor 사용으로 생성자 주입
	private final CalenderRepository calenderRepository;
	private final CalenderService calederService;
	//private final UserInfoRepository userInfoRepository;
	
	 @GetMapping("/create")
	    public String questionCreate(CalenderForm calenderForm) {
	        return "html변경";
	    }
	
	@PostMapping("/create")
	public String CreateCalender(@Valid CalenderForm calenderForm, BindingResult bindingResult, 
			@RequestParam UserInfo userInfo, @RequestParam("Text") String Text, 
			@RequestParam("Register") LocalDateTime Register, @RequestParam("Deadline") LocalDateTime Deadline, 
			@RequestParam("Category") String Category,@RequestParam("Replay") String Repaly) {
		if (bindingResult.hasErrors()) {//Calender 등록창에서 제목입력 안하면 작동
            return "html변경";
        }
		this.calederService.AddCalenderData(userInfo, Register, Text, Deadline, Category, Repaly);
		return ("redirect:/Calender"); //달력 출력 화면으로 전환
	}

}
