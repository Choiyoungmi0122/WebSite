package com.insight.board;

import java.time.LocalDateTime;

import com.insight.user.UserInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeCommentDTO {
	private Integer cmId;
	private Notice noticeId;
	private UserInfo cmAuthor;
	private String cmText;
	private LocalDateTime cmRegister;
	private LocalDateTime cmModifyRegister;
	
	public static NoticeCommentDTO toCommentDTO(NoticeComment noticeComment, Integer id) {
		NoticeCommentDTO noticeCommentDTO = new NoticeCommentDTO();
		noticeCommentDTO.setCmId(noticeComment.getCmId());
		noticeCommentDTO.setNoticeId(noticeComment.getNotice());
		noticeCommentDTO.setCmAuthor(noticeComment.getCmAuthor());
		noticeCommentDTO.setCmText(noticeComment.getCmText());
		noticeCommentDTO.setCmRegister(noticeComment.getCmRegister());
		noticeCommentDTO.setCmModifyRegister(noticeComment.getCmModifyRegister());
		return noticeCommentDTO;
	}
}
