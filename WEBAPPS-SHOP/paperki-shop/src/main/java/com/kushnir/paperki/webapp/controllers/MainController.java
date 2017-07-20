package com.kushnir.paperki.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping()
    public String welcomeHandler(Model model) {
        return "/WEB-INF/templates/index.html";
    }

}