package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Author;
import com.example.librarymanagementsystem.Entities.Book;
import com.example.librarymanagementsystem.ReequestDtos.AddBookRequest;
import com.example.librarymanagementsystem.Repository.AuthorRepository;
import com.example.librarymanagementsystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
public String addBook(AddBookRequest bookRequest){

//Logical step:
// 1 get author from authorId

 Author author = authorRepository.findById(bookRequest.getAuthorId()).get();

    //2 create book entity from book request
Book newBook = new Book(bookRequest.getBookName(),bookRequest.getGenre(),bookRequest.getNoOfPages(),bookRequest.getPrice(),bookRequest.getPublishDate());

              author.setNoOfBooksWritten(author.getNoOfBooksWritten()+1);


    //3 Set the FK variable / mapping variable

    //3.1 adding for the author entity
    newBook.setAuthor(author);//unidirectional mapping


    //3.2for the author add book in the booklist

    author.getBookList().add(newBook);//bidirectional mapping



    // 4 save parent
authorRepository.save(author);

return "Book has been saved to DB";


}

}
