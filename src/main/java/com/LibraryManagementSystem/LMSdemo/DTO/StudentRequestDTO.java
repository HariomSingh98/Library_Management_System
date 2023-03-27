package com.LibraryManagementSystem.LMSdemo.DTO;

import com.LibraryManagementSystem.LMSdemo.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentRequestDTO {//use to add student with given attribute in  request body
    private String name;
    private int age ;
    private Department dept;
    private String email;
}
