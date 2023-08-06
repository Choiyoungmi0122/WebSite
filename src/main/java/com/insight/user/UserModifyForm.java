package com.insight.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModifyForm {

	private String username;
	
	@NotEmpty(message = "이름은 필수항목입니다.")
	private String studentName;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;
    
    @NotEmpty(message = "번호는 필수항목입니다.")
    private String phoneNumber;
	
    @NotEmpty(message = "전공은 필수항목입니다.")
	private String major;
    
    @NotEmpty(message = "학년은 필수항목입니다.")
	private String grade;
    
	private String doing;
	
	@NotEmpty(message = "재적상태는 필수항목입니다.")
	private String condition;
	
	@NotEmpty(message = "원하는 활동은 필수항목입니다.")
	private String wantedAct;
	
	private String introduction;
	
	private String adminAut;
	
}