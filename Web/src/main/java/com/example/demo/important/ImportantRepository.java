package com.example.demo.important;

import org.springframework.data.jpa.repository.JpaRepository;
//import com.example.demo.table.UserInfo;
import com.example.demo.table.Important;
import java.util.List;

public interface ImportantRepository extends JpaRepository<Important, Integer> {
//	Important findByImpor_Id(Integer Impor_Id);
//	Important findBytitleandtext(String title, String text);
//	List<Important> findBytitleLike(String title);
	
}