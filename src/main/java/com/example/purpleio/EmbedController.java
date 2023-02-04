package com.example.purpleio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmbedController {

    @GetMapping("/")
    public String showMainPage() {
        return "index";
    }
}
