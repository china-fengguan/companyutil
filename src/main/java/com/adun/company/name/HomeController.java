package com.adun.company.name;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @RequestMapping(value="/", method=GET)
    public String home() {
    	return "index.html";
    }
}
