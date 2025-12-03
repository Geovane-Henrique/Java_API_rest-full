package com.geo.pessoa.controller;

import com.geo.pessoa.config.PersonConfigPageable;
import com.geo.pessoa.controller.urlBuilder.PageLinkBuilder;
import com.geo.pessoa.dto.PersonDTO;
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@Valid @PathVariable("id") Long id){
        return service.findById(id);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<EntityModel<PersonDTO>> findAll(@PageableDefault(page = 0,size = 4) Pageable pageable , HttpServletRequest request){

        var page =  service.findAll(pageable);

        String url = linkBuilder.buildUrl(request,"");
        String param = linkBuilder.buildParam(request);
        return model.pagedModel(pageable,page, url, param);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE ,
                                   produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO create(@Valid @RequestBody PersonDTO dto){
        return service.create(dto);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO update(@Valid @RequestBody PersonDTO dto){
        return service.update(dto);
    }

    @DeleteMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> delete(@Valid @PathVariable ("id") Long id){
           service.delete(id);
           return ResponseEntity.noContent().build();

    }

    //filtros

    @GetMapping(value = "/filtro/nome/start-end",produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<EntityModel<PersonDTO>> findByNameStarEnd(@RequestParam() String start, @RequestParam String end, @PageableDefault(size = 4) Pageable pageable, HttpServletRequest request)
    {
        var page = service.findByNomeStartingWithIgnoreCaseAndNomeEndingWithIgnoreCase(pageable , start , end);
        String url = linkBuilder.buildUrl(request,"/filtro/nome/start-end");
        String param = linkBuilder.buildParam(request);
        return model.pagedModel(pageable,page,url ,param);
    }

    }



