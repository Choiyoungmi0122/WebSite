package com.example.demo.notice;

import com.example.demo.share.DataNotFoundException;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class NoticeService {
	
	private final NoticeRepository noticeRepository;
	
	public Page<Notice> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("register"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.noticeRepository.findAll(pageable);
	}
	
	public Page<Notice> getCategory(String number, int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("register"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.noticeRepository.findByNumber(number, pageable);
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