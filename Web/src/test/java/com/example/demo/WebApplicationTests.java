package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.notice.*;
import com.example.demo.table.Notice;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class WebApplicationTests {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Test
	void testJpa() {
		Notice N = this.noticeRepository.findByNoticeTitle("1번글 제목");
		assertEquals(2, N.getnoticeId());
		}
}


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
