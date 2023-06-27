
package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.table.NoticeComment;

public interface Repository extends JpaRepository<NoticeComment, Integer> {
		
}
