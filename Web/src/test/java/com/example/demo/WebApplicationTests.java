//package com.example.demo;
//
//import java.time.LocalDateTime;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.example.demo.table.Important;
//import com.example.demo.important.ImportantRepository;
//
//@SpringBootTest
//class SbbApplicationTests {
//
//	private final ImportantRepository importantRepository;
//
//    @Autowired
//    public SbbApplicationTests(ImportantRepository importantRepository) {
//        this.importantRepository = importantRepository;
//    }
//
//    @Test
//    void testJpa() {        
//    	Important q1 = new Important();
//        q1.setTitle("sbb가 무엇인가요?");
//        q1.setText("sbb에 대해서 알고 싶습니다.");
//        q1.setRegister(LocalDateTime.now());
//        q1.setImportant(true);
//        this.importantRepository.save(q1);  // 첫번째 질문 저장
//    }
//}
