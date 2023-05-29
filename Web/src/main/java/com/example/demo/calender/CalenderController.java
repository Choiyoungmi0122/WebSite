package com.example.demo.calender;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.table.Calender;
import com.example.demo.table.UserInfo;

@RequestMapping("/calender")
@RequiredArgsConstructor
@Controller

public class CalenderController {
	//@RequiredArgsConstructor 사용으로 생성자 주입
	private final CalenderRepository calenderRepository;
	private final CalenderService calenderService;
	//private final UserInfoRepository userInfoRepository;
	
	@GetMapping("/calender")
    public String index() {
        return "CalenderMain";
    }
	
	//http://8080/calender/create
	//일정 등록 페이지에서 입력하고 등록 버튼 누르면 작동하는 함수
	//*역할조건 안넣음 @PreAuthorize
	@PostMapping("/create")
	public String CreateCalender(//입력 값들
			@Valid CalenderRegisteForm calenderRegisteForm, BindingResult bindingResult, 
			@RequestParam UserInfo userinfo, @RequestParam("text") String text, 
			@RequestParam("register") LocalDateTime register, 
			@RequestParam("deadline") LocalDateTime deadline, 
			@RequestParam("category") String category,
			@RequestParam("replay") String replay) {
		//BindingResult는 입력을 했는지 확인위해 추가
		//@RequestParam("가져올 데이터의 이름") [데이터타입] [가져온데이터를 담을 변수]
		
		if (bindingResult.hasErrors()) {//Calender 등록창에서 제목입력 안하면 작동
            return "/create";
        }
		
		this.calenderService.AddCalenderData(userinfo, register, text, deadline, category, replay);
		//일정 등록화면에서 문제없다면 calederService에서 AddCalenderData실행해 저장
		return ("redirect:/CalenderMain"); 
		//달력 출력 화면으로 전환
	}
	
	//http://8080/calender/modify/{id}
	//일정 수정 페이지에 들어가면 입력된 값들을 불러오는 함수
	//*역할조건 안넣음 @PreAuthorize
	@GetMapping("/modify/{id}")
	public void ModifyCalender(CalenderRegisteForm calenderRegisteForm, @PathVariable("id") Integer Calender_Id,
			Principal prin) {
		//매개변수를 CalenderForm, 일정의 id, 사용자의 정보로 한다.
		Calender calender = this.calenderService.getCalender(Calender_Id);
		
		if(!calender.getUserInfo().equals(prin.getName()))/*사용자의 학번과 작성자의 학번 비교)*/ {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

		calenderRegisteForm.setRegister(calender.getRegister());
        //calenderRegisteForm의 Calender_Register 변수에 선택한 일정의 등록일 전달
		calenderRegisteForm.setText(calender.getText());
		calenderRegisteForm.setDeadline(calender.getDeadline());
		calenderRegisteForm.setCategory(calender.getCategory());
		calenderRegisteForm.setReplay(calender.getReplay());
        //calenderRegisteForm의 Calender_Replay 변수에 선택한 일정의 반복일 전달
	}
	
	//http://8080/calender/modify/{id}
	//일정 수정 페이지에서 일정을 수정후 확인 버튼을 누르면 작동하는 함수
	//*역할조건 안넣음 @PreAuthorize
    @GetMapping("/modify/{id}")
    public String questionModify(@Valid @RequestBody CalenderRegisteForm calenderRegisteForm, 
    		BindingResult bindingResult, Principal prin, @PathVariable("id") Integer Calender_Id) {
        if (bindingResult.hasErrors()) {
            return "캘린더수정html";
        }
        Calender calender = this.calenderService.getCalender(Calender_Id);
        
        this.calenderService.ModifyCalenderData(calender, calenderRegisteForm.getRegister(),
        		calenderRegisteForm.getText(), calenderRegisteForm.getDeadline(), 
        		calenderRegisteForm.getCategory(),calenderRegisteForm.getReplay());
        //calenderService에서 ModifyCalenderData함수 작동
        return ("redirect:/CalenderMain"); //달력 출력 화면으로 전환
    }
}
