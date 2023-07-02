package com.example.demo.board;


import java.time.LocalDateTime;

import com.example.demo.user.UserInfo;

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
	private Integer cmId;
	
	@ManyToOne
	private Notice notice;
	
	@ManyToOne
	private UserInfo userinfo;
	
	@Column(columnDefinition = "TEXT")
	private String cmText;
	
	private LocalDateTime cmRegister;
}