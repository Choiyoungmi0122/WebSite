package com.example.demo.notice;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.table.Notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;


@RequestMapping("/Notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping("/main")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Notice> noticeMain = this.noticeService.getList(page);
		model.addAttribute("noticeMain", noticeMain);

		return "notice/NoticeMain";
	}

	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Notice notice = this.noticeService.getNotice(id);
		model.addAttribute("notice", notice);
		// Model addAttribute(String name, Object value)
		// value 객체를 name 이름으로 추가한다.
		return "notice/NoticeDetail";
	}

	@GetMapping("/input")
	public String noticeinput() {
		return "notice/NoticeInput";
	}

	 @PostMapping("/input")
	 public String noticeinput(@Valid NoticeForm noticeForm, BindingResult bindingResult)
	 {
		 if (bindingResult.hasErrors()) {
	            return "notice/NoticeInput";
	        }
		 this.noticeService.noticeinput(noticeForm.getTitle(), noticeForm.getNumber(), noticeForm.getText());
		 return "redirect:/Notice/main"; // 질문 저장후 질문목록으로이동
	 }

}