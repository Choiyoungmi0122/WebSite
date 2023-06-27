package com.example.demo.calender;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.user.UserInfo;

@RequestMapping("/calender")
@RequiredArgsConstructor
@Controller

public class CalenderController {
	private final CalenderService calService;
	
	@GetMapping("")
	public String calenderMain() {
        return "CalenderMain";
    }
	
	@GetMapping("/{day}")
	public String loadCalender(Model model,@PathVariable LocalDate day) {
		List<Calender> calender = this.calService.getDayList(day);
		model.addAttribute("calender",calender);
		return "CalenderMain";
	}

	@GetMapping("/create")
    public String create(CalenderRegisteForm calenderRegisterForm) {
        return "CalenderCreate";
    }
	
	//일정 등록 html에서 등록 버튼 눌렀을 때 저장하는 함수
	@PostMapping("/create")
	public String createAnswer(Model model, @RequestParam String calRegister, @RequestParam String calText,
			@RequestParam String calUserinfo, Principal principal) {
        calService.addData(/*principal.getName()*/calUserinfo, LocalDate.parse(calRegister), calText);
        return "CalenderMain";
    }

	
	@GetMapping("/modify/{id}")
	public String modify(Model model, CalenderRegisteForm calenderRegisterForm, @PathVariable Integer id) {
		//매개변수를 CalenderForm, 일정의 id, 사용자의 정보로 한다.
		Calender cal = this.calService.getInfo(id);
		//model.addAttribute("cal", cal);
		calenderRegisterForm.setCalRegister((cal.getCalRegister()).toString());
		calenderRegisterForm.setCalText(cal.getCalText());
		model.addAttribute("calenderRegisterForm",calenderRegisterForm);
		return "CalenderModify";
	}
	
	
	//일정 수정 페이지에서 일정을 수정후 확인 버튼을 누르면 작동하는 함수
	//역할조건 안넣음 @PreAuthorize
    @PostMapping("/modify/{id}")
    public String modifyCalender(@Valid CalenderRegisteForm calenderRegisteForm, 
    		BindingResult bindingResult, Principal principal, @PathVariable("id") Integer Calender_Id) {
        if (bindingResult.hasErrors()) {
            return "CalenderModify";
        }
        Calender calender = this.calService.getInfo(Calender_Id);
        
        this.calService.modify(calender, calenderRegisteForm.getCalRegister(),
        		calenderRegisteForm.getCalText());

        return ("redirect:/calender"); //달력 출력 화면으로 전환
    }
    
    //일정 삭제 시 작동하는 함수
	@GetMapping("/delete/{id}")
    public String deleteCalender(@PathVariable("id") Integer Calender_Id,
			Principal p) {
		Calender calender = this.calService.getInfo(Calender_Id);
		
		if(!calender.getUserinfo().equals(p.getName()))/*사용자의 학번과 작성자의 학번 비교)*/ {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
		this.calService.delete(calender);
    	return ("redirect:/calender");
    }
}
