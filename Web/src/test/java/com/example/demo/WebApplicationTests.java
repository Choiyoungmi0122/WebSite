package com.example.demo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.important.ImportantRepository;
import com.example.demo.table.Important;

@SpringBootTest
class SbbApplicationTests {

//   @Autowired
//    private ImportantRepository importantRepository;
//
//    @Test
//    void testJpa() {        
//        Important i1 = new Important();
//        i1.setID("01");
//        i1.setTitlee("Hello");
//        i1.setText("lalalal");
//        i1.setRegister(LocalDateTime.now());
//        i1.setImpor(default);
//        this.importantRepository.save(i1);  // 첫번째 질문 저장
//    }
}