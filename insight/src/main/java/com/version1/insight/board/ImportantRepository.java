package com.version1.insight.board;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportantRepository extends JpaRepository<Important, Integer>{
	Page<Important> findAll(Pageable pageable); // main 리스트 호출
}
