package com.LibraryManagementSystem.LMSdemo.DTO;

import com.LibraryManagementSystem.LMSdemo.Enum.TranscationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


public class IssueBookResponseDTO {
    private String transcationNo;
    private String bookName;

    private TranscationStatus status;
}
