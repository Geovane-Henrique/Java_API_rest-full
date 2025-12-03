package com.geo.pessoa.service;

import com.geo.pessoa.controller.PersonController;
import com.geo.pessoa.dto.PersonDTO;
import com.geo.pessoa.exception.NotFoundException;
import com.geo.pessoa.mapper.PersonMapper;
import com.geo.pessoa.model.Person;
import com.geo.pessoa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@Service
public class PersonService {

    @Autowired
    PersonMapper mapper;

    @Autowired
    PersonRepository repository;

    public PersonDTO findById(Long id){
        var entity = repository.findById(id).
                orElseThrow(()-> new NotFoundException("pessoa não encontrada"));

        var dto = mapper.toDTO(entity);

        addLink(dto);
        return dto;
    }

    public Page<PersonDTO> findAll(Pageable pageable){
        var entities = repository.findAll(pageable);
        var dtos = entities.map(mapper::toDTO);

        return dtos;
    }

    public PersonDTO create(PersonDTO dto){

        System.out.println(dto.getCpf());
        var entity = mapper.toEntity(dto);
        System.out.println(entity.getCpf());

        var entitySaved = repository.save(entity);
        var dtor = mapper.toDTO(entitySaved);



        addLink(dtor);

        return dtor;
    }

    public PersonDTO update(PersonDTO dto){
        var entity = repository.findById(dto.getId()).
                orElseThrow(()-> new NotFoundException("pessoa não encontrada"));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setCpf(dto.getCpf());

        var entitySaved = repository.save(entity);
        var dtor = mapper.toDTO(entitySaved);

        addLink(dtor);
        return dtor;
    }

    public void  delete(Long id){
        var entity = repository.findById(id).
                orElseThrow(()-> new RuntimeException("pessoa não encontrada"));

        repository.delete(entity);
    }

    public Page<PersonDTO> findByNomeStartingWithIgnoreCaseAndNomeEndingWithIgnoreCase(Pageable pageable,String start,String end){

        return repository.findByFirstNameStartingWithIgnoreCaseAndFirstNameEndingWithIgnoreCase(start ,end ,pageable)
                .map(mapper::toDTO);
    }


       public void addLink(PersonDTO dto){
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withRel("GET"));
       dto.add(linkTo(PersonController.class).slash("").withRel("findAll").withType("GET"));
       dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
       dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
       dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
   }
}
