package com.yws;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloHandler {
	public HelloHandler() {
		System.out.println("HelloHandler Construct...");
	}
	
	@RequestMapping(value = "/hello")
	public String hello() {
		System.out.println("hello");
		return "success";
	}
}
