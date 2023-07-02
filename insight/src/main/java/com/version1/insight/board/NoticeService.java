package com.version1.insight.board;

import java.util.List;
import com.version1.insight.DataNotFoundException;
import com.version1.insight.user.UserInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;
	
	public List<Notice> getList(){
		return this.noticeRepository.findAll();
	}
	
	public Notice getNotice(Integer noticeId) {  
        Optional<Notice> notice = this.noticeRepository.findById(noticeId);
        if (notice.isPresent()) {
            return notice.get();
        } else {
            throw new DataNotFoundException("notice not found");
        }
    }
	
	public void create(String noticeTitle, String noticeText, UserInfo user) {
		Notice q = new Notice();
        q.setNoticeTitle(noticeTitle);
        q.setNoticeText(noticeText);
        q.setNoticeRegister(LocalDateTime.now());
        q.setNoticeAuthor(user);
        this.noticeRepository.save(q);
    }
	
	public Page<Notice> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("noticeRegister"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.noticeRepository.findAll(pageable);
    }
	
	public void modify(Notice notice, String noticeTitle, String noticeText) {
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeText(noticeText);
        notice.setNoticeModifyRegister(LocalDateTime.now());
        this.noticeRepository.save(notice);
    }
	
	public void delete(Notice notice) {
        this.noticeRepository.delete(notice);
    }
}
