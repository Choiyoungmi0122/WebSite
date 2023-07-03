package com.version1.insight.calendar;

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

import com.version1.insight.user.UserInfo;
import com.version1.insight.user.UserService;


@RequestMapping("/calendar")
@RequiredArgsConstructor
@Controller

public class CalendarController {
	private final CalendarService calService;
	private final UserService userService;
	
	@GetMapping("")
	public String calendarMain() {
        return "calendar_main";
    }
	
	@GetMapping("/{day}")
	public String loadCalendar(Model model,@PathVariable LocalDate day) {
		List<Calendar> calendar = this.calService.getDayList(day);
		model.addAttribute("calendar",calendar);
		return "calendar_main";
	}

	@GetMapping("/create")
    public String create(CalendarRegisteForm calendarRegisterForm) {
        return "calendar_create";
    }
	
	//일정 등록 html에서 등록 버튼 눌렀을 때 저장하는 함수
	@PostMapping("/create")
	public String createAnswer(CalendarRegisteForm calendarRegisterForm, Principal principal) {
		UserInfo userInfo = this.userService.getUser(principal.getName());
        calService.addData(userInfo, calendarRegisterForm.getCalStartDay(), calendarRegisterForm.getCalText());
        return "calendar_main";
    }

	
	@GetMapping("/modify/{id}")
	public String modifyCalendar(CalendarRegisteForm calendarRegisterForm, @PathVariable("id") Integer calId
			,Principal principal) {
		Calendar calendar = this.calService.getInfo(calId);
		if (!calendar.getCalAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
		//매개변수를 CalendarForm, 일정의 id, 사용자의 정보로 한다.
		calendarRegisterForm.setCalStartDay((calendar.getCalStartDay()).toString());
		calendarRegisterForm.setCalText(calendar.getCalText());
		return "calendar_modify";
	}
	
	
	//일정 수정 페이지에서 일정을 수정후 확인 버튼을 누르면 작동하는 함수
	//역할조건 안넣음 @PreAuthorize
    @PostMapping("/modify/{id}")
    public String modifyCalendar(@Valid CalendarRegisteForm calendarRegisteForm, 
    		BindingResult bindingResult, Principal principal, @PathVariable("id") Integer calId) {
        if (bindingResult.hasErrors()) {
            return "calendar_modify";
        }
        Calendar calendar = this.calService.getInfo(calId);
        if (!calendar.getCalAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.calService.modify(calendar, calendarRegisteForm.getCalStartDay(), calendarRegisteForm.getCalText());

        return ("redirect:/calendar"); //달력 출력 화면으로 전환
    }
    
    //일정 삭제 시 작동하는 함수
	@GetMapping("/delete/{id}")
    public String deleteCalendar(@PathVariable("id") Integer calId,
			Principal principal) {
		Calendar calendar = this.calService.getInfo(calId);
		
		if(!calendar.getCalAuthor().getUsername().equals(principal.getName()))/*사용자의 학번과 작성자의 학번 비교)*/ {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
		this.calService.delete(calendar);
    	return ("redirect:/calendar");
    }
}
