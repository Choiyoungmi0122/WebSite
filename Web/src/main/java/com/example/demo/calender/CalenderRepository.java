package com.example.demo.calender;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CalenderRepository extends JpaRepository<Calender, Integer>{
	List<Calender> findByRegister(LocalDate register);
}