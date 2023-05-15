package com.Calender;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Table.Calender;

public interface CalenderRepository extends JpaRepository<Calender, Integer>{
	
}