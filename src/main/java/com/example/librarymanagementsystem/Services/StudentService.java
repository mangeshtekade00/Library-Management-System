package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Student;
import com.example.librarymanagementsystem.ReequestDtos.ModifyPhoneNoRequst;
import com.example.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class StudentService {
@Autowired
private  StudentRepository studentRepository;


    public  String addStudent(Student student){

           Student savedStudent = studentRepository.save(student);

           return "The student has been saved to DB with the studentId"+savedStudent.getStudentId();

    }

    public  Student findStudentById(Integer studentId)throws Exception{

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
           if(optionalStudent.isEmpty()){
               throw new Exception("Student Id entered is incorrect");
           }
           Student student = optionalStudent.get();

          return student;

    }

    public String ModifyPhoneNo(ModifyPhoneNoRequst modifyPhoneNoRequst){
        Integer studentId= modifyPhoneNoRequst.getStudentId();
        String newPhoneNo = modifyPhoneNoRequst.getNewPhoneNo();
        Student student = studentRepository.findById(studentId).get();

        student.setPhoneNo(newPhoneNo);
        studentRepository.save(student);

        return "Phone number has been modified";


    }
}
