package com.example.demo.table;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
	private LocalDateTime register;
	@Column(name="Calender_Text")
	private String text;
	@Column(name="Calender_Deadline")
	private LocalDateTime deadline;
	@Column(name="Calender_Category")
	private String category;
	@Column(name="Calender_Replay")
	private String replay;
	
	public Object getUserInfo() {
		return userinfo;
	}

	public void setuserinfo(UserInfo userInfo) {
		this.userinfo=userInfo;
	}

	public void setRegister(LocalDateTime register) {
		this.register=register;
	}

	public void setText(String text) {
		this.text=text;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline=deadline;
	}

	public void setReplay(String replay) {
		this.replay=replay;
	}

	public void setCategory(String category) {
		this.category=category;
	}

	public LocalDateTime getRegister() {
		return register;
	}

	public String getText() {
		return text;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public String getCategory() {
		return category;
	}

	public String getReplay() {
		return replay;
	}

}
