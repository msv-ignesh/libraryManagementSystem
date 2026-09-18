package com.example.demo.project.service;

import org.springframework.stereotype.Service;
import com.example.demo.project.repository.authorRepository;
import com.example.demo.project.model.Author;

import java.util.List;

@Service
public class authorService {

    private final authorRepository repository;


    public authorService(authorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    public Author addAuthor(Author author) {
        return repository.save(author);
    }

    public Author getAuthorById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author existingAuthor = repository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setCountry(updatedAuthor.getCountry());
        return repository.save(existingAuthor);
    }

    public void deleteAuthor(Long id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Author not found");
        }
        repository.deleteById(id);
    }
}
