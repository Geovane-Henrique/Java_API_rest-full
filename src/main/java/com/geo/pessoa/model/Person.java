package com.geo.pessoa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "person")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false, length = 70)
    private String firstName;

    @Column(name = "last_name",nullable = false, length = 70)
    private String lastName;

    @Column(name = "age",nullable = false)
    private int age;

    @Column(name = "cpf",nullable = false)
    private String cpf;

    @Column(name = "gender",nullable = false,length = 1)
    private String gender;

}
