package com.insight.calendar;

import java.time.LocalDate;

import java.time.LocalTime;

import com.insight.DataNotFoundException;
import com.insight.user.UserInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	
	public Page<Calendar> getListDayPage(LocalDate day, int page){
		Pageable pageable = PageRequest.of(page, 5);
		return this.calRepo.findByCalStartDayLessThanEqualAndCalEndDayGreaterThanEqual(day, day, pageable);
	}
	
	public Calendar getCalendar(Integer id) {  
        Optional<Calendar> question = this.calRepo.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
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
	
	//추가
	public void addData(UserInfo userInfo, String calText, String calStartDay, String calEndDay, String calStartTime, String calEndTime) {
		Calendar calendar = new Calendar();
		calendar.setCalAuthor(userInfo);
		calendar.setCalStartDay(LocalDate.parse(calStartDay));
		if(calEndDay=="")
			calendar.setCalEndDay(LocalDate.parse(calStartDay));
		else	
			calendar.setCalEndDay(LocalDate.parse(calEndDay));
		if(calStartTime != "" && calEndTime=="") {
			calendar.setCalStartTime(LocalTime.parse(calStartTime));
			calendar.setCalEndTime((LocalTime.parse(calStartTime)).plusHours(1));
		}
		else if(calStartTime == ""){
			calendar.setCalStartTime(null);
			calendar.setCalEndTime(null);
		}
		else {
			calendar.setCalStartTime(LocalTime.parse(calStartTime));
			calendar.setCalEndTime(LocalTime.parse(calEndTime));
		}
		calendar.setCalText(calText);
		this.calRepo.save(calendar);
	}
	//수정
	public void modify(Calendar calendar, String calText, String calStartDay,  String calEndDay, String calStartTime, String calEndTime) {
		calendar.setCalStartDay(LocalDate.parse(calStartDay));
		if(calEndDay=="")
			calendar.setCalEndDay(LocalDate.parse(calStartDay));
		else	
			calendar.setCalEndDay(LocalDate.parse(calEndDay));
		if(calStartTime=="")
			calendar.setCalStartTime(null);
		else
			calendar.setCalStartTime(LocalTime.parse(calStartTime));
		if(calEndTime=="")
			calendar.setCalEndTime(null);
		else
			calendar.setCalEndTime(LocalTime.parse(calEndTime));
		calendar.setCalText(calText);
		this.calRepo.save(calendar);
	}
	
	//삭제
	public void delete(Calendar calendar) {
		this.calRepo.delete(calendar);
	}
}
