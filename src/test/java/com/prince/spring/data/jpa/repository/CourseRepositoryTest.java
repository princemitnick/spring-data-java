package com.prince.spring.data.jpa.repository;

import com.prince.spring.data.jpa.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CourseRepositoryTest {


    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printAllCourses(){
        List<Course> courseList = courseRepository.findAll();
        System.out.println("Courses List : "+courseList);
    }

    @Test
    public void saveCourseWithTeacher(){

        Course course = Course.builder()
                .credit(4)
                .title("Francais")
                .teacher(
                        Teacher.builder()
                                .firstName("Bernard")
                                .lastName("Jean Pierre")
                                .build()
                )
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courseList =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getContent();

        long totalElementPagination1 = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();

        System.out.println("Total Element : "+totalElementPagination1);
        System.out.println(courseList);

        List<Course> courses =
                courseRepository.findAll(secondPageWithTwoRecords)
                        .getContent();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        long totalElementPagination2 = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();

        System.out.println(totalElementPagination2);
        System.out.println(courses);
    }

    @Test
    public void findAllSorting(){
        Pageable sortyByTitle =
                PageRequest.of(0,2, Sort.by("title"));

        Pageable sortByCredit =
                PageRequest.of(1, 2, Sort.by("credit"));

        List<Course> coursesByTitle = courseRepository
                .findAll(sortyByTitle)
                .getContent();

        List<Course> courseByCredit = courseRepository
                .findAll(sortByCredit)
                .getContent();

        System.out.println(coursesByTitle);
        System.out.println(courseByCredit);


    }

    @Test
    public void findByTitleContaining(){
        Pageable coursesByTitleContaining =
                PageRequest.of(0, 3);


        Page<Course> coursePage = courseRepository.findByTitleContaining("S", coursesByTitleContaining);
        List<Course> courses = coursePage.getContent();

        System.out.println("Courses Contain 'S' : " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Guardian guardian = Guardian.builder()
                .email("guardian1@pih.org")
                .mobile("4362321")
                .name("Guardian 1")
                .build();

        Course course = Course.builder()
                .title("Architecture des Ordinateurs")
                .teacher(
                        Teacher.builder()
                                .firstName("Garry")
                                .lastName("Guerrier")
                                .build()
                )
                .students(
                        List.of(
                                Student.builder()
                                        .emailId("olamar@pih.org")
                                        .firstName("Olamar")
                                        .guardian(guardian)
                                        .lastName("Calamar")

                                                .build(),
                                Student.builder()
                                        .emailId("malemar@pih.org")
                                        .firstName("Malemar")
                                        .guardian(guardian)
                                        .lastName("Calamar")

                                                .build(),
                                Student.builder()
                                        .emailId("diego@pih.org")
                                        .firstName("Don")
                                        .guardian(guardian)
                                        .lastName("Diego")
                                        .build()
                        )
                )
                .build();

        courseRepository.save(course);

    }
}