package com.example.demo.calender;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.table.Calender;


public interface CalenderRepository extends JpaRepository<Calender, Integer>{
	public List<Calender> findByRegister(LocalDate register);
}