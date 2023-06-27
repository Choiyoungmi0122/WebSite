package com.example.demo.user;

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
   @Column(unique = true)
   private String usId;
   
   private String usName;
   
   private String usPwd;
   
   @Column(unique = true)
   private String usEmail;
  
}
