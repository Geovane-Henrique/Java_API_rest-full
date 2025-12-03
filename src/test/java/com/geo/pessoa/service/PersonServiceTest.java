package com.geo.pessoa.service;

import com.geo.pessoa.dto.PersonDTO;
import com.geo.pessoa.mapper.PersonMapper;
import com.geo.pessoa.model.Person;
import com.geo.pessoa.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    PersonRepository repository;

    @Mock
    PersonMapper mapper;

    @InjectMocks
    PersonService service;

    Person person = new Person(1L,"geovane","henrique",21,"1233423424","m");

    PersonDTO dto = new PersonDTO(1L,"geovane","henrique",21,"1233423424","m");

    PersonDTO dtoU = new PersonDTO(1L,"nane","henrique",21,"1233423424","m");

    @Test
    void findById() {
        when(repository.findById(dto.getId())).thenReturn(Optional.of(person));
        when(mapper.toDTO(person)).thenReturn(dto);

        PersonDTO result = service.findById(dto.getId());

        assertEquals(result.getFirstName(),person.getFirstName());
        assertEquals(result.getLastName(),person.getLastName());
        assertEquals(result.getAge(),person.getAge());
        assertEquals(result.getCpf(),person.getCpf());
        assertEquals(result.getGender(),person.getGender());

        verify(repository).findById(dto.getId());
        verify(mapper).toDTO(person);
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
        when(mapper.toEntity(dto)).thenReturn(person);
        when(repository.save(person)).thenReturn(person);
        when(mapper.toDTO(person)).thenReturn(dto);

        PersonDTO result = service.create(dto);

        assertEquals(result.getFirstName(),person.getFirstName());
        assertEquals(result.getLastName(),person.getLastName());
        assertEquals(result.getAge(),person.getAge());
        assertEquals(result.getCpf(),person.getCpf());
        assertEquals(result.getGender(),person.getGender());

        verify(mapper).toEntity(dto);
        verify(repository).save(person);
        verify(mapper).toDTO(person);

    }

    @Test
    void update() {

        when(repository.findById(dtoU.getId())).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(person);
        when(mapper.toDTO(person)).thenReturn(dtoU);

        PersonDTO result = service.update(dtoU);

        assertEquals(result.getFirstName(),dtoU.getFirstName());
        assertEquals(result.getLastName(),dtoU.getLastName());
        assertEquals(result.getAge(),dtoU.getAge());
        assertEquals(result.getCpf(),dtoU.getCpf());
        assertEquals(result.getGender(),dtoU.getGender());

        verify(repository).findById(dtoU.getId());
        verify(repository).save(person);
        verify(mapper).toDTO(person);
    }

    @Test
    void delete() {
        when(repository.findById(dto.getId())).thenReturn(Optional.of(person));
        doNothing().when(repository).delete(person);

         service.delete(dto.getId());

         verify(repository).findById(dto.getId());
         verify(repository).delete(person);
    }
}