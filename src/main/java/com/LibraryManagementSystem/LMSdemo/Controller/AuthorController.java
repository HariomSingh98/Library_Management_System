package com.LibraryManagementSystem.LMSdemo.Controller;

import com.LibraryManagementSystem.LMSdemo.DTO.AuthorRequestDTO;
import com.LibraryManagementSystem.LMSdemo.DTO.AuthorResponseDTO;
import com.LibraryManagementSystem.LMSdemo.Entity.Author;
import com.LibraryManagementSystem.LMSdemo.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    AuthorService service;
    @PostMapping("/add")
    public AuthorResponseDTO addAuthor(@RequestBody AuthorRequestDTO authorRequestDTO){//add a new author
       return  service.addAuthor(authorRequestDTO);
    }




}
