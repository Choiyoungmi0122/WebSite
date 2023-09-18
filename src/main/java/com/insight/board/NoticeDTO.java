package com.insight.board;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.insight.user.UserInfo;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
	private Integer noticeId;
	private String noticeTitle;
	private String noticeText;
	private String noticeCategory;
	private UserInfo noticeAuthor;
	private LocalDateTime noticeRegister;
	private LocalDateTime noticeModifyRegister;
	
	private MultipartFile noticeFile;
	private String originalFileName;
	private String storedFileName;
	private Integer fileAttached;
	
	public NoticeDTO(Integer noticeId, String noticeTitle, String noticeText,
			String noticeCategory, UserInfo userInfo, LocalDateTime noticeRegister,
			LocalDateTime noticeModifyRegister) {
		this.noticeId = noticeId;
		this.noticeTitle = noticeTitle;
		this.noticeText = noticeText;
		this.noticeCategory = noticeCategory;
		this.noticeAuthor = userInfo;
		this.noticeRegister = noticeRegister;
		this.noticeModifyRegister = noticeModifyRegister;
	}
	
	public static NoticeDTO toNoticeDTO(Notice notice) {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setNoticeId(notice.getNoticeId());
		noticeDTO.setNoticeTitle(notice.getNoticeTitle());
		noticeDTO.setNoticeText(notice.getNoticeText());
		noticeDTO.setNoticeCategory(notice.getNoticeCategory());
		noticeDTO.setNoticeRegister(notice.getNoticeRegister());
		noticeDTO.setNoticeModifyRegister(notice.getNoticeModifyRegister());
		if(notice.getFileAttached()==0) {
			noticeDTO.setFileAttached(notice.getFileAttached());
		}else {
			noticeDTO.setFileAttached(notice.getFileAttached());
			// 파일 이름을 가져가야 함.
            // orginalFileName, storedFileName : board_file_table(BoardFileEntity)
            // join
            // select * from board_table b, board_file_table bf where b.id=bf.board_id
            // and where b.id=?
			noticeDTO.setOriginalFileName(notice.getNoticeFileEntityList().get(0).getOriginalFileName());
			noticeDTO.setStoredFileName(notice.getNoticeFileEntityList().get(0).getStoredFileName());
		}
		return noticeDTO;
	}
	
}
