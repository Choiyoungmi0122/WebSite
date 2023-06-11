package com.example.demo.calender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.table.Calender;
import com.example.demo.table.UserInfo;


import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Service
@Setter
public class CalenderService {
	private final CalenderRepository calenderRepository = null;
	
	//Calender DB에 값 추가 함수
	public void AddCalenderData(UserInfo userInfo, LocalDateTime Calender_Register, 
			String Calender_Text,LocalDateTime Calender_Deadline, 
			String Calender_Category,String Calender_Replay) {
		Calender calender = new Calender();
		calender.setuserinfo(userInfo);
		calender.setRegister(Calender_Register);
		calender.setText(Calender_Text);
		calender.setDeadline(Calender_Deadline);
		//calender.setCalender_Category(Calender_Category);
		//calender.setCalender_Replay(Calender_Replay);
		this.calenderRepository.save(calender);
	}
	
	public void ModifyCalenderData(Calender calender, LocalDateTime register,String Calender_Text,
			LocalDateTime Calender_Deadline, String Calender_Category,String Calender_Replay) {
		calender.setRegister(register);
		calender.setText(Calender_Text);
		calender.setDeadline(Calender_Deadline);
		//calender.setCategory(Calender_Category);
		//calender.setReplay(Calender_Replay);
	}
	
	public Calender getCalender(Integer id) {
		Optional<Calender> calender = this.calenderRepository.findById(id);
		if(calender.isPresent())
			return calender.get();
		else
			return null;
	}
}
