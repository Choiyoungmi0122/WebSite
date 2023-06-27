package com.example.demo.calender;


import com.example.demo.user.UserInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer calId;
	
	@ManyToOne
	private Calender calCalender;
	
	@ManyToOne
	private UserInfo userInfo;

}
