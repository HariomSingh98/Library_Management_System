package com.LibraryManagementSystem.LMSdemo.Repository;

import com.LibraryManagementSystem.LMSdemo.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
}
