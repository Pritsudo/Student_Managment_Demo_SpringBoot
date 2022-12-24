package com.smart.bridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart.bridge.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer >{
    
}
