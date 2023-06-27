package com.example.demo.board;

import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NoticeForm {
    @Size(max=1000)
    private String noticeTitle;

    private String noticeNumber;
    
    private String noticeText;
}
