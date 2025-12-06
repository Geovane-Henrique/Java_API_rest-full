package com.geo.pessoa.repository;

import com.geo.pessoa.dto.PersonDTO;
import com.geo.pessoa.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Page<Person> findByFirstNameStartingWithIgnoreCaseAndFirstNameEndingWithIgnoreCase(String start,String end, Pageable pageable);

    Page<Person> findByFirstNameStartingWithIgnoreCase(String start,Pageable pageable);

    Page<Person> findByFirstNameEndingWithIgnoreCase(String end,Pageable pageable);
}




