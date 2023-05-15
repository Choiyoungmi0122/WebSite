package com.Calender;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Calender 등록창에서 경고 메세지
public class CalenderForm {
	//제목을 안적었을 경우
	@NotEmpty(message = "내용은 필수항목입니다.")
	private String Calender_Text;
}
