package com.prince.spring.data.jpa.repository;

import com.prince.spring.data.jpa.model.Guardian;
import com.prince.spring.data.jpa.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("ingjeanbaptiste@gmail.com")
                .firstName("Prince")
                .lastName("Stanley")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Pipo")
                .email("pipo@pih.org")
                .mobile("32421244")
                .build();
        Guardian guardian1 = Guardian.builder()
                .name("Emerson")
                .email("memerson@pih.org")
                .mobile("43531234")
                .build();


        Guardian guardian2 = Guardian.builder()
                .name("Paul Edouard")
                .email("pedouard@pih.org")
                .mobile("32421244")
                .build();

        Student student1 = Student.builder()
                .firstName("Jean Edouard")
                .lastName("Jean Pierre")
                .emailId("jeanedouard@pih.org")
                .guardian(guardian)
                .build();

        Student student2 = Student.builder()
                .firstName("Rikenson")
                .lastName("Jacques")
                .emailId("jriquenson@pih.org")
                .guardian(guardian2)
                .build();

        Student student3 = Student.builder()
                .firstName("Peterson")
                .lastName("Edouard")
                .emailId("epeterson@pih.org")
                .guardian(guardian1)
                .build();

        Student student4 = Student.builder()
                .firstName("Jean Paul")
                .lastName("Louidor")
                .emailId("jpaullouidor@pih.org")
                .guardian(guardian)
                .build();

        studentRepository.saveAll(List.of(student1, student2, student3, student4));
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Student List : "+studentList.toString());
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> studentList = studentRepository.findByFirstName("Peterson");
        System.out.println("Student : "+studentList);
    }

    @Test
    public void printStudentByFirstNameContains(){
        List<Student> studentList = studentRepository.findByFirstNameContaining("son");
        System.out.println("Student : "+studentList);
    }

    @Test
    public void printStudentByEmailContaining(){
        List<Student> studentList = studentRepository.findByEmailIdContaining("pih.org");
        System.out.println("Student : "+studentList);
    }

    @Test
    public void printStudentByNotNullLastName(){
        List<Student> studentList = studentRepository.findByLastNameNotNull();
        System.out.println("Student : "+studentList);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> studentList = studentRepository.findByGuardianName("Pipo");
        System.out.println("Student : "+studentList);
    }

}