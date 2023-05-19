package com.example.demo.calender;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Calender 등록창에서 경고 메세지
//여기있는 변수 이름으로 html 입력칸 이름 적기
//스프링 부트 2-15
public class CalenderForm {
	
	private LocalDateTime Calender_Register;
	private LocalDateTime Calender_Deadline;
	private String Calender_Category;
	private String Calender_Replay;
	//제목을 안적었을 경우
	@NotEmpty(message = "내용은 필수항목입니다.")
	private String Calender_Text;
	
	
}
