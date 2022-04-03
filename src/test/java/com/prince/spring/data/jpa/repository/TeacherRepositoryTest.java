package com.prince.spring.data.jpa.repository;

import com.prince.spring.data.jpa.model.Course;
import com.prince.spring.data.jpa.model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        List<Course> courseList = List.of(
                Course.builder()
                        .title("Statistique")
                        .credit(4)
                        .build(),
                Course.builder()
                        .title("Geometrie Analytique")
                        .credit(4)
                        .build()

        );

        Teacher teacher = Teacher.builder()
                .firstName("Prince Stanley")
                .lastName("Jean Baptiste")
                //.courseList(courseList)
                .build();

        teacherRepository.save(teacher);
    }

    @Test
    public void getTeachers(){
        List<Teacher> teachers = teacherRepository.findAll();
        System.out.println("Teachers : "+teachers);
    }
}