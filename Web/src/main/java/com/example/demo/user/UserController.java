package com.example.demo.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "LoginMain";
	}
	
	@GetMapping("/join")
	public String JoinMain(UserCreateForm userCreateForm) {
		return "JoinMain";
	}
	
	@PostMapping("/join")
	public String JoinMain(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "JoinMain";
		}
		if (!userCreateForm.getPwd1().equals(userCreateForm.getPwd2())) {
			bindingResult.rejectValue("Pwd2", "passwordInCorrect",
					"2개의 패스워드가 일치하지 않습니다.");
			return "JoinMain";
		}
		
		try{
			userService.create(userCreateForm.getStudent_Id(), userCreateForm.getEmail(),
					userCreateForm.getStudent_Name(), userCreateForm.getPwd1());
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "JoinMain";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "JoinMain";
		}
		return "JoinComplete";
	}
	
	

}
