package com.LibraryManagementSystem.LMSdemo.Service;

import com.LibraryManagementSystem.LMSdemo.DTO.AuthorRequestDTO;
import com.LibraryManagementSystem.LMSdemo.DTO.AuthorResponseDTO;
import com.LibraryManagementSystem.LMSdemo.Entity.Author;
import com.LibraryManagementSystem.LMSdemo.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository repo;
    public AuthorResponseDTO addAuthor(AuthorRequestDTO authorRequestDTO){
         //no attribute to be set before saving since all the attribute are already present in author object
         Author author = new Author();
         author.setAge(authorRequestDTO.getAge());
         author.setName(authorRequestDTO.getName());
         author.setPhone(authorRequestDTO.getPhone());
         author.setEmail(authorRequestDTO.getEmail());

         repo.save(author);//save the author

         AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
         authorResponseDTO.setId(author.getId());
         authorResponseDTO.setName(author.getName());

         return authorResponseDTO;

    }
    public List<Author> getAuthor(){
        return repo.findAll();
    }
}
