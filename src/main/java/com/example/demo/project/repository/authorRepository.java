package com.example.demo.project.repository;

import com.example.demo.project.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface authorRepository extends JpaRepository<Author, Long> {
    
    
}
