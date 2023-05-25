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
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noticeId;
	
	@ManyToOne
	@JoinColumn(name="STUNDENT_ID")
	private UserInfo userinfo;

	@Column(name="Notice_Title", length = 200)
	private String noticeTitle;
	
	@Column(name="Notice_Number")
	private String number;
	
	@Column(name="Notice_Text", columnDefinition = "TEXT")
	private String text;
	
	@Column(name="Notice_Register")
	private LocalDateTime register;
	
	@OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
	private List<NoticeComment> commentList;

}