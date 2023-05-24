package com.example.demo.notice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.table.NoticeComment;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Integer> {
		
}