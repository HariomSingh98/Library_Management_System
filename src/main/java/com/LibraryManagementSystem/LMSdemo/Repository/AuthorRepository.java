package com.LibraryManagementSystem.LMSdemo.Repository;

import com.LibraryManagementSystem.LMSdemo.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
