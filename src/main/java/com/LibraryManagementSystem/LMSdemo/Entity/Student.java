package com.LibraryManagementSystem.LMSdemo.Entity;

import com.LibraryManagementSystem.LMSdemo.Enum.Department;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//to make it auto increment
    private int id;

    private String name;
    private int age ;

    @Enumerated(EnumType.STRING)//to store it as string in table
    private Department dept;

    private String email;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)//crud opertion will propagate in child class also
    LibraryCard card;


}
