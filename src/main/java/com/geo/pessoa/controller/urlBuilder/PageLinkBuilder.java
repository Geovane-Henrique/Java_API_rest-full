package com.geo.pessoa.controller.urlBuilder;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;

import java.util.Optional;


public class PageLinkBuilder {

    public String buildParam(String request){
        var url = request == null ? "" : "&" + request ;
        return validationParam(url);
    }

    public String validationParam(String param){

        param = param.replaceAll("page=.", "");

        return param;
    }

}
