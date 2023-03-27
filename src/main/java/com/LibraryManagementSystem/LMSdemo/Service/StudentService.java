package com.LibraryManagementSystem.LMSdemo.Service;


import com.LibraryManagementSystem.LMSdemo.DTO.StudentRequestDTO;
import com.LibraryManagementSystem.LMSdemo.DTO.StudentResponseDTO;
import com.LibraryManagementSystem.LMSdemo.Entity.LibraryCard;
import com.LibraryManagementSystem.LMSdemo.Entity.Student;
import com.LibraryManagementSystem.LMSdemo.Enum.CardStatus;
import com.LibraryManagementSystem.LMSdemo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class StudentService {
    @Autowired
    StudentRepository repo;

    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO){

        //conversion from dto to student object
        Student student = new Student();
        student.setAge(studentRequestDTO.getAge());
        student.setName(studentRequestDTO.getName());
        student.setDept(studentRequestDTO.getDept());
        student.setEmail(studentRequestDTO.getEmail());

        //crate the card object
        LibraryCard card = new LibraryCard();
        //set the card attribute using bidirectional mapping
        card.setStatus(CardStatus.ACTIVATED);

        card.setStudent(student);

        //set the card attribute in student
        student.setCard(card);

        repo.save(student);//save the student row in the table ,also card will also be saved in card table

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();//crate the response object
        //set the attribute in response object
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());

        return studentResponseDTO;
    }

    public String getStudentByEmail(String email){
        Student student =  repo.findByEmail(email);
        return student.getName();
    }

    public List<StudentResponseDTO> getStudentByAge(int age){//take output as response dto solve the problem of stack overflow
         List<Student> studentList = repo.findByAge(age);
         List<StudentResponseDTO> studentResponseDTOList = new ArrayList<>();
         for(Student s : studentList){
             StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
             studentResponseDTO.setId(s.getId());
             studentResponseDTO.setName(s.getName());

             studentResponseDTOList.add(studentResponseDTO);

         }
         return studentResponseDTOList;
    }

    public void updateEmail(int studentId,String newEmail){
        repo.updateStudentEmail(studentId,newEmail);
    }

}
