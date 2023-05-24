package com.example.demo.table;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NoticeComment {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="Com_Id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="NOTICE_ID")
	private Notice notice;
	
	@ManyToOne
	@JoinColumn(name = "STUDENT_ID")
	private UserInfo userinfo;
	
	@Column(name="Com_Text", columnDefinition = "TEXT")
	private String text;
	
	@Column(name="Com_Register")
	private LocalDateTime register;
}