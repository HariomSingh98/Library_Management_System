package com.LibraryManagementSystem.LMSdemo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    private String email;
    private int phone;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    List<Book> books =  new ArrayList<>();//create an empty list of book when an author is created

}
