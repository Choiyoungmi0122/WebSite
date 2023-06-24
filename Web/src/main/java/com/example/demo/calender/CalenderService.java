package com.example.demo.calender;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.table.Calender;
import com.example.demo.table.UserInfo;
import com.example.demo.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Service
@Setter
public class CalenderService {
	private final CalenderRepository calRepo = null;
	
	public List<Calender> getDayList(String register) {
        return this.calRepo.findByRegister(LocalDate.parse(register));
    }

	
	//추가
	public void addData(LocalDate Calender_Register, 
			String Calender_Text) {
		Calender calender = new Calender();
		calender.setRegister(Calender_Register);
		calender.setText(Calender_Text);
		this.calRepo.save(calender);
	}
	//수정
	public void modifyData(Calender calender, LocalDate register,
			String text) {
		calender.setRegister(register);
		calender.setText(text);
		this.calRepo.save(calender);
	}
	//불러오기
	public Calender getInfo(Integer id) {
		Optional<Calender> calender = this.calRepo.findById(id);
		if(calender.isPresent())
			return calender.get();
		else
			return null;
	}
	
	//삭제
	public void delete(Calender calender) {
		this.calRepo.delete(calender);
	}
}
