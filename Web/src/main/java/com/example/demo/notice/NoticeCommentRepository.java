package com.example.demo.notice;
import com.example.demo.table.NoticeComment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Integer> {
		
}