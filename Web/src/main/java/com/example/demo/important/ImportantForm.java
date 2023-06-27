package com.example.demo.important;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ImportantForm {
	@NotEmpty(message="제목은 필수항목입니다.")	//Not Null
	@Size(max=200)
	private String Impor_Title;
	
	@NotEmpty(message="내용은 필수항목입니다.")
	private String Impor_Text;

	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
}
