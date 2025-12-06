package com.geo.pessoa.repository;

import com.geo.pessoa.dto.PersonDTO;
import com.geo.pessoa.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepository extends JpaRepository<Person,Long>, JpaSpecificationExecutor<Person> {


}




