package com.example.librarymanagementsystem.ReequestDtos;

import com.example.librarymanagementsystem.Enums.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AddBookRequest {



    private String bookName;

    private Genre genre;
    private int noOfPages;
    private int price;
    private Date publishDate;


    //aditional attribute from outside
    private  Integer authorId;


}
