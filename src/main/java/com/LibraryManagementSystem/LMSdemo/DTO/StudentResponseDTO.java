package com.LibraryManagementSystem.LMSdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentResponseDTO {//give the response object with given field
    private int id ;
    private String name;

    //only name and id will be output in  response object
}
