package com.example.demo.notice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.table.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
	Notice findBytitle(String Notice_Title);
//	Notice findByNoticeTitleAndNoticeText(String Notice_Title, String Notice_Text);
}