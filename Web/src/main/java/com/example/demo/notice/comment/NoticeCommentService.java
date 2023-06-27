package com.example.demo.notice.comment;

import com.example.demo.table.Notice;
import com.example.demo.table.NoticeComment;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class NoticeCommentService {

	private final NoticeCommentRepository noticeCommentRepository;
	
	public void noticecommentinput(Notice notice, String text) {
		NoticeComment noticecomment = new NoticeComment();
		noticecomment.setText(text);
		noticecomment.setRegister(LocalDateTime.now());
		noticecomment.setNotice(notice);
		this.noticeCommentRepository.save(noticecomment);
	}
}
