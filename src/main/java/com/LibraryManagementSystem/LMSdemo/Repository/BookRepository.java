package com.LibraryManagementSystem.LMSdemo.Repository;

import com.LibraryManagementSystem.LMSdemo.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
