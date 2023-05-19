package com.example.demo.table;


import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Calender {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Calender_Id;
	
	@ManyToOne
	@JoinColumn(name="Student_Id")
	private UserInfo userinfo;
	
	
	private LocalDateTime Calender_Register;
	private String Calender_Text;
	private LocalDateTime Calender_Deadline;
	private String Calender_Category;
	private String Calender_Replay;
	
	@OneToOne(mappedBy = "Calender")
	private Schedule schedule;
	
	public Object getUserInfo() {
		// TODO Auto-generated method stub
		return userinfo;
	}
}
