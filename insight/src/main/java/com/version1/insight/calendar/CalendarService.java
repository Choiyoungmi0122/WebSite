package com.version1.insight.calendar;

import java.time.LocalDate;

import com.version1.insight.DataNotFoundException;
import com.version1.insight.user.UserInfo;
import com.version1.insight.user.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Service
@Setter
public class CalendarService {
	private final CalendarRepository calRepo;
	
	public List<Calendar> getDayList(LocalDate register) {
        return this.calRepo.findByCalStartDay(register);
    }
	public Calendar getCalendar(Integer id) {  
        Optional<Calendar> question = this.calRepo.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

	
	//추가
	public void addData(UserInfo userInfo, String calStartDay, String calText) {
		Calendar calendar = new Calendar();
		calendar.setCalAuthor(userInfo);
		calendar.setCalStartDay(LocalDate.parse(calStartDay));
		calendar.setCalText(calText);
		this.calRepo.save(calendar);
	}
	//수정
	public void modify(Calendar calendar, String calStartDay, String calText) {
		calendar.setCalStartDay(LocalDate.parse(calStartDay));
		calendar.setCalText(calText);
		this.calRepo.save(calendar);
	}
	//불러오기
	public Calendar getInfo(Integer calId) {
		Optional<Calendar> calendar = this.calRepo.findById(calId);
		if(calendar.isPresent())
			return calendar.get();
		else{
            throw new DataNotFoundException("notice not found");
        }
	}
	
	//삭제
	public void delete(Calendar calendar) {
		this.calRepo.delete(calendar);
	}
}
