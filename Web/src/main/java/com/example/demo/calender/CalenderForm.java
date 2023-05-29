package com.example.demo.calender;

import java.time.LocalDateTime;

import com.example.demo.table.UserInfo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//달력 출력되는 창에서 날짜 선택하면 선택한 날짜의 일정 출력하는 부분 변수명
//스프링 부트 2-15
public class CalenderForm {
	
	private UserInfo userinfo;
	// html에서 등록자의 변수
	private LocalDateTime Calender_Register;
	// html에서 시작 일정의 변수 
	private LocalDateTime Calender_Deadline;
	// html에서 종료 일정의 변수 
	private String Calender_Text;
	// html에서 입력창의 변수
	
	
}
