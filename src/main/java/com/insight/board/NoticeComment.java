package com.insight.board;

import java.time.LocalDateTime;

import com.insight.user.UserInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NoticeComment {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer cmId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeId")
	private Notice notice;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cmAuthor")
	private UserInfo cmAuthor;
	
	@Column(columnDefinition = "TEXT")
	private String cmText;
	
	private LocalDateTime cmRegister;
	
	private LocalDateTime cmModifyRegister;
	
	public static NoticeComment toSaveEntity(NoticeCommentDTO noticeCommentDTO, Notice notice, UserInfo userInfo) {
		NoticeComment noticeComment = new NoticeComment();
		noticeComment.setNotice(notice);
		noticeComment.setCmAuthor(userInfo);
		noticeComment.setCmText(noticeCommentDTO.getCmText());
		return noticeComment;
	}
}
