package com.example.demo.table;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Important {
	
	@JoinColumn(name="Student_Id")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserInfo userinfo;		// 작성자 pk
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Impor_Id")
	private Integer Impor_Id;
	
    @Column(name="Impor_Title", length = 200)	// 제목 
    private String title;
    
    @Column(name="Impor_Text", columnDefinition = "TEXT")   // 내용 
    private String text;
    
    @Column(name="Impor_Register")
    private LocalDateTime register;	// 작성일시
    
    @Column(name="Important")
    private Boolean important;		// 필독 여부 

}