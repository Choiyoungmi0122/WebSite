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