package com.example.demo.calender;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.table.Calender;

public interface CalenderRepository extends JpaRepository<Calender, Integer>{
	
}