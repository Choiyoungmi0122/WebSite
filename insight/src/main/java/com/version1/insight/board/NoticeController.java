package com.version1.insight.board;

import java.util.List;
import java.security.Principal;

import com.version1.insight.user.UserInfo;
import com.version1.insight.user.UserService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {
	
	private final NoticeService noticeService;
	private final UserService userService;
	
	@GetMapping("/main")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Notice> noticeList = this.noticeService.getList(page);
        model.addAttribute("noticeList", noticeList);
        return "question_list";
    }
	
	@GetMapping("/main/free")
	public String freelist(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Notice> noticeList = this.noticeService.getCategory("free", page);
		model.addAttribute("noticeList", noticeList);
		return "question_list";
	}
	
	@GetMapping("/main/coding")
	public String codinglist(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Notice> noticeList = this.noticeService.getCategory("coding", page);
		model.addAttribute("noticeList", noticeList);
		return "question_list";
	}
	
	@GetMapping("/main/gallery")
	public String gallerylist(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Notice> noticeList = this.noticeService.getCategory("gallery", page);
		model.addAttribute("noticeList", noticeList);
		return "question_list";
	}
	
	@GetMapping("/main/contest")
	public String contestlist(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Notice> noticeList = this.noticeService.getCategory("contest", page);
		model.addAttribute("noticeList", noticeList);
		return "question_list";
	}
	
	@GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer noticeId, NoticeCommentForm noticeCommentForm) {
		Notice notice = this.noticeService.getNotice(noticeId);
		model.addAttribute("notice", notice);
        return "question_detail";
    }
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
    public String noticeCreate(NoticeForm noticeForm) {
        return "question_form";
    }
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
    public String noticeCreate(@Valid NoticeForm noticeForm, BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
            return "question_form";
        }
		UserInfo userInfo = this.userService.getUser(principal.getName());
		this.noticeService.create(noticeForm.getNoticeTitle(), noticeForm.getNoticeText(), userInfo, noticeForm.getNoticeCategory());
		return "redirect:/notice/main";
    }

	@PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String noticeModify(NoticeForm noticeForm, @PathVariable("id") Integer noticeId, Principal principal) {
        Notice notice = this.noticeService.getNotice(noticeId);
        if(!notice.getNoticeAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        noticeForm.setNoticeTitle(notice.getNoticeTitle());
        noticeForm.setNoticeText(notice.getNoticeText());
        return "question_form";
    }
	
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String noticeModify(@Valid NoticeForm noticeForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer noticeId) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Notice notice = this.noticeService.getNotice(noticeId);
        if (!notice.getNoticeAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.noticeService.modify(notice, noticeForm.getNoticeTitle(), noticeForm.getNoticeText());
        return String.format("redirect:/notice/detail/%s", noticeId);
    }
	
	 @PreAuthorize("isAuthenticated()")
	    @GetMapping("/delete/{id}")
	    public String noticeDelete(Principal principal, @PathVariable("id") Integer noticeId) {
	        Notice notice = this.noticeService.getNotice(noticeId);
	        if (!notice.getNoticeAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	        }
	        this.noticeService.delete(notice);
	        return "redirect:/notice/main";
	    }
}
