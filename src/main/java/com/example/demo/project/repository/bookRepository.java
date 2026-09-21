package com.example.demo.project.repository;

import com.example.demo.project.model.Book;
import com.example.demo.project.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bookRepository extends JpaRepository<Book, Long> {
    
    long countByAuthor(Author author);
}
