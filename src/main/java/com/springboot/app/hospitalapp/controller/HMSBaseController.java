package com.springboot.app.hospitalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HMSBaseController {
	@GetMapping("/")
	public String homePage() {
		
		return "homePage";
	}
//	@GetMapping("/")
//	 
//	public String doctorLogin() {
//	
//		return "redirect:/doctor/Login";
//		
//	}
//
//	@GetMapping("/")
//	 
//	public String patientLogin() {
//	
//		return "redirect:/patient/Login";
//		
//	}
}
