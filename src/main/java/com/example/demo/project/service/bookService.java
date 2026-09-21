package com.example.demo.project.service;

import org.springframework.stereotype.Service;
import com.example.demo.project.repository.bookRepository;
import com.example.demo.project.model.Author;
import com.example.demo.project.model.Book;

import java.util.List;

@Service
public class bookService {

    private final bookRepository repository;


    public bookService(bookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book addBook(Book book) {
        return repository.save(book);
    }

    public Book getBookById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPrice(updatedBook.getPrice());
        return repository.save(existingBook);
    }

    public void deleteBook(Long id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        repository.deleteById(id);
    }

    public long countBooksByAuthor(Author author) {
        
        return repository.countByAuthor(author);

    }
}
