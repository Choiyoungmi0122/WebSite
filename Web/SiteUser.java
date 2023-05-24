package com.example.demo.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SiteUser {
	
	@Id
	@Column(unique = true)
	private Integer Student_Id;
	
	private String Student_Name;
	
	private String Pwd;
	
	@Column(unique = true)
	private String Email;
}