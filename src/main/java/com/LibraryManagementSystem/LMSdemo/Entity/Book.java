package com.LibraryManagementSystem.LMSdemo.Entity;

import com.LibraryManagementSystem.LMSdemo.Enum.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private boolean isIssued;

    @ManyToOne
    @JoinColumn(name="a_id")
    Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    List<Transcation> transcationList = new ArrayList<>();//one book may be involved in many transcation

    @ManyToOne
    @JoinColumn
    LibraryCard card;//to connect with card entity

}
