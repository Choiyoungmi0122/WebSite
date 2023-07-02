
package com.example.demo.calender;


import java.time.LocalDate;

import com.example.demo.user.UserInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Calender {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer calId;
	
	@ManyToOne
	private UserInfo userinfo;

	private LocalDate calRegister;
	
	private String calText;


}

