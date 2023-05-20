package com.example.demo.table;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="notice")
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Notice_Id;
	
//	@ManyToOne
//	@JoinColumn(name="STUNDENT_ID")
//	private Userinfo userinfo;

	@Column(length = 200)
	private String Notice_Title;
	
	private String Notice_Number;
	
	@Column(columnDefinition = "TEXT")
	private String Notice_Text;
	
	private LocalDateTime Notice_Register;
	
	@OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
	private List<NoticeComment> commentList;
}