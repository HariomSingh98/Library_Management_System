package com.LibraryManagementSystem.LMSdemo.Controller;

import com.LibraryManagementSystem.LMSdemo.DTO.IssueBookRequestDTO;
import com.LibraryManagementSystem.LMSdemo.DTO.IssueBookResponseDTO;
import com.LibraryManagementSystem.LMSdemo.Service.TranscationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transcation")
public class TranscationController {

    @Autowired
    TranscationService service;
    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO){
          IssueBookResponseDTO issueBookResponseDTO;
          try{
            issueBookResponseDTO =  service.issueBook(issueBookRequestDTO);
          }
          catch (Exception e){
              return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
          }
         return new ResponseEntity(issueBookResponseDTO,HttpStatus.ACCEPTED);
    }

    @PostMapping("/returnBook")
    public ResponseEntity<String> returnBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO){
         try{
             service.returnBook(issueBookRequestDTO);
         }
         catch(Exception e ){
             return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
         }

         return new ResponseEntity("Book Successfully Returned",HttpStatus.ACCEPTED);
    }
}
