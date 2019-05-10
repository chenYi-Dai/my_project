package org.spring.boot.demo.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrControlller implements ErrorController {

    @RequestMapping("/error")
    public String error() {
        System.out.println("ErrControlller.error");
        return "error";
    }

//    @Override
    public String getErrorPath() {
        return "error";
    }
}