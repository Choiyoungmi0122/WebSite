package com.insight.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.insight.user.UserInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeId;
    
    @Column(length = 200)
    private String noticeTitle;
    
    @Column(columnDefinition = "TEXT")
    private String noticeText;
    
    private String noticeCategory;
    
    @ManyToOne
	private UserInfo noticeAuthor;
    
    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
	private List<NoticeComment> commentList;
    
    private LocalDateTime noticeRegister;
    
    private LocalDateTime noticeModifyRegister;
    
    @Column(columnDefinition = "integer default 0", nullable = false)	// 조회수의 기본 값을 0으로 지정, null 불가 처리
	private int noticeView;
    
    private Integer noticeHits;
    
    private Integer fileAttached;
    
    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NoticeFileEntity> noticeFileEntityList = new ArrayList<>();
    
    public static Notice toSaveEntity(NoticeDTO noticeDTO, UserInfo userInfo) {
    	Notice notice = new Notice();
    	notice.setNoticeTitle(noticeDTO.getNoticeTitle());
    	notice.setNoticeText(noticeDTO.getNoticeText());
    	notice.setNoticeCategory(noticeDTO.getNoticeCategory());
    	notice.setNoticeAuthor(userInfo);
    	notice.setNoticeRegister(noticeDTO.getNoticeRegister());
    	notice.setNoticeHits(0);
    	notice.setFileAttached(0);
    	return notice;
    }
    
    public static Notice toUpdateEntity(NoticeDTO noticeDTO) {
    	Notice notice = new Notice();
    	notice.setNoticeId(noticeDTO.getNoticeId());
    	notice.setNoticeTitle(noticeDTO.getNoticeTitle());
    	notice.setNoticeText(noticeDTO.getNoticeText());
    	notice.setNoticeCategory(noticeDTO.getNoticeCategory());
    	notice.setNoticeModifyRegister(noticeDTO.getNoticeModifyRegister());
    	notice.setNoticeHits(noticeDTO.getNoticeHits());
    	return notice;
    }
    
    public static Notice toSaveFileEntity(NoticeDTO noticeDTO, UserInfo userInfo) {
    	Notice notice = new Notice();
    	notice.setNoticeTitle(noticeDTO.getNoticeTitle());
    	notice.setNoticeText(noticeDTO.getNoticeText());
    	notice.setNoticeCategory(noticeDTO.getNoticeCategory());
    	notice.setNoticeAuthor(userInfo);
    	notice.setNoticeRegister(noticeDTO.getNoticeRegister());
    	notice.setNoticeHits(0);
    	notice.setFileAttached(1);
    	return notice;
    }
}
