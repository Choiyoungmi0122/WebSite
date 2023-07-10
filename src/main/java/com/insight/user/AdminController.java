package com.insight.user;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.insight.board.Notice;
import com.insight.board.NoticeComment;
import com.insight.board.NoticeCommentForm;
import com.insight.board.NoticeCommentService;
import com.insight.board.NoticeForm;
import com.insight.board.NoticeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final NoticeService noticeService;
	private final UserService userService;
	private final NoticeCommentService noticeCommentService;
	
	@GetMapping("/notice/main/all")
    public String list(Model model, @RequestParam(value="page", defaultValue="1") int page, 
    		@RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Notice> noticeList = this.noticeService.getList(page-1, kw, "");
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("kw", kw);
        model.addAttribute("ca", "all");
        return "question_list";
    }
	
	@GetMapping(value = "/notice/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer noticeId, NoticeCommentForm noticeCommentForm) {
		Notice notice = this.noticeService.getNotice(noticeId);
		model.addAttribute("notice", notice);
        return "question_detail";
    }

	@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/notice/modify/{id}")
    public String noticeModify(Model model, NoticeForm noticeForm, @PathVariable("id") Integer noticeId, Principal principal) {
        Notice notice = this.noticeService.getNotice(noticeId);

//        if(!notice.getNoticeAuthor().getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//        }
        model.addAttribute(notice);
        noticeForm.setNoticeTitle(notice.getNoticeTitle());
        noticeForm.setNoticeText(notice.getNoticeText());
        return "question_form";
    }
	
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/notice/modify/{id}")
    public String noticeModify(@Valid NoticeForm noticeForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer noticeId) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Notice notice = this.noticeService.getNotice(noticeId);
//        if (!notice.getNoticeAuthor().getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//        }
        this.noticeService.modify(notice, noticeForm.getNoticeTitle(), noticeForm.getNoticeText());
        return String.format("redirect:/notice/detail/%s", noticeId);
    }
	
	 @PreAuthorize("hasRole('ADMIN')")
	    @GetMapping("/notice/delete/{id}")
	    public String noticeDelete(Principal principal, @PathVariable("id") Integer noticeId) {
	        Notice notice = this.noticeService.getNotice(noticeId);
//	        if (!notice.getNoticeAuthor().getUsername().equals(principal.getName())) {
//	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//	        }
	        this.noticeService.delete(notice);
	        return "redirect:/notice/main/all";
	    }
	 
	 @PreAuthorize("hasRole('ADMIN')")
	    @GetMapping("/comment/modify/{id}")
	    public String noticeCommentModify(Model model, NoticeCommentForm noticeCommentForm, @PathVariable("id") Integer cmId, Principal principal) {
			NoticeComment noticeComment = this.noticeCommentService.getNoticeComment(cmId);
			model.addAttribute("notice", noticeComment.getNotice());
//	        if (!noticeComment.getCmAuthor().getUsername().equals(principal.getName())) {
//	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//	        }
	        noticeCommentForm.setCmText(noticeComment.getCmText());
	        return "answer_form";
	    }
		
	 @PreAuthorize("hasRole('ADMIN')")
	    @PostMapping("/comment/modify/{id}")
	    public String noticeCommentModify(@Valid NoticeCommentForm noticeCommentForm, BindingResult bindingResult,
	            @PathVariable("id") Integer cmId, Principal principal) {
			
			if (bindingResult.hasErrors()) {
	            return "answer_form";
	        }
	        NoticeComment noticeComment = this.noticeCommentService.getNoticeComment(cmId);
//	        if (!noticeComment.getCmAuthor().getUsername().equals(principal.getName())) {
//	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//	        }
	        this.noticeCommentService.modify(noticeComment, noticeCommentForm.getCmText());
	        return String.format("redirect:/admin/notice/detail/%s#comment_%s", noticeComment.getNotice().getNoticeId(), noticeComment.getCmId());
	    }
		
	 @PreAuthorize("hasRole('ADMIN')")
	    @GetMapping("/comment/delete/{id}")
	    public String noticeCommentDelete(Principal principal, @PathVariable("id") Integer cmId) {
			NoticeComment noticeComment = this.noticeCommentService.getNoticeComment(cmId);
	//	        if (!noticeComment.getCmAuthor().getUsername().equals(principal.getName())) {
	//	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	//	        }
	        this.noticeCommentService.delete(noticeComment);
	        return String.format("redirect:/admin/notice/detail/%s", noticeComment.getNotice().getNoticeId());
	    }
	 
	 @GetMapping("/list")
	 public String userList(Model model) {
		 List<UserInfo> userList = this.userService.getUserList();
		 model.addAttribute("userList",userList);
		 return "user_list";
	 }
	 
	 @GetMapping(value = "/detail/{id}")
	    public String detail(UserModifyForm userModifyForm, Model model, @PathVariable("id") String username,
	    		Principal principal, BindingResult bindingResult) {
	    	UserInfo userInfo = this.userService.getUser(username);
	    	model.addAttribute("username",username);
//	    	if(!userInfo.getUsername().equals(username)) {
//	    		return "redirect:/";
//	    	}
//	    	if (!userModifyForm.getPassword1().equals(userModifyForm.getPassword2())) {
//	            bindingResult.rejectValue("password2", "passwordInCorrect", 
//	                    "2개의 패스워드가 일치하지 않습니다.");
//	            return "signup_form";
//	        }
	    	userModifyForm.setUsername(userInfo.getUsername());
	    	userModifyForm.setStudentName(userInfo.getStudentName());
	    	userModifyForm.setCondition(userInfo.getCondition());
	    	userModifyForm.setDoing(userInfo.getDoing());
	    	userModifyForm.setEmail(userInfo.getEmail());
	    	userModifyForm.setGrade(userInfo.getGrade());
	    	userModifyForm.setIntroduction(userInfo.getIntroduction());
	    	userModifyForm.setMajor(userInfo.getMajor());
	    	userModifyForm.setPassword(userInfo.getPassword());
	    	userModifyForm.setPhoneNumber(userInfo.getPhoneNumber());
	    	userModifyForm.setWantedAct(userInfo.getWantedAct());
	    	return "user_detail";
	    }
}
