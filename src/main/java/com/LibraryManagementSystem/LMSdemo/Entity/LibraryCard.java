package com.LibraryManagementSystem.LMSdemo.Entity;

import com.LibraryManagementSystem.LMSdemo.Enum.CardStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String validTill="06/2025";

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updationDate;

    @OneToOne
    @JoinColumn(name="s_id")//join column name will be s_id
    Student student;//single student instance for every card

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Transcation> transcationList = new ArrayList<>();//one card have many transcation

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Book> isssuedBookList = new ArrayList<>();//one card have many issued book


}
