package com.example.librarymanagementsystem.Entities;

import com.example.librarymanagementsystem.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "library_card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;
    @Enumerated(value = EnumType.STRING)
     private CardStatus cardStatus;
     private int noOfBookIssued;



     // it has foriegn key this is child class
@JoinColumn
    @OneToOne
    private Student student;

}
