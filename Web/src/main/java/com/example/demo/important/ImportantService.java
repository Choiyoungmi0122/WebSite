package com.example.demo.important;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.example.demo.share.DataNotFoundException;

@Service
public class ImportantService{
	private final ImportantRepository importantRepository;
	
	public ImportantService(ImportantRepository importantRepository) {
        this.importantRepository = importantRepository;
    }
	
	public List<Important> getList(){
		return this.importantRepository.findAll();
	}
	
	
	public Important getImportant(Integer id) {
		Optional<Important> impor = this.importantRepository.findById(id);
		
		if(impor.isPresent())
			return impor.get();
		else
			throw new DataNotFoundException("Not Found!");
	}
	
	public void Input(String Impor_Title, String Impor_Text) {
	    Important im = new Important();
	    im.setImpoTitle(Impor_Title);
	    im.setImpoText(Impor_Text);
	    im.setImpoRegister(LocalDateTime.now());
//	    im.setImportant(true); // 필독 여부 설정 (원하는 값으로 수정)

	    this.importantRepository.save(im);
	}

}