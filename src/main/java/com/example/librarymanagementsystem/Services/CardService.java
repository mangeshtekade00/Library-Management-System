package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.LibraryCard;
import com.example.librarymanagementsystem.Entities.Student;
import com.example.librarymanagementsystem.Enums.CardStatus;
import com.example.librarymanagementsystem.ReequestDtos.AssociateCardStudentRequest;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
@Autowired
    private CardRepository cardRepository;
@Autowired
private StudentRepository studentRepository;

public String getFreshCard(){
    LibraryCard newCard= new LibraryCard();
    newCard.setCardStatus(CardStatus.NEW);
    newCard.setNoOfBookIssued(0);

      LibraryCard savedCard = cardRepository.save(newCard);
      return "New card with Card No. "+savedCard.getCardId() +" have beed genrated";
}
public String associateCardAndStudent(AssociateCardStudentRequest associateCardStudentRequest)throws Exception{

    Integer cardId = associateCardStudentRequest.getCardId();
    Integer studentId = associateCardStudentRequest.getStudentId();


    Optional<LibraryCard>optionalLibraryCard = cardRepository.findById(cardId);

    if(optionalLibraryCard.isEmpty()){
        throw new Exception("Invalid card Id entered");
    }
    LibraryCard libraryCard = optionalLibraryCard.get();

    Optional<Student>optionalStudent =studentRepository.findById(studentId);
    if(optionalLibraryCard.isEmpty()){
        throw new Exception("No Student with the Id exist in the system");
    }
    Student student = optionalStudent.get();

    //use rule here

    libraryCard.setCardStatus(CardStatus.ACTIVE);
    libraryCard.setStudent(student);
    libraryCard.setNoOfBookIssued(0);cardRepository.save(libraryCard);

    return "Card with cardId  "+cardId+" and Student with studentId "+studentId+" are associated";
}
}



