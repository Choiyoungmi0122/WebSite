//package com.example.demo.important;
//
//import com.example.demo.important.*;
//import com.example.demo.table.Important;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ImportantService {
//	
//	@Autowired
//	private ImportantRepository importantRepository;
//	
//	public Page<Important> findImporList(Pageable pageable){
//		pageable = PageRequest.of(
//			pageable.getPageNumber() <= 0?0:pageable.getPageNumber()-1,
//					pageable.getPageSize());
//			return importantRepository.findAll(pageable);
//	}
//	
//	public Important findImportantByImpor_Id(Integer Impor_Id) {
//		return importantRepository.findById(Impor_Id).orElse(new Important());
//	}
//}
