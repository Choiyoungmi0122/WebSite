package com.insight.board;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NoticeFileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fileId;
	
	private String originalFileName;
	
	private String storedFileName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "noticeId")
	private Notice notice;
	
	public static NoticeFileEntity toNoticeFileEntity(Notice notice,
			String originalFileName, String storedFileName) {
		NoticeFileEntity noticeFileEntity = new NoticeFileEntity();
		noticeFileEntity.setOriginalFileName(originalFileName);
		noticeFileEntity.setStoredFileName(storedFileName);
		noticeFileEntity.setNotice(notice);
		return noticeFileEntity;
	}
}
