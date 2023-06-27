package com.example.demo.board;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.user.UserInfo;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
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
	private UserInfo userinfo;

	@Column(length = 200)
	private String noticeTitle;
	
	private String noticeNumber;
	
	@Column(columnDefinition = "TEXT")
	private String noticeText;
	
	private LocalDateTime noticeRegister;
	
	@OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
	private List<NoticeComment> commentList;

	private LocalDateTime noticeModifyDate;
}