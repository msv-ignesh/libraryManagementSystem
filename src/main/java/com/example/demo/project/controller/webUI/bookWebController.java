package com.example.demo.project.controller.webUI;

import com.example.demo.project.model.Author;
import com.example.demo.project.model.Book;
import com.example.demo.project.service.authorService;
import com.example.demo.project.service.bookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/books")
public class bookWebController {

    private final bookService bookService;
    private final authorService authorService;

    public bookWebController(
            bookService bookService,
            authorService authorService) {

        this.bookService = bookService;
        this.authorService = authorService;
    }
    

   
    @GetMapping
    public String showBooks(Model model) {

        model.addAttribute(
                "books",
                bookService.getAllBooks()
        );

        return "books/book";
    }


    @GetMapping("/new")
    public String showAddBookForm(Model model) {

        model.addAttribute("book", new Book());

        model.addAttribute(
                "authors",
                authorService.getAllAuthors()
        );

        return "books/addBook";
    }


    @PostMapping("/save")
    public String saveBook(
            @Valid @ModelAttribute("book") Book book,
            BindingResult result,
            @RequestParam Long authorId,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "authors",
                    authorService.getAllAuthors()
            );

            return "books/addBook";
        }

        Author author = authorService.getAuthorById(authorId);

        book.setAuthor(author);

        bookService.addBook(book);

        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Book book = bookService.getBookById(id);

        model.addAttribute("book", book);

        model.addAttribute(
                "authors",
                authorService.getAllAuthors()
        );

        return "books/editBook";
    }


    @PostMapping("/update/{id}")
    public String updateBook(
            @PathVariable Long id,
            @Valid @ModelAttribute("book") Book book,
            BindingResult result,
            @RequestParam Long authorId,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "authors",
                    authorService.getAllAuthors()
            );

            return "books/editBook";
        }

        Author author = authorService.getAuthorById(authorId);

        book.setAuthor(author);

        bookService.updateBook(id, book);

        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        return "redirect:/books";
    }
}