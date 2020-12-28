package com.yws.handlers;

import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yws.enties.Address;
import com.yws.enties.User;

@SessionAttributes(value = {"user"}, types = {String.class})
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
	
	/**
	 * 目标方法的返回值可以是ModeAndView类型
	 * 其中可以包含视图和模型信息
	 * SpringMVC 会把ModelAndView的model中数据放入到request域对象中。
	 * @return
	 */
	@RequestMapping(value = "/testModeAndView", method = RequestMethod.GET)
	public ModelAndView testModeAndView() {
		ModelAndView modelAndView = new ModelAndView(SUCCESS);
		//添加模型数据到ModeAndView中
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}
	
	/**
	 * 目标方法可以添加Map类型(实际上也可以是Model或ModelMap类型)的参数
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/testMap", method = RequestMethod.GET)
	public String testMap(Map<String, Object> map) {
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("tom", "jack", "lucy"));
		return SUCCESS;
	}
	
	
	/**
	 * @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外（实际上使用的是value属性值），
	 * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中（实际上使用的 是type属性值）
	 * 
	 * 注意：该注解只能放在类上面，而不能修饰方法
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/testSessionAttribute", method = RequestMethod.GET)
	public String testSessionAttribute(Map<String, Object> map) {
		User user = new User("张三", "13456", 11, new Address("fujian", "xiamen"));
		map.put("user", user);
		map.put("school", "yws");
		System.out.println("testSessionAttribute");
		return SUCCESS;
	}
	
	/**
	 * 1.有 @ModelAttribute 标记的方法，会在每个目标方法执行之前被springMVC调用！
	 * 2. @ModelAttribute 注解也可以来修饰目标方法POJO类型的入参，其value属性值有如下的作用： 
	 * 		1）SpringMVC会使用value属性值在implicitModel中查找对应的对象，若存在则会直接传入到目标方法的入参中。
	 * 		2）SpringMVC会以value属性值为key，POJO类型的对象为value，存入到request中。
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id",required = false) Integer id, Map<String, Object> map) {
		System.out.println("modelAttribute method");
		if (id != null) {
			User user = new User(1, "zs", "134", 21);
			System.out.println("从数据库中获取一个对象：" + user);
			map.put("user", user);
		}
	}
	
	/**
	 * 运行流程：
	 * 1.执行 @ModelAttribute 注解修饰的方法：从数据库中取出对象，把对象放入到了Map中。key为user
	 * 2.SpringMVC从map中取出User对象，并把表单的请求参数赋值给User对象对应的属性
	 * 3.SpringMVC把上述对象传入目标方法的参数
	 * 
	 * 注意：在 @ModelAttribute 修饰的方法中，放入到map时的键需要和目标方法的入参类型的第一个字母小写的字符串一致。
	 * 
	 * SpringMVC 确定目标方法POJO类型入参的过程
	 * 1.确定一个key：
	 * 		1）如果目标方法的POJO类型的参数没有使用 @ModelAttribute 作为修饰，则key为POJO类名的第一个字母小写 
	 * 		2）若使用了 @ModelAttribute 类修饰，则key为  @ModelAttribute 注解的value属性值。
	 * 2.在implicitModel中查找key对应的对象，若存在，则作为入参传入
	 * 		1）若在 @ModelAttribute 标记点方法中在Map中保存过，且key和1确定的key一致，则会获取到。
	 * 3.若implicitModel不存在key对应的对象，则检查当前Handler是否使用 @SessionAttributes 注解修饰，
	 * 		若使用了该注解，且 @SessionAttributes 注解的value属性值包含了key，则会从HttpSession中来获取key所对应的value值：
	 * 			若存在则直接传入目标方法的入参中。
	 * 			若不存在则将抛出异常。（@SessionAttributes 的value值包含了1的key的情况 ）
	 * 4.若没有标识  @SessionAttributes 或者  @SessionAttributes 注解的value值中不包含key，
	 * 		则会通过反射来创建POJO类型的参数，传入为目标方法的参数
	 * 5，SpringMVC会把key 和POJO类型的对象保存到implicitModel中，进而保存到request中。
	 * 
	 * 源代码分析的流程：
	 * 1.调用 @ModelAttribute 注解修饰的方法。实际上把 @ModelAttribute 方法中Map中的数据放在了implicitModel中。
	 * 2.解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder对象的target属性
	 * 	1）创建WebDataBinder对象：
	 * 		①.确定objectName属性：若传入的attrName属性值为“”，则objectName为类名第一个字母小写。
	 * 		*注意：attrName 如目标方法的Pojo属性使用了 @ModelAttribute 来修饰，则attrName值即为  @ModelAttribute 的value属性值
	 * 		②.确定target属性
	 * 			>在implicitModel中查找attrName对应的属性值。若存在，ok
	 * 			>*若不存在：则验证当前Handler是否使用了 @SessionAttributes 进行修饰，
	 * 						若使用了，则尝试从Session中获取attrName所对应的属性值。
	 * 						若session中没有对应的属性值，则抛出了异常。
	 * 			>若Handler 没有使用  @SessionAttributes 进行修饰，或 @SessionAttributes 中没有使用value值指定的key 和  attrName相匹配，
	 * 				则通过反射创建POJO对象
	 * 	2）SpringMVC把表单的请求参数赋值给了WebDataBinder的target对象的属性。
	 * 	3）*SpringMVC会把WebDataBinder的attrName和target给到implicitModel。近而传到request域对象中。
	 * 	4）把WebDataBinder的target作为参数传递给目标方法的入参。
	 * 					
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/testModeAttribute")
	public String testModeAttribute(User user) {
		System.out.println("testModeAttribute:" + user);
		return SUCCESS;
	}
	
	
	@RequestMapping(value = "/testViewAndViewResolver")
	public String testViewAndViewResolver() {
		System.out.println("testViewAndViewResolver");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testView")
	public String testView() {
		System.out.println("testView");
		return "helloView";
	}
}
