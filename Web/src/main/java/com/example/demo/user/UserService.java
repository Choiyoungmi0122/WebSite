package com.example.demo.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(Integer Student_Id, String Student_Name, String Email, String Pwd) {
        SiteUser user = new SiteUser();
        user.setStudent_Id(Student_Id);
        user.setStudent_Name(Student_Name);
        user.setEmail(Email);
        user.setPwd(passwordEncoder.encode(Pwd));
        this.userRepository.save(user);
        return user;
    }
    
    public SiteUser getUser(String Student_Name) {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(Student_Name);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }

}