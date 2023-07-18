package com.insight.calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CalendarRepository extends JpaRepository<Calendar, Integer>{
	List<Calendar> findByCalStartDay(LocalDate register);
	Page<Calendar> findByCalStartDay(LocalDate register, Pageable pageable);
}