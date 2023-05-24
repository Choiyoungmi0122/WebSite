package com.example.demo.notice;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.table.Notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NoticeController {

	// final 클래스 : 더이상 수정 불가
	private final NoticeService noticeservice;
	
	@GetMapping("/NoticeMain/list")
	public String list(Model model) {
		List<Notice> noticeMain = this.noticeservice.getList();
		model.addAttribute("noticeMain", noticeMain);
		return "NoticeMain";
	}
	
	@GetMapping(value = "/NoticeMain/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Notice notice = this.noticeservice.getNotice(id);
		model.addAttribute("notice", notice);
		// Model addAttribute(String name, Object value)
		// value 객체를 name 이름으로 추가한다.
		return "NoticeMain_detail";
	}
}