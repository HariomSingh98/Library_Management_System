package com.LibraryManagementSystem.LMSdemo.Repository;

import com.LibraryManagementSystem.LMSdemo.Entity.Transcation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscationRepository extends JpaRepository<Transcation,Integer> {

}
