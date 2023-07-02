package com.example.demo.calender;

import java.time.LocalDate;

import com.example.demo.user.UserInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//달력 출력되는 창에서 날짜 선택하면 선택한 날짜의 일정 출력하는 부분 변수명
//스프링 부트 2-15
public class CalenderForm {
	
	private Integer calId;
	private UserInfo calUserinfo;
	// html에서 등록자의 변수
	private LocalDate calRegister;
	// html에서 시작 일정의 변수 
	private String calText;
	// html에서 입력창의 변수
}
