package com.assit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assit.Servcie.DeathCounterService;

@Controller
public class HelloController {
	@Autowired
	private DeathCounterService service;
	
    @RequestMapping("/hello")
    @ResponseBody
    String home() {
    	service.setBirthDay("test", "19891022");
    	service.getLeftSeconds("test");
        return "Hello World!";
    }
}
