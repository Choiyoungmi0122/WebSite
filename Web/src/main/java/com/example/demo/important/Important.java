package com.example.demo.important;

import java.time.LocalDateTime;

import com.example.demo.user.UserInfo;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserInfo userinfo;		// 작성자 pk
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer impoId;
	
    @Column( length = 200)	// 제목 
    private String impoTitle;
    
    @Column(columnDefinition = "TEXT")   // 내용 
    private String impoText;
    
    private LocalDateTime impoRegister;	// 작성일시
    
    private Boolean impoTf;		// 필독 여부 

}
