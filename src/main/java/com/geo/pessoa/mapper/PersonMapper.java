package com.geo.pessoa.mapper;

import com.geo.pessoa.dto.PersonDTO;
import com.geo.pessoa.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonDTO dto);
    PersonDTO toDTO(Person entity);

    List<Person> toListPerson(List<PersonDTO> dtos);
    List<PersonDTO> toListPersonDto(List<Person> entities);
}
