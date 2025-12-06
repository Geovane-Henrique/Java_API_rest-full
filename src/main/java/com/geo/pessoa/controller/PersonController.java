package com.geo.pessoa.controller;

import com.geo.pessoa.config.PersonConfigPageable;
import com.geo.pessoa.controller.urlBuilder.PageLinkBuilder;
import com.geo.pessoa.dto.PersonDTO;
import com.geo.pessoa.dto.PersonFilter;
import com.geo.pessoa.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pessoa")
public class PersonController {

    @Autowired
    PersonService service;

    @Autowired
    PersonConfigPageable model;

    PageLinkBuilder linkBuilder = new PageLinkBuilder();

    //http://localhost:8080/pessoa/1
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@Valid @PathVariable("id") Long id){
        return service.findById(id);
    }

    //http://localhost:8080/pessoa
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<EntityModel<PersonDTO>> findAll(@PageableDefault(size = 4) Pageable pageable,
                                                      HttpServletRequest request,
                                                      PersonFilter filter){
        var page = service.filter(pageable,filter);
        return model.pagedModel(pageable,page,"",linkBuilder.buildParam(request.getQueryString()));
    }
    //http://localhost:8080/pessoa/create
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE ,
                                   produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO create(@Valid @RequestBody PersonDTO dto){
        return service.create(dto);
    }

    //http://localhost:8080/pessoa/update
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO update(@Valid @RequestBody PersonDTO dto){
        return service.update(dto);
    }

    //http://localhost:8080/pessoa/9
    @DeleteMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> delete(@Valid @PathVariable ("id") Long id){
           service.delete(id);
           return ResponseEntity.noContent().build();

    }

    }



