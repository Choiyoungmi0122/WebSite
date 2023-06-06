package com.example.demo.notice.comment;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.table.NoticeComment;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Integer> {
		
}
