package com.example.demo.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserInfo {
//	@Column(name="number")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long number;
	
   @Id
   @Column(name="Student_Id", unique = true)
   private String id;
   
   @Column(name="Student_Name")
   private String name;
   
   @Column(name="Pwd")
   private String pwd;
   
   @Column(name="Email", unique = true)
   private String email;
  
}
