package com.geo.pessoa.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO extends RepresentationModel<PersonDTO> {

    private Long id;


    @NotBlank(message = "Naõ pode ser vazio")
    private String firstName;

    @NotBlank(message = "Naõ pode ser vazio")
    private String lastName;

    @NotNull(message = "Naõ pode ser nulo")
    private int age;

    @Size(min = 11,max = 11,message = "cpf invalido")
    private String cpf;

    @NotBlank(message = "Naõ pode ser nulo")
    @Size(min = 1,max = 1,message = "deve ser apenas 1 caractere")
    private String gender;
}
