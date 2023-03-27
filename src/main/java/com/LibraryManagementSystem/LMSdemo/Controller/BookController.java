package com.LibraryManagementSystem.LMSdemo.Controller;

import com.LibraryManagementSystem.LMSdemo.DTO.BookRequestDTO;
import com.LibraryManagementSystem.LMSdemo.DTO.BookResponseDTO;
import com.LibraryManagementSystem.LMSdemo.Entity.Book;
import com.LibraryManagementSystem.LMSdemo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    BookService service;

    @PostMapping("/add")
    public BookResponseDTO addBook(@RequestBody BookRequestDTO bookRequestDTO) throws Exception {
        try {
           return   service.addBook(bookRequestDTO);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + " Book not added");
        }
    }
}
