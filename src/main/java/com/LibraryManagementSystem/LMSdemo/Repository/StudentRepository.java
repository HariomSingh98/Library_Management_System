package com.LibraryManagementSystem.LMSdemo.Repository;


import com.LibraryManagementSystem.LMSdemo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository

public interface StudentRepository extends JpaRepository<Student,Integer> {


    //custom function to find student by email and age

    Student findByEmail(String email);

    List<Student> findByAge(int age);

  //update student email using @Query annotation
    @Modifying
    @Transactional
    @Query(value = "update Student s set s.email= :newEmail where s.id=:id")
    void updateStudentEmail(int id,String newEmail);
}
