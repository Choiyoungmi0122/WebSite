package com.insight.user;

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
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> _siteUser = this.userRepository.findByusername(username);
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        UserInfo siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (siteUser.getAdminAut().equals("관리자")) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        }else if (siteUser.getAdminAut().equals("부원")) {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }else if (siteUser.getAdminAut().equals("탈퇴")) {
        	throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }else {
        	authorities.add(new SimpleGrantedAuthority(UserRole.GUEST.getValue()));
        }
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}