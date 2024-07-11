package com.example.filelister.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring REST controller for endpoint /doc to show Javadoc.
 */
@Controller
public class JavadocController {

    /**
     * Redirector method.
     * @return The redirect URL.
     */
    @GetMapping("/doc")
    public String redirectToJavadoc() {
        return "redirect:/doc/index.html";
    }
}