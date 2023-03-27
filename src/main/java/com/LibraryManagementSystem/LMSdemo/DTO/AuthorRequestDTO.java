package com.LibraryManagementSystem.LMSdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class AuthorRequestDTO {
    private String name;
    private  int age;
    private String email;
    private int phone;
}
