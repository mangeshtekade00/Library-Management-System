package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Book;
import com.example.librarymanagementsystem.Entities.LibraryCard;
import com.example.librarymanagementsystem.Entities.Transaction;
import com.example.librarymanagementsystem.Enums.TransactionStatus;
import com.example.librarymanagementsystem.Enums.TransactionType;
import com.example.librarymanagementsystem.Exceptions.BookNotFoundException;
import com.example.librarymanagementsystem.Exceptions.CardNotFoundException;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public String issueBook(Integer cardId, Integer bookId) throws Exception{

//Logical step to issue book

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.ISSUED);
        transaction.setTransactionStatus(TransactionStatus.ONGOING);

        //1 get book and card from the db


       Optional<Book> bookOptional = bookRepository.findById(bookId);
       if(bookOptional.isEmpty()){
           throw new BookNotFoundException("BookId entered is invalid");
       }

           Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
       if(optionalLibraryCard.isEmpty()){
           throw new CardNotFoundException("Card Id entered is invalid");
       }

        Book book = bookOptional.get();
        LibraryCard card = optionalLibraryCard.get();
        //2 validate book and card entity variables

        // check availability
        if(book.getIsAvailable()==Boolean.FALSE){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction= transactionRepository.save(transaction);

            throw new Exception("Book with given bookId is not available. TransactionId " +transaction.getTrasactionId());

        }

        //check for Max book issued
        if(card.getNoOfBookIssued()>=LibraryCard.MAX_NO_ALLOWED_BOOKS){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction= transactionRepository.save(transaction);

            throw new Exception("You have reached the Max limit of issued book"+
                    "Please return a book in oder to issue a new book. TransactionId "+transaction.getTrasactionId());
        }

 // Here all validation are done
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);


      //update book and card status
     book.setIsAvailable(Boolean.FALSE);
     card.setNoOfBookIssued(card.getNoOfBookIssued()+1);

     //child class need to have attribute
     transaction.setBook(book);
     transaction.setLibraryCard(card);

//save the child and it will cascade to both of the parent

// Lc and book
      transaction = transactionRepository.save(transaction);

      return "Transaction with Id "+transaction.getTrasactionId()+" have been saved to Db";
    }

}
