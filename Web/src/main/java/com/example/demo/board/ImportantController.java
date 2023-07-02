package com.example.demo.board;

import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/Important")
@RequiredArgsConstructor
@Controller
public class ImportantController {
	
	
	private final ImportantService importantService;
//	
//	public ImportantController(ImportantService importantService) {
//		this.importantService = importantService;
//	}
//	
	//공지사항 첫화면 
	@GetMapping("/Main")
	public String list(Model model) {
		List<Important> importanteMain = this.importantService.getList();
		model.addAttribute("importanteMain", importanteMain);
		return "ImportantMain";
	}

	//글쓰기 부분 들어가기
	@GetMapping("/Input")
    public String importantInput(ImportantForm importantForm) {
        return "ImportantInput";
    }
	
	//글쓰기 저장 
	@PostMapping("/Input")
	public String importantInput(@RequestParam String Impor_Title, @RequestParam String Impor_Text) {
		this.importantService.Input(Impor_Title, Impor_Text);
		return "redirect:/Important/Main";
	}
	
	//객체 템플릿 전달  
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Important important = this.importantService.getImportant(id);
		model.addAttribute("important", important);
		return "important_detail";
	}
	
}