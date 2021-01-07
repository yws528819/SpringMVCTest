package com.yws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloHandler {
	
	@Autowired
	private UserService userService;
	
	public HelloHandler() {
		System.out.println("HelloHandler Construct...");
	}
	
	@RequestMapping(value = "/hello")
	public String hello() {
		System.out.println("success");
		System.out.println(userService);
		return "success";
	}
}
