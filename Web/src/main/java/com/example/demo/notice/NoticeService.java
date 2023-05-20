package com.example.demo.notice;

import com.example.demo.table.Notice;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
}
