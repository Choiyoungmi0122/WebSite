package com.example.demo.calender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
	
	public List<Calender> getList() {
        return this.calenderRepository.findAll();
    }
	
	//Calender DB에 값 추가 함수
	public void AddCalenderData(UserInfo userInfo, LocalDateTime Calender_Register, 
			String Calender_Text) {
		Calender calender = new Calender();
		calender.setuserinfo(userInfo);
		calender.setRegister(Calender_Register);
		calender.setText(Calender_Text);
		this.calenderRepository.save(calender);
	}
	
	public void ModifyCalenderData(Calender calender, LocalDateTime register,String text) {
		calender.setRegister(register);
		calender.setText(text);
	}
	
	public Calender getCalender1(Integer id) {
		Optional<Calender> calender = this.calenderRepository.findById(id);
		if(calender.isPresent())
			return calender.get();
		else
			return null;
	}
	
	public Calender getCalender(Integer id) {
		Optional<Calender> calender = this.calenderRepository.findById(id);
		if(calender.isPresent())
			return calender.get();
		else
			return null;
	}
}
