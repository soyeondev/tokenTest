package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.DemoDto;
import com.example.demo.service.DemoService;

@Controller
public class DemoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	public DemoService demoService;
	
	@GetMapping(value = {"", "/"})
	public String index(@RequestParam String code, Model model) {
		LOGGER.info("index {} ", code);
		
		// 서비스로 가서 서비스에서 처리
		DemoDto demoDto = demoService.unirest(code);
		
		model.addAttribute("code", code);
		model.addAttribute("demoDto", demoDto);
		
		return "test";
	}
	
	@PostMapping("/access_token")
	public String accessToken(@RequestParam String code, Model model) {
		
		model.addAttribute("code", code);
		
		return "success";
	}
}