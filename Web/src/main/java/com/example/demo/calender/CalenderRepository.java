package com.example.demo.calender;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.table.Calender;

@Repository
public interface CalenderRepository extends JpaRepository<Calender, Integer>{
}