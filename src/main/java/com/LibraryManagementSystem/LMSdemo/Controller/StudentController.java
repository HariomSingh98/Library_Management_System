package com.LibraryManagementSystem.LMSdemo.Controller;


import com.LibraryManagementSystem.LMSdemo.DTO.StudentRequestDTO;
import com.LibraryManagementSystem.LMSdemo.DTO.StudentResponseDTO;
import com.LibraryManagementSystem.LMSdemo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService service;
    @PostMapping("/add")
    public StudentResponseDTO addStudent(@RequestBody StudentRequestDTO studentRequestDTO){

         return service.addStudent(studentRequestDTO);
    }

    @GetMapping("/find_by_email")
    public String getStudentByEmail(@RequestParam("email") String email){

        return service.getStudentByEmail(email);
    }

    @GetMapping("/find_by_age/{age}")
    public List<StudentResponseDTO> getStudentByAge(@PathVariable("age") int age){

        return service.getStudentByAge(age);

    }


    @PutMapping("/update_email")
    public String updateEmail(@RequestParam int studentId, @RequestParam String newEmail){
        service.updateEmail(studentId,newEmail);
        return "successfully Updated";
    }


}
