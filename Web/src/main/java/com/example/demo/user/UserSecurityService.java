package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
		Optional<UserInfo> _userInfo = this.userRepository.findByname(name);
		if(_userInfo.isEmpty()) {
			throw new UsernameNotFoundException("해당 부원을 찾을 수 없습니다.");
		}
		UserInfo userInfo = _userInfo.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		if("admin".equals(name)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		return new User(userInfo.getName(), userInfo.getPwd(), authorities);
	}
}