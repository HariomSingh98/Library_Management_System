package com.LibraryManagementSystem.LMSdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor

public class IssueBookRequestDTO {
    //info needed to issue a book

    private int book_id;
    private int card_id;
}
