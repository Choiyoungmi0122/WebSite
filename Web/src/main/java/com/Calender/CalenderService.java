package com.Calender;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.Table.Calender;
import com.example.demo.Table.UserInfo;

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
}
