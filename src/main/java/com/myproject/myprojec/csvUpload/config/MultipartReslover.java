//package com.myproject.myprojec.csvUpload.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//
//
//public class MultipartReslover {
//    @Bean
//    public MultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver
//                = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(5242880);
//        return multipartResolver;
//    }
//}