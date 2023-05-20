//package com.example.demo;
//
//import com.example.demo.notice.*;
//import com.example.demo.table.Notice;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class WebApplicationTests {
//
//	@Autowired
//	private NoticeRepository noticeRepository;
//	
//	@Test
//	void testJpa() {
//        Notice q1 = new Notice();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        q1.setCreateDate(LocalDateTime.now());
//        this.noticeRepository.save(q1);  // 첫번째 질문 저장
//
//        Notice q2 = new Notice();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setCreateDate(LocalDateTime.now());
//        this.noticeRepository.save(q2);  // 두번째 질문 저장
//	}
//
//}
