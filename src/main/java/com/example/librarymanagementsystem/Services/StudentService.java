package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Student;
import com.example.librarymanagementsystem.ReequestDtos.ModifyPhoneNoRequst;
import com.example.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
@Autowired
private static StudentRepository studentRepository;
    public String addStudent(Student student){

           Student savedStudent = studentRepository.save(student);

           return "The student has been saved to DB with the studentId"+savedStudent.getStudentId();

    }

    public static Student findStudentById(Integer studentId)throws Exception{

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
