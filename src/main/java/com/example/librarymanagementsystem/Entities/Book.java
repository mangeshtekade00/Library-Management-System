package com.example.librarymanagementsystem.Entities;

import com.example.librarymanagementsystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;

@Entity
@Table(name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int bookId;
    @Column(unique = true)
    private String bookName;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private int noOfPages;
    private int price;
    private Date publishDate;

    @JoinColumn(referencedColumnName = "emailId")
    @ManyToOne
    private Author author;

}
