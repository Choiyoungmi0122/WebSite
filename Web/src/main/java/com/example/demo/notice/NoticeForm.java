package com.example.demo.notice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NoticeForm {
    @Size(max=1000)
    private String title;

    private String number;
    
    private String text;
}
