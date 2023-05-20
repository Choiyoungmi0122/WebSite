package com.example.demo.table;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Important {
	private Long Student_Id;		//작성자 pk
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Impor_Id;
	
    @Column(length = 200)	//제목 
    private String Impor_Title;
    
    @Column(columnDefinition = "TEXT")   //내용 
    private String Impor_Text;
    private LocalDateTime Impor_Register;	//작성일시 
    private Boolean Important;		//필독 여부 
	
}
