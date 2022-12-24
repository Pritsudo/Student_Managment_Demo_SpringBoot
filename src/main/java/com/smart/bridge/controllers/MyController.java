package com.smart.bridge.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.bridge.models.Student;
import com.smart.bridge.repositories.StudentRepository;

@RestController
@RequestMapping("/students")
public class MyController {    
    @Autowired
    StudentRepository studentRepository; // Dependency Injection

    @GetMapping("/all")
    public List<Student> getStudents()
    {
        List<Student> stuents = new ArrayList<Student>();
        studentRepository.findAll().forEach(stduent -> stuents.add(stduent));
        return stuents;
    }

    @GetMapping("/{stId}")
    public Student getStudent(@PathVariable Integer stId)
    {
        Optional<Student> st = studentRepository.findById(stId);
        return st.get();
    }

    // @GetMapping("/{stId}")
    @PatchMapping("/{stId}")
    public String updateStudent(@PathVariable Integer stId, @RequestBody Student student)
    {
        Student st = studentRepository.findById(stId).get();
        st.setName(student.getName());
        st.setAddress(student.getAddress());
        if (st != null) {
            studentRepository.save(st);
        }
       return  "Not found expection";
    }
    
    @DeleteMapping("/{stId}")
    @Transactional
    public String deleteStudent(@PathVariable Integer stId)
    {
        studentRepository.deleteById(stId);
        return "Student deleted";
    }

    @PostMapping("")
    @Transactional
    public Student saveStudent(@RequestBody Student student)
    {
        studentRepository.save(student);
        return student;
        
    }

}
