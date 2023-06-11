package com.example.demo.calender;

import java.time.LocalDateTime;

import com.example.demo.table.UserInfo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//달력 등록, 수정 시에 사용되는 변수
//여기있는 변수 이름으로 등록, 수정 html 입력칸 이름 적기
//스프링 부트 2-15
public class CalenderRegisteForm {
	
	private LocalDateTime register;
	// html에서 시작 일정의 변수 
	private LocalDateTime deadline;
	// html에서 종료 일정의 변수 
	private String category;
	// html에서 카테고리의 변수 
	private String replay;
	// html에서 반복 일정의 변수 
	
	@NotEmpty(message = "내용은 필수항목입니다.")
	//빈칸이면 에러메세지 출력
	private String text;
	// html에서 입력창의 변수
	
	public LocalDateTime getRegister() {
		return register;
	}

	public String getText() {
		return text;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public String getCategory() {
		return category;
	}

	public String getReplay() {
		return replay;
	}

	public void setRegister(LocalDateTime register) {
		this.register=register;
	}

	public void setText(String text) {
		this.text=text;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline=deadline;
	}

	public void setReplay(String replay) {
		this.replay=replay;
	}

	public void setCategory(String category) {
		this.category=category;
	}

	
	
}
