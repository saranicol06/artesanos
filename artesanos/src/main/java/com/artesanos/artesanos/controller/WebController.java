package com.artesanos.artesanos.controller;

import com.artesanos.artesanos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String home(Model model) {
        // añade productos al model para la vista
        return "index";
    }
}
