package com.geo.pessoa.config;

import com.geo.pessoa.controller.PersonController;
import com.geo.pessoa.dto.PersonDTO;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonConfigPageable implements RepresentationModelAssembler<PersonDTO, EntityModel<PersonDTO>>{

    @Override
    public EntityModel<PersonDTO> toModel(PersonDTO dto){
        return EntityModel.of(dto,
                linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withRel("GET"),
               linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

    public PagedModel<EntityModel<PersonDTO>> pagedModel(Pageable pageable, Page<PersonDTO> page, String url, String param){

        List<EntityModel<PersonDTO>> content = page.getContent().stream()
                .map(this::toModel)
                .toList();


        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );


        PagedModel<EntityModel<PersonDTO>> model = PagedModel.of(content,metadata);




        model.add(linkTo(PersonController.class).slash("").withRel("findAll").withType("GET"));
        model.add(linkTo(PersonController.class).slash(url + "?sort=FirstName,asc").withRel("find").withType("GET"));


        System.out.println("param =" + param);
        System.out.println("nUrl =" + url);


        if(page.hasPrevious()){
            int prev = page.getNumber() - 1;
            model.add(linkTo(PersonController.class).slash(url +"?page=" + prev + param).withRel("prev"));
        }
        if(page.hasNext()){
           int next = page.getNumber() + 1;
           model.add(linkTo(PersonController.class).slash(url + "?page=" + next + param).withRel("next"));
        }


        return model;

    }
}
