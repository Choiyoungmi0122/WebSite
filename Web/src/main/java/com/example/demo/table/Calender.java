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
		// TODO Auto-generated method stub
		return userinfo;
	}

	public void setuserinfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		this.userinfo=userInfo;
	}

	public void setRegister(LocalDateTime register) {
		// TODO Auto-generated method stub
		this.register=register;
	}

	public void setText(String text) {
		// TODO Auto-generated method stub
		this.text=text;
	}

	public void setDeadline(LocalDateTime deadline) {
		// TODO Auto-generated method stub
		this.deadline=deadline;
	}

	public void setReplay(String replay) {
		// TODO Auto-generated method stub
		this.replay=replay;
	}

	public void setCategory(String category) {
		// TODO Auto-generated method stub
		this.category=category;
	}
}
