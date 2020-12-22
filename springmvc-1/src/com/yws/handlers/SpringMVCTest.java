package com.yws.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	private static final String SUCCESS = "success";
	
	/**
	 * 1. @RequestMapping 除了修饰方法，还可以修饰类
	 * 2. 
	 * （1）类定义处：提供初步的请求映射信息。相对于WEB应用的根目录
	 * （2）方法处：   提供进一步的细分映射信息。相对于类定义处的URL。若类定义处未标注  @RequestMapping，
	 * 			则方法处标记的URL 相对于 WEB 应用的根目录
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
	
	
	/**
	 * 常用：使用method属性来指定请求方式
	 * @return
	 */
	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMehtod() {
		System.out.println("testMethod");
		return SUCCESS;
	}
	
}
