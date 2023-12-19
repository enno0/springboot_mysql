package com.enno.controllers;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@Service
@ControllerAdvice
public class ExceptionContr {
    @ExceptionHandler(value = Exception.class)
    public String HandleException(HttpServletRequest request, Exception exception) {
        System.out.println(request.getRequestURI() + ": " + exception);
        String mm = exception.getMessage();
        System.out.println(mm);

        return "/exception";
    }

}
