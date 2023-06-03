package com.example.demo.notice;

import com.example.demo.share.*;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.table.Notice;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class NoticeService {
	
	private final NoticeRepository noticeRepository;
	
	public List<Notice> getList(){
		return this.noticeRepository.findAll();
	}
	
	public Notice getNotice(Integer id) {
		Optional<Notice> notice = this.noticeRepository.findById(id);
		if (notice.isPresent()) {
			return notice.get();
		} else {
			throw new DataNotFoundException("Notice not found");
		}
	}
	
	public void noticeinput(String title, String number, String text) {
		Notice n = new Notice();
		n.setTitle(title);
		n.setNumber(number);
		n.setText(text);
		n.setRegister(LocalDateTime.now());
		this.noticeRepository.save(n);
		
	}
}
