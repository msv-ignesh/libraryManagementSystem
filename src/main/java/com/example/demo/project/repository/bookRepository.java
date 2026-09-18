package com.example.demo.project.repository;

import com.example.demo.project.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bookRepository extends JpaRepository<Book, Long> {
    
    
}
