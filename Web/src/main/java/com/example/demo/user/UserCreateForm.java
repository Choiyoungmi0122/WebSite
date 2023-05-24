package com.example.demo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@NotEmpty(message = "이름은 필수항목입니다.")
	private String Student_Name;

	@Size(min = 8, max = 8)
	@NotEmpty(message = "학번은 필수항목입니다.")
	private Integer Student_Id;

	@Size(min = 8, max = 15)
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String Pwd1;

	@Size(min = 8, max = 15)
	@NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
	private String Pwd2;

	@NotEmpty(message = "이메일은 필수항목입니다.")
	@Email
	private String Email;
}