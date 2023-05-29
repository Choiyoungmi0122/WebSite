package com.example.demo.table;


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
	@Column(name="Schedule_Id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="Calender_Id")
	private Calender calender;
	
	@ManyToOne
	@JoinColumn(name="Student_Id")
	private UserInfo userInfo;

}
