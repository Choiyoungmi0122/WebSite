package com.insight.calendar;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import com.insight.user.UserInfo;
import com.insight.user.UserService;


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
	
	/*
	 * @GetMapping("/{day}") public String loadCalendar(Model model,@PathVariable
	 * LocalDate day) { List<Calendar> calendar = this.calService.getDayList(day);
	 * model.addAttribute("calendar",calendar); return "calendar_main"; }
	 */
	
	@GetMapping("/{day}/main")
	public String loadCalendar(Model model,@PathVariable LocalDate day, @RequestParam(value="page", defaultValue="1") int page) { 
		Page<Calendar> calList = this.calService.getListDayPage(day, page-1);
		model.addAttribute("calList",calList); 
		return "calendar_main"; 
	}
	
	@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
	@GetMapping("/create")
    public String createCalendar(Model model, CalendarRegisteForm calendarRegisteForm) {
		model.addAttribute("create","create");
		calendarRegisteForm.setCalStartDay((LocalDate.now()).toString());
		calendarRegisteForm.setCalEndDay(null);
        return "calendar_form";
    }
	
	@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
	//일정 등록 html에서 등록 버튼 눌렀을 때 저장하는 함수
	@PostMapping("/create")
	public String createCalendar(Model model, @Valid CalendarRegisteForm calendarRegisteForm, BindingResult bindingResult, Principal principal){
		if (bindingResult.hasErrors()) {
            return "calendar_form";
        }
		
		if((calendarRegisteForm.getCalEndDay() != "") && 
				(0<(LocalDate.parse(calendarRegisteForm.getCalStartDay())).compareTo(LocalDate.parse(calendarRegisteForm.getCalEndDay()))))
		{
			model.addAttribute("DateError", "종료일은 시작일 보다 빠를 수 없습니다.");
			return "calendar_form";
			
		}
		if ((calendarRegisteForm.getCalStartTime() != "") && (calendarRegisteForm.getCalEndTime() != "") && 
				(0<(LocalTime.parse(calendarRegisteForm.getCalStartTime())).compareTo(LocalTime.parse(calendarRegisteForm.getCalEndTime())))){
			model.addAttribute("TimeError", "종료 시간은 시작 시간보다 빠를 수 없습니다.");
			return "calendar_form";
		}
		UserInfo userInfo = this.userService.getUser(principal.getName());
        this.calService.addData(userInfo, calendarRegisteForm.getCalText(), calendarRegisteForm.getCalStartDay(), calendarRegisteForm.getCalEndDay(), 
        		calendarRegisteForm.getCalStartTime(), calendarRegisteForm.getCalEndTime());
        return String.format("redirect:/calendar/%s/main", calendarRegisteForm.getCalStartDay());
    }

	@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
	@GetMapping("/modify/{id}")
	public String modifyCalendar(CalendarRegisteForm calendarRegisteForm, Model model, @PathVariable("id") Integer calId
			,Principal principal) {
		model.addAttribute("modify", "modify");
		Calendar calendar = this.calService.getInfo(calId);
		//매개변수를 CalendarForm, 일정의 id, 사용자의 정보로 한다.
		model.addAttribute("calendar",calendar);
		calendarRegisteForm.setCalStartDay((calendar.getCalStartDay()).toString());
		calendarRegisteForm.setCalText(calendar.getCalText());
		
		String adminAut = userService.getUser(principal.getName()).getAdminAut();
		if((!calendar.getCalAuthor().getUsername().equals(principal.getName())) && (!adminAut.equals("관리자"))) {
			return String.format("redirect:/calendar/%s/main", calendar.getCalStartDay()	); //달력 출력 화면으로 전환 
        }
		if(calendar.getCalEndDay()==null)
			calendarRegisteForm.setCalEndDay(null);
		else
			calendarRegisteForm.setCalEndDay(calendar.getCalEndDay().toString());
		if(calendar.getCalStartTime()==null)
			calendarRegisteForm.setCalStartTime(null);
		else
			calendarRegisteForm.setCalStartTime(calendar.getCalStartTime().toString());
		if(calendar.getCalEndTime()==null)
			calendarRegisteForm.setCalEndTime(null);
		else
			calendarRegisteForm.setCalEndTime(calendar.getCalEndTime().toString());
		return "calendar_form";
	}
	
	@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
	//일정 수정 페이지에서 일정을 수정후 확인 버튼을 누르면 작동하는 함수
	//역할조건 안넣음 @PreAuthorize
    @PostMapping("/modify/{id}")
    public String modifyCalendar(@Valid CalendarRegisteForm calendarRegisteForm, Model model,
    		BindingResult bindingResult, Principal principal, @PathVariable("id") Integer calId) {
        if (bindingResult.hasErrors()) {
            return "calendar_form";
        }
        Calendar calendar = this.calService.getInfo(calId);
        if((calendarRegisteForm.getCalEndDay() != "") && 
				(0<(LocalDate.parse(calendarRegisteForm.getCalStartDay())).compareTo(LocalDate.parse(calendarRegisteForm.getCalEndDay()))))
		{
			model.addAttribute("DateError", "종료일은 시작일 보다 빠를 수 없습니다.");
			return "calendar_form";
			
		}
		if ((calendarRegisteForm.getCalStartTime() != "") && (calendarRegisteForm.getCalEndTime() != "") && 
				(0<(LocalTime.parse(calendarRegisteForm.getCalStartTime())).compareTo(LocalTime.parse(calendarRegisteForm.getCalEndTime())))){
			model.addAttribute("TimeError", "종료 시간은 시작 시간보다 빠를 수 없습니다.");
			return "calendar_form";
		}
        this.calService.modify(calendar, calendarRegisteForm.getCalText(), calendarRegisteForm.getCalStartDay(), calendarRegisteForm.getCalEndDay(), 
        		calendarRegisteForm.getCalStartTime(), calendarRegisteForm.getCalEndTime());

        return String.format("redirect:/calendar/%s/main", calendarRegisteForm.getCalStartDay()); //달력 출력 화면으로 전환
    }
	
	@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    //일정 삭제 시 작동하는 함수
	@GetMapping("/delete/{id}")
    public String deleteCalendar(@PathVariable("id") Integer calId,
			Principal principal) {
		Calendar calendar = this.calService.getInfo(calId);
		
		String adminAut = userService.getUser(principal.getName()).getAdminAut();
		if((!calendar.getCalAuthor().getUsername().equals(principal.getName())) && (!adminAut.equals("관리자")))/*사용자의 학번과 작성자의 학번 비교)*/ {
			return String.format("redirect:/calendar/%s/main", calendar.getCalStartDay()	); //달력 출력 화면으로 전환 
        }
		LocalDate back = calendar.getCalStartDay();
		this.calService.delete(calendar);
		return String.format("redirect:/calendar/%s/main",back);
    }
}
