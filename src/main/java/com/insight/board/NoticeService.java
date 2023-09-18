package com.insight.board;

import com.insight.DataNotFoundException;
import com.insight.user.UserInfo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;
	private final NoticeFileRepositoty noticeFileRepository;
	
	private Specification<Notice> search(String kw, String ca){
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Notice> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거 
                Join<Notice, UserInfo> u1 = q.join("noticeAuthor", JoinType.LEFT);
                Join<Notice, NoticeComment> a = q.join("commentList", JoinType.LEFT);
                Join<NoticeComment, UserInfo> u2 = a.join("cmAuthor", JoinType.LEFT);
                Predicate predicate= cb.or(cb.like(q.get("noticeTitle"), "%" + kw + "%"), // 제목 
                        cb.like(q.get("noticeText"), "%" + kw + "%"),      // 내용 
                        cb.like(u1.get("studentName"), "%" + kw + "%"),    // 질문 작성자 
                        cb.like(a.get("cmText"), "%" + kw + "%"),      // 답변 내용 
                        cb.like(u2.get("studentName"), "%" + kw + "%")	// 답변 작성자 
                        );
        		predicate = cb.and(
                        predicate,
                        cb.like(q.get("noticeCategory"), "%" + ca + "%") // 카테고리 필터링
        				);
        		return predicate;    
            }
        };
	}
	
	public List<Notice> getList(){
		return this.noticeRepository.findAll();
	}
	
	public Notice getNotice(Integer noticeId) {  
        Optional<Notice> notice = this.noticeRepository.findById(noticeId);
        if (notice.isPresent()) {
        	Notice noticeview = notice.get();
        	noticeview.setNoticeView(noticeview.getNoticeView()+1);
        	return this.noticeRepository.save(noticeview);
        } else {
            throw new DataNotFoundException("notice not found");
        }
    }

	public void create(NoticeDTO noticeDTO, UserInfo userInfo) throws IOException{
		if(noticeDTO.getNoticeFile().isEmpty()) {
			Notice notice = Notice.toSaveEntity(noticeDTO, userInfo);
			noticeRepository.save(notice);
		}else {
			// 첨부 파일 있음.
            /*
                1. DTO에 담긴 파일을 꺼냄
                2. 파일의 이름 가져옴
                3. 서버 저장용 이름을 만듦
                // 내사진.jpg => 839798375892_내사진.jpg
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. board_table에 해당 데이터 save 처리
                7. board_file_table에 해당 데이터 save 처리
             */
			MultipartFile noticeFile = noticeDTO.getNoticeFile();
			String originalFileName = noticeFile.getOriginalFilename();
			String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
			String savePath = "C:\\Users\\kimbo\\image\\" + storedFileName;
			noticeFile.transferTo(new File(savePath));
			
			Notice notice = Notice.toSaveFileEntity(noticeDTO, userInfo);
			Integer saveId = noticeRepository.save(notice).getNoticeId();
			Notice noticeEntity = noticeRepository.findById(saveId).get();
			
			NoticeFileEntity noticeFileEntity = NoticeFileEntity.toNoticeFileEntity(noticeEntity, originalFileName, storedFileName);
			noticeFileRepository.save(noticeFileEntity);
			
		}
	}
	/*
	public void create(String noticeTitle, String noticeText, UserInfo user, String noticeCategory) {
		Notice q = new Notice();
        q.setNoticeTitle(noticeTitle);
        q.setNoticeText(noticeText);
        q.setNoticeRegister(LocalDateTime.now());
        q.setNoticeAuthor(user);
        q.setNoticeCategory(noticeCategory);
        this.noticeRepository.save(q);
    }
	*/
	
	public NoticeDTO findById(Integer id) {
		Optional<Notice> optionalNotice = noticeRepository.findById(id);
		if(optionalNotice.isPresent()) {
			Notice notice = optionalNotice.get();
			NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(notice);
			return noticeDTO;
		}else {
			return null;
		}
	}

	
	public Page<Notice> getList(int page, String kw, String ca) {
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("noticeRegister"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Notice> spec = search(kw, ca);
        return this.noticeRepository.findAll(spec, pageable);
    }
	
	public void modify(Notice notice, String noticeTitle, String noticeText, String noticeCategory) {
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeText(noticeText);
        notice.setNoticeCategory(noticeCategory);
        notice.setNoticeModifyRegister(LocalDateTime.now());
        this.noticeRepository.save(notice);
    }
	
	public void delete(Notice notice) {
        this.noticeRepository.delete(notice);
    }
}
