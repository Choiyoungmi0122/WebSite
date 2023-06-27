package com.example.demo.important;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportantRepository extends JpaRepository<Important, Integer> {
//    Important findByImpor_Id(Integer Impor_Id);
//    Important findBytitleandtext(String title, String text);
    // List<Important> findBytitleLike(String title);
    Important findByImpoTitle(String title);
}