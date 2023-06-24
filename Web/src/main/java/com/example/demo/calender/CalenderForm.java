package com.example.demo.calender;

import java.time.LocalDate;

import com.example.demo.table.UserInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//달력 출력되는 창에서 날짜 선택하면 선택한 날짜의 일정 출력하는 부분 변수명
//스프링 부트 2-15
public class CalenderForm {
	
	private Integer id;
	private UserInfo userinfo;
	// html에서 등록자의 변수
	private LocalDate register;
	// html에서 시작 일정의 변수 
	private String text;
	// html에서 입력창의 변수
	
	public UserInfo getUserInfo() {
		//일정의 작성자 이름 반환
		return userinfo;
	}
	
	public LocalDate getRegister() {
		//일정의 등록일 반환
		return register;
	}

	public String getText() {
		//일정의 내용 반환
		return text;
	}
	
	public void setUserInfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	
	public void setRegister(LocalDate register) {
		this.register = register;
	}

	public void setText(String text) {
		this.text = text;
	}
}
