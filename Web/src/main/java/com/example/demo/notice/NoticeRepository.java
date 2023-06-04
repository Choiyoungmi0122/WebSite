package com.example.demo.notice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.table.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NoticeRepository extends JpaRepository<Notice, Integer> {
	Notice findBytitle(String Notice_Title);
	Notice findBytitleAndText(String Notice_Title, String Notice_Text);
	Page<Notice> findAll(Pageable pageable);
}