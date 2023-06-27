package com.example.demo.board;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class NoticeCommentService {

	private final NoticeCommentRepository noticeCommentRepository;
	
	public void noticecommentinput(Notice notice, String text) {
		NoticeComment noticecomment = new NoticeComment();
		noticecomment.setCmText(text);
		noticecomment.setCmRegister(LocalDateTime.now());
		noticecomment.setNotice(notice);
		this.noticeCommentRepository.save(noticecomment);
	}
}
