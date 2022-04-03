package com.prince.spring.data.jpa.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1,
            initialValue = 1
    )

    @GeneratedValue(
            generator = "teacher_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long teacherId;

    private String firstName;
    private String lastName;

    /*
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )

    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private List<Course> courseList;*/




}
