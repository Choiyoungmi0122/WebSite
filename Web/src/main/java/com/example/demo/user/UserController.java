package com.example.demo.user;

import jakarta.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/UserInfo")
public class UserController{
	private final UserService userService;

	@GetMapping("/JoinMain")
	public String signup(UserCreateForm userCreateForm){
		return "JoinMain";
	}

	@PostMapping("/JoinMain")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			return "JoinMain";
		}

		if (!userCreateForm.getPwd1().equals(userCreateForm.getPwd2())){
			bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
			return "JoinMain";
		}
		try{
			userService.create(userCreateForm.getStudent_Id(),userCreateForm.getStudent_Name(),
				userCreateForm.getEmail(), userCreateForm.getPwd1());
		}catch(DataIntegrityViolationException e){
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 동아리 부원입니다.");
			return "JoinMain";
		}catch(Exception e){
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "JoinMain";
		}
		return "redirect:/";
	}
}