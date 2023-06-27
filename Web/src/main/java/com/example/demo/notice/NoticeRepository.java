package com.example.demo.notice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.table.Notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NoticeRepository extends JpaRepository<Notice, Integer> {
	Notice findByTitle(String Notice_Title); // 제목 호출
	// Notice findByNumber(String Notice_Number); // 카테고리 호출
	Notice findByTitleAndText(String Notice_Title, String Notice_Text); // 제목 및 내용 호출
	Page<Notice> findAll(Pageable pageable); // main 리스트 호출
	Page<Notice> findByNumber(String Notice_Number, Pageable pageable); // 카테고리 설정
}