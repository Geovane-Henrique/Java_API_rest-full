package com.geo.pessoa.controller.urlBuilder;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;

import java.util.Optional;


public class PageLinkBuilder {

    public String buildParam(HttpServletRequest request){
        var url = request.getQueryString() == null ? "" : "&" + request.getQueryString() ;
        return validationParam(url);
    }

    public  String buildUrl(HttpServletRequest request, String baseUrl){
        var url = baseUrl +(request.getQueryString() == null ? "" :  request.getQueryString() );
        return validationUrl(url);
    }

    public String validationParam(String param){

        param = param.replaceAll("page=.", "");
        param = param.replaceAll("asc\\?" ,"asc");
        param = param.replaceAll("\\?\\?","?");

        return param;
    }

    public String validationUrl(String url){
         url = url.replaceAll("sort=FirstName,asc","");
        url = url.replaceAll("page=.", "");

        return url;
    }



}
