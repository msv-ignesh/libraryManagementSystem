package com.example.demo.project.controller.webUI;

import com.example.demo.project.model.Author;
import com.example.demo.project.service.authorService;
import com.example.demo.project.service.bookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/authors-ui")
public class authorWebController {

    private final authorService authorService;
    private final bookService bookService;

    public authorWebController(
            authorService authorService,
            bookService bookService) {

        this.authorService = authorService;
        this.bookService = bookService;
    }

    // Show all authors
    @GetMapping
    public String showAuthors(Model model) {

        model.addAttribute(
                "authors",
                authorService.getAllAuthors()
        );

        Map<Long, Long> bookCounts = new HashMap<>();

        for (Author author : authorService.getAllAuthors()) {

            long count = bookService.countBooksByAuthor(author);

            bookCounts.put(author.getId(), count);
        }

        model.addAttribute("bookCounts", bookCounts);

        return "authors/author";
    }

    // Show Add Author form
    @GetMapping("/new")
    public String showAddAuthorForm(Model model) {

        model.addAttribute("author", new Author());

        return "authors/addAuthor";
    }

    // Save new author
    @PostMapping("/save")
    public String saveAuthor(
            @ModelAttribute("author") Author author) {

        authorService.addAuthor(author);

        return "redirect:/authors-ui";
    }

    // Show Edit Author form
    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Author author = authorService.getAuthorById(id);

        model.addAttribute("author", author);

        return "authors/editAuthor";
    }

    // Update author
    @PostMapping("/update/{id}")
    public String updateAuthor(
            @PathVariable Long id,
            @ModelAttribute("author") Author author) {

        authorService.updateAuthor(id, author);

        return "redirect:/authors-ui";
    }

    // Delete author
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {

        Author author = authorService.getAuthorById(id);

        long bookCount = bookService.countBooksByAuthor(author);

        if (bookCount > 0) {

            return "redirect:/authors-ui?deleteError=true";
        }

        authorService.deleteAuthor(id);

        return "redirect:/authors-ui";
    }
}