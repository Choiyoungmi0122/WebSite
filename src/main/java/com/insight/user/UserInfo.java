package com.insight.user;

//import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserInfo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//학번
	@Column(unique = true, length = 8)
	private String username;
	
	private String studentName;
	
	private String password;
	
	@Column(unique=true)
	private String email;
	
	@Column(unique=true)
	private String phoneNumber;
	
	private String major;
	private String grade;
	private String doing;
	private String condition;
	private String wantedAct;
	private String introduction;
	private LocalDate joinDate;

	private Boolean adminAut;

//	@Builder
//	public UserInfo(String username, String studentName, String password,
//			String email, String adminAut) {
//		this.username = username;
//		this.studentName = studentName;
//		this.password = password;
//		this.email = email;
//		this.adminAut = adminAut;
//	}
}
