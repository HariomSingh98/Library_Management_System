package com.LibraryManagementSystem.LMSdemo.DTO;

import com.LibraryManagementSystem.LMSdemo.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequestDTO {//give details to add book

    private String title ;
    private int price;
    private Genre genre;
    private int authorId;
}
