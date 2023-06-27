package com.example.demo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@Size(min=8, max = 8)
	@NotEmpty(message =" 학번은 필수항목입니다.")
	private String usId;
	
	@NotEmpty(message="이름은 필수항목입니다.")
	private String usName;
	
	@Size(min = 8, max = 15)
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String usPwd1;
	
	@Size(min = 8, max = 15)
	@NotEmpty(message = "비밀번호 확인은 필수항목압니다.")
	private String usPwd2;
	
	@NotEmpty(message="이메일은 필수항목입니다.")
	@Email
	private String usEmail;
}