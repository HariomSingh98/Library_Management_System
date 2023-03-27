package com.LibraryManagementSystem.LMSdemo.Service;

import com.LibraryManagementSystem.LMSdemo.DTO.IssueBookRequestDTO;
import com.LibraryManagementSystem.LMSdemo.DTO.IssueBookResponseDTO;
import com.LibraryManagementSystem.LMSdemo.Entity.Book;
import com.LibraryManagementSystem.LMSdemo.Entity.LibraryCard;
import com.LibraryManagementSystem.LMSdemo.Entity.Transcation;
import com.LibraryManagementSystem.LMSdemo.Enum.CardStatus;
import com.LibraryManagementSystem.LMSdemo.Enum.TranscationStatus;
import com.LibraryManagementSystem.LMSdemo.Repository.BookRepository;
import com.LibraryManagementSystem.LMSdemo.Repository.CardRepository;
import com.LibraryManagementSystem.LMSdemo.Repository.TranscationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TranscationService {

    @Autowired
    TranscationRepository transcationRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    private JavaMailSender emailSender;//to send the email
    public IssueBookResponseDTO issueBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception {

        //Create Transcation Object and set the parameter that are known
        Transcation transcation = new Transcation();
        transcation.setTranscationNo(String.valueOf(UUID.randomUUID()));
        transcation.setIssuedOperation(true);

        //get the card object
        LibraryCard card;
        try {
               card = cardRepository.findById(issueBookRequestDTO.getCard_id()).get();
        }
        catch(Exception e){
            transcation.setStatus(TranscationStatus.FAILED);
            transcation.setMessage("Invalid card_id");
            transcationRepository.save(transcation);//save the transcation since no further line will execute after it
            throw new Exception("Invalid card_id");
        }
        //get the book object
        Book book;
        try {
            book = bookRepository.findById(issueBookRequestDTO.getBook_id()).get();
        }
        catch(Exception e){
            transcation.setStatus(TranscationStatus.FAILED);
            transcation.setMessage("Invalid Book_id");
            transcationRepository.save(transcation);//save the transcation since no further line will execute after it
            throw new Exception("Invalid book_id");
        }
        //both card and book are valid

        transcation.setBook(book);
        transcation.setCard(card);

        //check for status and issuedStatus
        if(card.getStatus()!= CardStatus.ACTIVATED){
            transcation.setStatus(TranscationStatus.FAILED);
            transcation.setMessage("card is not Active");
            transcationRepository.save(transcation);//save the transcation since no further line will execute after it
          throw new Exception("Your card is not Activated");
        }
        if(book.isIssued()==true){
            transcation.setStatus(TranscationStatus.FAILED);
            transcation.setMessage("Book is already issued");
            transcationRepository.save(transcation);//save the transcation since no further line will execute after it
            throw new Exception("Sorry!,Book is already issued");
        }

        //I can issue the book

        book.setIssued(true);//set the issue status
        book.getTranscationList().add(transcation);//add the transcation in the list
        book.setCard(card);

        card.getTranscationList().add(transcation);
        card.getIsssuedBookList().add(book);

        transcation.setStatus(TranscationStatus.SUCCESS);
        transcation.setMessage("Successfully Issued");

        cardRepository.save(card);//book and transcation will also get save becoz of cascading operation

       //create the response object
        IssueBookResponseDTO issueBookResponseDTO = new IssueBookResponseDTO();
        issueBookResponseDTO.setTranscationNo(transcation.getTranscationNo());
        issueBookResponseDTO.setBookName(book.getTitle());
        issueBookResponseDTO.setStatus(transcation.getStatus());


        //to send the email to the student email
        String text ="Congrats!! "+card.getStudent().getName()+" you have successfully issued the book named "+book.getTitle();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backenedmarchsb@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Book Issued Notification");
        message.setText(text);
        emailSender.send(message);

        return issueBookResponseDTO;

    }

    public void returnBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception {
        //Create Transcation Object and set the parameter that are known
        Transcation transcation = new Transcation();
        transcation.setTranscationNo(String.valueOf(UUID.randomUUID()));
        transcation.setIssuedOperation(false);

        //get the card object
        LibraryCard card;
        try {
            card = cardRepository.findById(issueBookRequestDTO.getCard_id()).get();
        }
        catch(Exception e){
            transcation.setStatus(TranscationStatus.FAILED);
            transcation.setMessage("Invalid card_id");
            transcationRepository.save(transcation);//save the transcation since no further line will execute after it
            throw new Exception("Invalid card_id");
        }
        //get the book object
        Book book;
        try {
            book = bookRepository.findById(issueBookRequestDTO.getBook_id()).get();
        }
        catch(Exception e){
            transcation.setStatus(TranscationStatus.FAILED);
            transcation.setMessage("Invalid Book_id");
            transcationRepository.save(transcation);//save the transcation since no further line will execute after it
            throw new Exception("Invalid book_id");
        }
        //valid book and card_id
        transcation.setBook(book);
        transcation.setCard(card);

        //check if current book-id belong to given card-id
        if(book.getCard().getId()!= card.getId()){
            transcation.setStatus(TranscationStatus.FAILED);
            transcation.setMessage("given book is not issued to given card");
            transcationRepository.save(transcation);//save the transcation since no further line will execute after it
            throw new Exception("Invalid book-card Id");
        }



        book.setIssued(false);//set the issue status
        book.getTranscationList().add(transcation);//add the transcation in the list
        book.setCard(null);

        card.getTranscationList().add(transcation);
        card.getIsssuedBookList().remove(book);

        transcation.setStatus(TranscationStatus.SUCCESS);
        transcation.setMessage("Successfully Returned");

        cardRepository.save(card);//book and transcation will also get save becoz of cascading operation

        //send the email
        String text ="Congrats!! "+card.getStudent().getName()+" you have successfully returned the book named "+book.getTitle();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backenedmarchsb@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Book Returned Notification");
        message.setText(text);
        emailSender.send(message);



    }

}
