package com.example.demo.table;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;



public class UserInfo {
	@Id
	@Column(unique = true)
	private String sid;
	
	private String sname;
	
	private String password;
	
	@Column(unique = true)
	private String semail;
	
	//Calender에 PK 쓰기위해 선언
	@OneToMany(mappedBy="UserInfo")
	private Calender calender;
	
	//Schedule에 PK 쓰기위해 선언
	@ManyToMany(mappedBy="UserInfo")
	private Schedule schedule;
	

}
