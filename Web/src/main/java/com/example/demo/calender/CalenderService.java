package com.example.demo.calender;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.share.DataNotFoundException;
import com.example.demo.table.Calender;
import com.example.demo.table.UserInfo;
import com.example.demo.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Service
@Setter
public class CalenderService {
	private final CalenderRepository calRepo;
	private final UserRepository userRepo;
	
	public List<Calender> getDayList(LocalDate register) {
        return this.calRepo.findByRegister(register);
    }
	public Optional<UserInfo> getUserInfo(String Id){
		return this.userRepo.findById(Long.parseLong(Id));
	}
	public Calender getCalender(Integer id) {  
        Optional<Calender> question = this.calRepo.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

	
	//추가
	public void addData(String Id, LocalDate Calender_Register, String Calender_Text) {
		Calender calender = new Calender();
		Optional<UserInfo> userId = getUserInfo(Id);
		calender.setUserinfo(userId.get());
		calender.setRegister(Calender_Register);
		calender.setText(Calender_Text);
		this.calRepo.save(calender);
	}
	//수정
	public void modify(Calender calender, LocalDate register,
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
