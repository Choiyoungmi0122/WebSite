package com.example.demo.table;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Important {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Impor_Id;
	
	@ManyToOne
	@JoinColumn(name="STUNDENT_ID")  //작성자 pk
	private Userinfo userinfo;
	
	
    @Column(length = 200)	//제목 
    private String Impor_Title;
    
    @Column(columnDefinition = "TEXT")   //내용 
    private String Impor_Text;
    
    private LocalDateTime Impor_Register;	//작성일시 
    private Boolean Important;		//필독 여부
    
    //충돌나버림  
	
}
