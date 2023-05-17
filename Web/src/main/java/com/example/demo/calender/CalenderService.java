package com.example.demo.calender;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.table.Calender;
import com.example.demo.table.UserInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CalenderService {
	private final CalenderRepository calenderRepository;
	
	//Calender DB에 값 추가 함수
	public void AddCalenderData(UserInfo userInfo, LocalDateTime Calender_Register, 
			String Calender_Text,LocalDateTime Calender_Deadline, 
			String Calender_Category,String Calender_Replay) {
		Calender calender = new Calender();
		calender.setUserinfo(userInfo);
		calender.setCalender_Register(Calender_Register);
		calender.setCalender_Text(Calender_Text);
		calender.setCalender_Deadline(Calender_Deadline);
		//calender.setCalender_Category(Calender_Category);
		//calender.setCalender_Replay(Calender_Replay);
		this.calenderRepository.save(calender);
	}
	
	public void ModifyCalenderData(Calender calender, LocalDateTime Calender_Register,String Calender_Text,
			LocalDateTime Calender_Deadline, String Calender_Category,String Calender_Replay) {
		calender.setCalender_Register(Calender_Register);
		calender.setCalender_Text(Calender_Text);
		calender.setCalender_Deadline(Calender_Deadline);
		calender.setCalender_Category(Calender_Category);
		calender.setCalender_Replay(Calender_Replay);
	}
	
	public Calender getCalender(Integer Calender_Id) {
		Optional<Calender> calender = this.calenderRepository.findById(Calender_Id);
		if(calender.isPresent())
			return calender.get();
		else
			return null;
	}
}
