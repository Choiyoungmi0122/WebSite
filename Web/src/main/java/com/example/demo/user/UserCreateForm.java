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
	private Integer Student_Id;

	@NotEmpty(message = "필수항목입니다.")
	private String Student_Name;
	
	@Size(min = 8, max = 15)
	@NotEmpty(message = "필수항목입니다.")
	private String Pwd1;
	
	@Size(min = 8, max = 15)
	@NotEmpty(message = "필수항목입니다.")
	private String Pwd2;
	
	@NotEmpty(message = "필수항목입니다.")
	@Email
	private String Email;
}
