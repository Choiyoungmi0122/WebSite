package com.example.demo.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportantRepository extends JpaRepository<Important, Integer> {
//    Important findBytitleandtext(String title, String text);
    // List<Important> findBytitleLike(String title);
    Important findByImpoTitle(String title);
}