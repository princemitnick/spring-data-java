package com.prince.spring.data.jpa.repository;


import com.prince.spring.data.jpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByEmailIdContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailId(String emailId);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailId(String emailId);

    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    String getStudentFirstNameByEmailIdNative(String emailId);

    @Query(
            value = "SELECT * from tbl_student s where s.email_address = ?1 or s.guardian_email = ?2 ",
            nativeQuery = true
    )
    List<Student> getStudentFirstNameByEmailIdOrGuardianEmail(String emailId, String guardianEmail);


    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = :emailId AND s.guardian_email = :guardianEmail",
            nativeQuery = true
    )
    List<Student> getStudentFirstNameByEmailIdOrGuardianEmailParams(@Param("emailId") String emailId, @Param("guardianEmail") String guardianEmail);


    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student s set s.first_name = ?1 where s.email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);



}
