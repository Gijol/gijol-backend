package com.gist.graduation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectSwagger(){
        return "redirect:/swagger-ui/index.html";
    }
}
