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
	private Integer Com_Id;
	
	@ManyToOne
	@JoinColumn(name="NOTICE_ID")
	private Notice notice;
	
	// @ManyToOne (mappedBy = "UserInfo")
	// private Integer Student_Id;
	
	@Column(columnDefinition = "TEXT")
	private String Com_Text;
	
	private LocalDateTime Com_Register;

}