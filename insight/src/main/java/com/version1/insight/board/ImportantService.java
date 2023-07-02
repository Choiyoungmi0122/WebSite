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
public class ImportantService {

	private final ImportantRepository importantRepository;
	
	public List<Important> getList(){
		return this.importantRepository.findAll();
	}
	
	public Important getImportant(Integer impoId) {  
        Optional<Important> important = this.importantRepository.findById(impoId);
        if (important.isPresent()) {
            return important.get();
        } else {
            throw new DataNotFoundException("important not found");
        }
    }
	
	public void create(String impoTitle, String impoText, UserInfo user) {
		Important q = new Important();
        q.setImpoTitle(impoTitle);
        q.setImpoText(impoText);
        q.setImpoRegister(LocalDateTime.now());
        q.setImpoAuthor(user);
        this.importantRepository.save(q);
    }
	
	public Page<Important> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("impoRegister"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.importantRepository.findAll(pageable);
    }
	
	public void modify(Important important, String impoTitle, String impoText) {
		important.setImpoTitle(impoTitle);
		important.setImpoText(impoText);
		important.setImpoModifyRegister(LocalDateTime.now());
        this.importantRepository.save(important);
    }
	
	public void delete(Important important) {
        this.importantRepository.delete(important);
    }
}
