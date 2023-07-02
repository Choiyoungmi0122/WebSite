package com.example.demo.user;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.share.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserInfo create(String id, String name, String pwd, String email) {
		UserInfo user = new UserInfo();
		user.setUsId(id);
		user.setUsName(name);
		user.setUsPwd(passwordEncoder.encode(pwd));
		user.setUsEmail(email);
		this.userRepository.save(user);
		return user;
	}
	
	public UserInfo getUser(String name) {
		Optional<UserInfo> userInfo = this.userRepository.findByUsName(name);
		if(userInfo.isPresent()) {
			return userInfo.get();
		}else {
			throw new DataNotFoundException("not found");
		}
	}
	
}