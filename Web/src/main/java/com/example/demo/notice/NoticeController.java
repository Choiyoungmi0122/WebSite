package com.example.demo.notice;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.table.Notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/")
@RequiredArgsConstructor
@Controller
public class NoticeController {
	
	private final NoticeService noticeService;

	@GetMapping("/NoticeMain")
	public String noticemain(Model model) {
		List<Notice> noticeMain = this.noticeService.getList();
		model.addAttribute("noticeMain", noticeMain);
		 
		return "NoticeMain";
	}

	@GetMapping(value = "/NoticeMain/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Notice notice = this.noticeService.getNotice(id);
		model.addAttribute("notice", notice);
		// Model addAttribute(String name, Object value)
		// value 객체를 name 이름으로 추가한다.
		return "NoticeMain_detail";
	}
	
	@GetMapping("/NoticeInput")
    public String noticeinput() {
        return "NoticeInput";
    }
}