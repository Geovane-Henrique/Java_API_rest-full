package com.geo.pessoa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonFilter {

    String startName;
    String endName;
    String containName;

    String gender;

    Integer minAge;
    Integer maxAge;

}
