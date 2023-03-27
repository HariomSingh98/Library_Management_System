package com.LibraryManagementSystem.LMSdemo.Service;

import com.LibraryManagementSystem.LMSdemo.DTO.BookRequestDTO;
import com.LibraryManagementSystem.LMSdemo.DTO.BookResponseDTO;
import com.LibraryManagementSystem.LMSdemo.Entity.Author;
import com.LibraryManagementSystem.LMSdemo.Entity.Book;
import com.LibraryManagementSystem.LMSdemo.Repository.AuthorRepository;
import com.LibraryManagementSystem.LMSdemo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;//to get author info using id we get from the book object

    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) throws Exception {

        Book book = new Book();//create a book object
        Author author;
       try {
            author = authorRepository.findById(bookRequestDTO.getAuthorId()).get();//get the author object from bookRequestDto object

       }catch (Exception e){
           throw new Exception("Author not present");
       }

       //conversion of book object from dto, set the book attribute
       book.setTitle(bookRequestDTO.getTitle());
       book.setPrice(bookRequestDTO.getPrice());
       book.setGenre(bookRequestDTO.getGenre());
       book.setIssued(false);
       book.setAuthor(author);

       //add the book in author
       List<Book> booksWritten = author.getBooks();
       booksWritten.add(book);
       //book table  will store  every element in list as 1 row in the table
       authorRepository.save(author);//book will also be saved using bidirectional mapping

        //create the response object
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(book.getId());
        bookResponseDTO.setTitle(book.getTitle());

        return bookResponseDTO;
    }
}