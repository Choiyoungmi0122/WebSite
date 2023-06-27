package com.example.demo.notice.comment;

import com.example.demo.notice.*;
import com.example.demo.table.*;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;

@RequestMapping("/Notice/comment")
@RequiredArgsConstructor
@Controller
public class NoticeCommentController {

	private final NoticeService noticeService;
	private final NoticeCommentService noticeCommentService;
	
	@PostMapping("/input/{id}")
	public String inputComment(Model model, @PathVariable("id") Integer id, @Valid NoticeCommentForm noticecommentForm, BindingResult bindingResult) {
		Notice notice = this.noticeService.getNotice(id);
		if (bindingResult.hasErrors()) {
			model.addAttribute("notice", notice);
			return "notice/NoticeDetail";
		}
		this.noticeCommentService.noticecommentinput(notice, noticecommentForm.getText());
		return String.format("redirect:/Notice/detail/%s", id);
	}
}
