package com.example.demo.user;

import jakarta.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController{
	private final UserService userService;
	
	@GetMapping("/join")
	public String join(UserCreateForm userCreateForm) {
		return "JoinMain";
	}
	
	@PostMapping("/join")
	public String join(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "JoinMain";
		}
		
		if (!userCreateForm.getUsPwd1().equals(userCreateForm.getUsPwd2())) {
			bindingResult.rejectValue("pwd2", "passwordInCorrect",
					"2개의 패스워드가 일치하지 않습니다.");
			return "JoinMain";
		}
		try {
			userService.create(userCreateForm.getUsId(), userCreateForm.getUsName(), userCreateForm.getUsPwd1(), userCreateForm.getUsEmail());
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 부원입니다.");
			return "JoinMain";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "JoinMain";
		}
		
		return "redirect:/";
	}
	@GetMapping("/login")
    public String login() {
        return "redirect:/";
    }
}