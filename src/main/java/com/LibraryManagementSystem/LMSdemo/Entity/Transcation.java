package com.LibraryManagementSystem.LMSdemo.Entity;

import com.LibraryManagementSystem.LMSdemo.Enum.TranscationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transcation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String  transcationNo;

    @Enumerated(EnumType.STRING)
    private TranscationStatus status;

    private boolean isIssuedOperation;

    @CreationTimestamp
    private Date transcationDate;
    private String message;

    @ManyToOne
    @JoinColumn
    LibraryCard card;//to connect with card entity through many_to_one mapping

    @ManyToOne
    @JoinColumn
    Book book;//to connect with book entity


}
