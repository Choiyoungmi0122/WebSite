package com.version1.insight;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.version1.insight.board.NoticeService;
import com.version1.insight.user.UserInfo;
import com.version1.insight.user.UserRepository;

@SpringBootTest
class InsightApplicationTests {

    @Autowired
    private NoticeService noticeService;
    private UserRepository us;

    @Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            
            this.noticeService.test(subject, content,"자유");
        }
    }
    

}
