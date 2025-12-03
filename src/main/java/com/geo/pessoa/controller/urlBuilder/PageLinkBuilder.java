package com.geo.pessoa.controller.urlBuilder;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;


public class PageLinkBuilder {

    public String buildParam(HttpServletRequest request){
        var url = request.getQueryString() == null ? "" : "&" + request.getQueryString() ;
        return validationParam(url);
    }

    public  String buildUrl(HttpServletRequest request, String baseUrl){
        return baseUrl +(request.getQueryString() == null ? "?" : "?" + request.getQueryString() + "&");
    }

    public String validationParam(String param){

        param = param.replaceAll("page=.", "");
        param = param.replaceAll("asc?" ,"asc");

        return param;
    }



}
