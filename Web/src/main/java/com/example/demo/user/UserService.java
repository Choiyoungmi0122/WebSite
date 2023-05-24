package com.example.demo.user;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.share.DataNotFoundException;
import com.example.demo.table.UserInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserInfo create(Integer id, String name, String pwd, String email) {
		UserInfo userinfo = new UserInfo();
		userinfo.setId(id);
		userinfo.setName(name);
		userinfo.setPwd(passwordEncoder.encode(pwd));
		userinfo.setEmail(email);
		this.userRepository.save(userinfo);
		return userinfo;
	}
	
	public UserInfo getUser(String name) {
		Optional<UserInfo> userInfo = this.userRepository.findByname(name);
		if(userInfo.isPresent()) {
			return userInfo.get();
		}else {
			throw new DataNotFoundException("not found");
		}
	}
	
}