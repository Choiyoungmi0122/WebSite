package com.example.demo.calender;

import jakarta.validation.Valid;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.table.Calender;
import com.example.demo.table.UserInfo;

import lombok.RequiredArgsConstructor;

@RequestMapping("/calender")
@RequiredArgsConstructor
@Controller

public class CalenderController {
	//@RequiredArgsConstructor 사용으로 생성자 주입
	private final CalenderRepository calenderRepository;
	private final CalenderService calenderService;
	//private final UserInfoRepository userInfoRepository;
	
	//http://8080/calender/create
	//일정 등록 페이지에서 입력하고 등록 버튼 누르면 작동하는 함수
	//*역할조건 안넣음 @PreAuthorize
	@PostMapping("/create")
	public String CreateCalender(//입력 값들
			@Valid CalenderForm calenderForm, BindingResult bindingResult, 
			@RequestParam UserInfo userInfo, @RequestParam("Calender_Text") String Text, 
			@RequestParam("Calender_Register") LocalDateTime Register, 
			@RequestParam("Calender_Deadline") LocalDateTime Deadline, 
			@RequestParam("Calender_Category") String Category,@RequestParam("Calender_Replay") String Repaly) {
		if (bindingResult.hasErrors()) {//Calender 등록창에서 제목입력 안하면 작동
            return "캘린더등록html";
        }
		//일정 등록화면에서 문제없다면 calederService에서 저장
		this.calenderService.AddCalenderData(userInfo, Register, Text, Deadline, Category, Repaly);
		return ("redirect:/CalenderMain"); //달력 출력 화면으로 전환
	}
	
	//http://8080/calender/modify
	//일정 수정 페이지에서 입력하고 수정버튼 누르면 작동하는 함수
	//*역할조건 안넣음 @PreAuthorize
	@GetMapping("/modify/{id}")
	public String ModifyCalender(CalenderForm calenderForm, @PathVariable("id") Integer Calender_Id,
			Principal prin) {
		Calender calender = this.calenderService.getCalender(Calender_Id);
		//사용자의 학번과 작성자의 학번 비교
		if(!calender.getUserInfo().equals(prin.getName())) {//getName() 사용자의 학번받아와야함
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
		//CalenderForm.java에 캘린더 id의 정보 전달
        calenderForm.setCalender_Register(calender.getCalender_Register());
        calenderForm.setCalender_Text(calender.getCalender_Text());
        calenderForm.setCalender_Deadline(calender.getCalender_Deadline());
        calenderForm.setCalender_Category(calender.getCalender_Category());
        calenderForm.setCalender_Replay(calender.getCalender_Replay());
		return "캘린더수정html";
	}
	
	//*역할조건 안넣음 @PreAuthorize
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid CalenderForm calenderForm, BindingResult bindingResult, 
            Principal prin, @PathVariable("id") Integer Calender_Id) {
        if (bindingResult.hasErrors()) {
            return "캘린더수정html";
        }
        Calender calender = this.calenderService.getCalender(Calender_Id);
      //사용자의 학번과 작성자의 학번 비교
        if(!calender.getUserInfo().equals(prin.getName())) {//getName() 사용자의 학번받아와야함
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        //CalenderForm에서 받아온 값을 CalenderService에서 저장
        this.calenderService.ModifyCalenderData(calender, calenderForm.getCalender_Register(),
        		calenderForm.getCalender_Text(), calenderForm.getCalender_Deadline(), 
        		calenderForm.getCalender_Category(),calenderForm.getCalender_Replay());
        return ("redirect:/CalenderMain"); //달력 출력 화면으로 전환
    }
}
