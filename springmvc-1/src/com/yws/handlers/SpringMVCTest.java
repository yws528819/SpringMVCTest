package com.yws.handlers;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yws.enties.User;

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
	
	
	/**
	 * Rest风格的URL
	 * 以CRUD为例：
	 * 新增： /order  POST  
	 * 修改：/order/1 PUT    update?id=1
	 * 获取：/order/1 GET    get?id=1
	 * 删除：/order/1 DELETE delete?id=1
	 * 
	 * 如何发送PUT请求和DELETE请求?
	 * 1.需要配置HiddenHttpMethodFilter
	 * 2.需要发送POST请求
	 * 3.需要在发送POST请求时携带一个name="_method"的隐藏域，值为DELETE或PUT
	 * 
	 * 在SpringMVC的目标方法中如何得到id呢？
	 * 使用 @PathVariable 注解
	 */
	@RequestMapping(value = "/rest/{id}", method = RequestMethod.GET)
	public String testRest(@PathVariable Integer id) {
		System.out.println("testRest:get请求" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/rest", method = RequestMethod.POST)
	public String testRestPost() {
		System.out.println("testRest:post请求");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/rest/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id) {
		System.out.println("testRest:put请求" + id);
		return "redirect:/success.jsp";
	}
	
	@RequestMapping(value = "/rest/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id) {
		System.out.println("testRest:delete请求" + id);
		return "redirect:/success.jsp";
	}
	
	
	/**
	 * @RequestParam 来映射请求参数
	 * value 值即请求参数的参数名
	 * required 该参数是否必须。 默认为true
	 * defaultValue 请求参数的默认值
	 * @param username
	 * @param age
	 * @return
	 */
	@RequestMapping(value = "/testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String username, @RequestParam(value = "age", required = false) Integer age) {
		System.out.println("testRequestParam: username：" + username + ",age:" + age);
		return SUCCESS;
	}
	
	/**
	 * 映射请求头信息（了解）
	 * 用法同 @RequestParam
	 * @param al
	 * @return
	 */
	@RequestMapping(value = "/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
		System.out.println("testRequestHeader，Accept-Language："+ al);
		return SUCCESS;
	}
	
	/**
	 * 了解：
	 * @CookieValue：映射一个cookie值。属性同@RequestParam
	 * @param jsessionId
	 * @return
	 */
	@RequestMapping(value = "/testCookieValue")
	public String testCookieValue(@CookieValue(value = "JSESSIONID") String jsessionId) {
		System.out.println("testCookieValue：" + jsessionId);
		return SUCCESS;
	}
	
	/**
	 * springMVC会按请求参数名和POJO属性名自动匹配，自动为该对象填充属性值。支持级联属性。
	 * 如：dept.deptId, dept.address.tel等
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/testPojo", method = RequestMethod.POST)
	public String testPojo(User user) {
		System.out.println("testPojo:" + user);
		return SUCCESS;
		
	}
	
	/**
	 * 可以使用Servelt 原生的API 作为目标方法的参数
	 * 具体支持以下类型
	 * HttpServletRequest 
	 * HttpServletResponse
	 * HttpSession 
	 * java.security.Principal 
	 * Locale 
	 * InputStream 
	 * OutputStream 
	 * Reader
	 * Writer
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "testServletAPI")
	public void testServletAPI(HttpServletRequest request, HttpServletResponse response, Writer out) throws Exception {
		System.out.println("testServletAPI: " + request + "," + response);
		out.write("hello,springMVC");
		//return SUCCESS;
	}
}
