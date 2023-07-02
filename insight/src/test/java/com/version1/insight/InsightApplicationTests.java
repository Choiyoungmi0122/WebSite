package com.version1.insight;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.version1.insight.board.Important;
import com.version1.insight.board.ImportantRepository;

@SpringBootTest
class InsightApplicationTests {

    @Autowired
    private ImportantRepository importantRepository;

    @Test
    void testJpa() {        
        Important q1 = new Important();
        q1.setImpoTitle("sbb가 무엇인가요?");
        q1.setImpoText("sbb에 대해서 알고 싶습니다.");
        q1.setImpoTf(true);
        q1.setImpoRegister(LocalDateTime.now());
        this.importantRepository.save(q1);  // 첫번째 질문 저장
        }
}
