package com.yws.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	/**
	 * 1.请求地址应为：/springmvc/testParamsAndHeaders?username=admin&age=11,参数不完整或者值不对都不能访问
	 * 2.请求头应为：Accept-Language: en-US,zh;q=0.9
	 * 
	 * 了解：可以使用params和headers来更加精确的映射请求，params和headers支持简单的表达式
	 * @return
	 */
	@RequestMapping(value = "/testParamsAndHeaders", params = {"username", "age!=10"}, headers = {"Accept-Language: en-US,zh;q=0.9"})
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	/**
	 * 支持ant风格资源地址
	 * @return
	 */
	@RequestMapping(value = "/testAntPath/*/abc")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	/**
	 * @PathVariable 可以用来映射url中的占位符到目标方法的参数中
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/testPathVariable/{id}")
	public String testPathVariable(@PathVariable Integer id) {
		System.out.println("testPathVariable：" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "rest/{id}", method = RequestMethod.GET)
	public String testRest1(@PathVariable Integer id) {
		System.out.println("testRest:get请求" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "rest", method = RequestMethod.POST)
	public String testRest2() {
		System.out.println("testRest:post请求");
		return SUCCESS;
	}
	
	@RequestMapping(value = "rest/{id}", method = RequestMethod.PUT)
	public String testRest3(@PathVariable Integer id) {
		System.out.println("testRest:put请求" + id);
		return "redirect:/success.jsp";
	}
	
	@RequestMapping(value = "rest/{id}", method = RequestMethod.DELETE)
	public String testRest4(@PathVariable Integer id) {
		System.out.println("testRest:delete请求" + id);
		return "redirect:/success.jsp";
	}
}
