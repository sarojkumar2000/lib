package com.project.library.Library_Management.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class AppController {

	@RequestMapping("/")
	public String allStudents() {
	
		return "home";
	}
	@RequestMapping("/selectuser")
	public String UserOrAdmin() {
	
		return "login";
	}
}
