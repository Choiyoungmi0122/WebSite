/*
package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.notice.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class WebApplicationTests {

	@Autowired
	private NoticeService noticeService;
	
    @Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String title = String.format("테스트 데이터입니다:[%03d]", i);
            String number = "coding";
            String text = "내용무";
            this.noticeService.noticeinput(title, number, text);
        }
    }
	
//	@Test
//	void testJpa() {
//		Notice N = this.noticeRepository.findBytitle("1번글 제목");
//		assertEquals(2, N.getId());
//		}
}
*/

//@Test
//void testJpa() {
//	assertEquals(2, this.noticeRepository.count());
//	Optional<Notice> on = this.noticeRepository.findById(1);
//	assertTrue(on.isPresent());
//	Notice n = on.get();
//	this.noticeRepository.delete(n);
//	assertEquals(1, this.noticeRepository.count());
//}



//@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//private Integer Notice_Id;
//
////@ManyToOne
////@JoinColumn(name="STUNDENT_ID")
////private Userinfo userinfo;
//
//@Column(length = 200)
//private String Notice_Title;
//
//private String Notice_Number;
//
//@Column(columnDefinition = "TEXT")
//private String Notice_Text;
//
//private LocalDateTime Notice_Register;
//
//@OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
//private List<NoticeComment> commentList;

//package com.example.demo;
//
//import java.time.LocalDateTime;
//
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.example.demo.table.UserInfo;
//import com.example.demo.user.*;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//@SpringBootTest
//class WebApplicationTests {
//	
//	@Autowired
//	private UserService userService;
//	
//	
//	@Test
//	void testJpa() {
//		
//		UserInfo user = new UserInfo();
//		user.setId(id);
//		user.setName(name);
//		user.setPwd(passwordEncoder.encode(pwd));
//		user.setEmail(email);
//		this.userRepository.save(user);
//		return user;
//	}
//}



