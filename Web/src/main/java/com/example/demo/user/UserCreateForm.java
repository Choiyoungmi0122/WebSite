package com.example.demo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@Size(min = 8, max = 8)
	@NotEmpty(message = "필수항목입니다.")
	private String sid;

	@NotEmpty(message = "필수항목입니다.")
	private String sname;

	@NotEmpty(message = "필수항목입니다.")
	private String pwd1;

	@NotEmpty(message = "필수항목입니다.")
	private String pwd2;
	
	@NotEmpty(message = "필수항목입니다.")
	@Email
	private String semail;
}
