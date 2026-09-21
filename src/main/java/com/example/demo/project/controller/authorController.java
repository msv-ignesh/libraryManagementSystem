package com.example.demo.project.controller;

import com.example.demo.project.model.Author;
import com.example.demo.project.service.authorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController 
@RequestMapping("/api/authors")
public class authorController {
    private final authorService service;
    public authorController(authorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return service.getAllAuthors();
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return service.addAuthor(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(
           @PathVariable Long id, 
           @RequestBody Author author) {
    
        return service.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        service.deleteAuthor(id);
        
    }
    
}
