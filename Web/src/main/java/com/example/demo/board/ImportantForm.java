package com.example.demo.board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ImportantForm {
	@NotEmpty(message="제목은 필수항목입니다.")	//Not Null
	@Size(max=200)
	private String impoTitle;
	
	@NotEmpty(message="내용은 필수항목입니다.")
	private String impoText;
}
