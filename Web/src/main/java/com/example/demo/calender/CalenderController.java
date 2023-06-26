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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.table.Calender;
import com.example.demo.table.UserInfo;

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
    		Principal principal) {
        calService.addData(/*principal.getName()*/"20212874", LocalDate.parse(calRegister), calText);
        return "CalenderMain";
    }
	/*
    public String createAnswer(@Valid CalenderRegisteForm calenderRegisterForm, BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
            return "question_form";
        }
        calService.addData(principal.getName()"20212874", calenderRegisterForm.getCalRegister(), calenderRegisterForm.getCalText());
        return "CalenderMain";
    }
	*/
	/*
	@GetMapping("/modify/{id}")
	public String modify(Model model, CalenderRegisteForm calenderRegisterForm, @PathVariable Integer id) {
		//매개변수를 CalenderForm, 일정의 id, 사용자의 정보로 한다.
		Calender cal = this.calService.getInfo(id);
		model.addAttribute("calender",cal);
		
		calenderRegisterForm.setCalRegister(cal.getRegister());
		calenderRegisterForm.setCalText(cal.getText());
		return "CalenderModify";
	}
	
	@PostMapping("/modify/{id}")
	public String modify(@Valid CalenderRegisteForm calenderRegisterForm, BindingResult br, 
            Principal p, @PathVariable Integer id) {
        if (br.hasErrors()) {
            return "CalenderModify";
        }
        Calender cal = this.calService.getCalender(id);
        this.calService.modify(cal, calenderRegisterForm.getCalRegister(), calenderRegisterForm.getCalText());
        return "redirect:/calender";
    }
	*/
	/*
	//http://8080/calender/create
	//일정 등록 html에서 등록 버튼 눌렀을 때 저장하는 함수
	//*역할조건 안넣음 @PreAuthorize
	@PostMapping("/create")
	public String create(//입력 값들
			@Valid CalenderRegisteForm crf, BindingResult bindingResult,
			Principal principal){

		//BindingResult는 입력을 했는지 확인위해 추가
		//@RequestParam("가져올 데이터의 이름") [데이터타입] [가져온데이터를 담을 변수]
		
		if (bindingResult.hasErrors()) {//Calender 등록창에서 제목입력 안하면 작동
            return "/create";
        }
		
		this.calService.addData(crf.getRegister(), crf.getText());

		return "redirect:/calender/{day}"; 
	}
	*/
	
	/*
	
	//http://8080/calender/modify/{id}
	//일정 수정 html에서 DB에 저장된 값 불러오는 함수
	//역할조건 안넣음 @PreAuthorize
	@GetMapping("/modify/{id}")
	public void modifyLoad(CalenderRegisteForm calenderRegisteForm, @PathVariable("id") Integer Calender_Id,
			Principal principal) {
		//매개변수를 CalenderForm, 일정의 id, 사용자의 정보로 한다.
		Calender calender = this.calService.getInfo(Calender_Id);
		
		if(!calender.getUserInfo().equals(principal.getName()))) {//사용자의 학번과 작성자의 학번 비교
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

		calenderRegisteForm.setRegister(calender.getRegister());
		calenderRegisteForm.setText(calender.getText());
	}
	

	//일정 수정 페이지에서 일정을 수정후 확인 버튼을 누르면 작동하는 함수
	//역할조건 안넣음 @PreAuthorize
    //@PostMapping("/modify/{id}")
    public String modifyCalender(@Valid @RequestBody CalenderRegisteForm calenderRegisteForm, 
    		BindingResult bindingResult, Principal principal, @PathVariable("id") Integer Calender_Id) {
        if (bindingResult.hasErrors()) {
            return "캘린더수정html";
        }
        Calender calender = this.calService.getInfo(Calender_Id);
        
        this.calService.modifyData(calender, calenderRegisteForm.getRegister(),
        		calenderRegisteForm.getText());

        return ("redirect:/calender"); //달력 출력 화면으로 전환
    }
    
    
    */
    
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
