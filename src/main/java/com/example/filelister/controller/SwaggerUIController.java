package com.example.filelister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring REST controller for endpoint / to show Swagger UI.
 */
@Controller
public class SwaggerUIController {

    /**
     * Redirector method.
     * @return The redirect URL.
     */
    @GetMapping("/")
    public String redirectToSwaggerUI() {
        return "redirect:/swagger-ui/index.html";
    }
}