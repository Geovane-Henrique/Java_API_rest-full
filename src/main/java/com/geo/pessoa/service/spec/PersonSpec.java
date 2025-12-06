package com.geo.pessoa.service.spec;

import com.geo.pessoa.model.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PersonSpec {

    public  Specification<Person> firstNameStart(String value){
      return (root, query, cb) -> cb.like(cb.lower(root.get("firstName")), value.toLowerCase() + "%");
    }

    public  Specification<Person> firstNameEnd(String value){
        return (root, query, cb) -> cb.like(cb.lower(root.get("firstName")),"%" + value.toLowerCase());
    }

    public Specification<Person> firstNameContains(String value){
        return (root, query, cb) -> cb.like(cb.lower(root.get("firstName")),"%" + value.toLowerCase() + "%");
    }

    public Specification<Person> LessAge(int value){
        return (root, query, cb) -> cb.lessThan(root.get("age"), value);
    }

    public Specification<Person> greaterAge(int value){
        return (root, query, cb) -> cb.greaterThan(root.get("age"),value);
    }

    public Specification<Person> gender(String value){
        return (root, query, cb) -> cb.like(cb.lower(root.get("gender")), "%" + value +"%");
    }
}
