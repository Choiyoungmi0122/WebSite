package com.example.demo.table;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Calender {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Calender_Id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="Student_Id")
	private UserInfo userinfo;

	@Column(name="Calender_Register")
	private LocalDate register;
	@Column(name="Calender_Text")
	private String text;

	
	/*
	public Integer getId() {
		return id;
	}
	
	public Object getUserInfo() {
		return userinfo;
	}

	public LocalDate getRegister() {
		return register;
	}
	
	public String getText() {
		return text;
	}
	
	public void setId(Integer Id) {
		this.id = Id;
	}
	
	public void setUserinfo(UserInfo userInfo) {
		this.userinfo=userInfo;
	}

	public void setRegister(LocalDate register) {
		this.register=register;
	}

	public void setText(String text) {
		this.text=text;
	}
	*/

}
