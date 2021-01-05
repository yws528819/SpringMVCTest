package com.yws.handlers;

import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yws.crud.dao.EmployeeDao;
import com.yws.crud.entities.Employee;
import com.yws.exception.UserNameNotMatchPasswordException;

@Controller
public class SpringMVCConvertTest {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;

	@RequestMapping(value = "/testConversionServiceConverer", method = RequestMethod.POST)
	public String testConversionServiceConverer(Employee employee) {
		System.out.println("save:" + employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/testJson", method = RequestMethod.POST)
	public Collection<Employee> testJson() {
		return employeeDao.getAll();
	}
	
	/**
	 * 上传的文件和输入的文本框内容，转成字符串（body）
	 * @param body
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/testHttpMessageConverter", method = RequestMethod.POST)
	public String testHttpMessageConverter(@RequestBody String body) {
		System.out.println(body);
		return "hello world!" + new Date();
	}
	
	/**
	 * 将files/a.txt文件下载到本地
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws Exception{
		byte[] body = null;
		ServletContext servletContext = session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/files/a.txt");
		body = new byte[in.available()];
		in.read(body);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=a.txt");
		
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, statusCode);
		return responseEntity;
		
	}
	
	@RequestMapping(value = "i18n")
	public String testI18n(Locale locale) {
		String message = messageSource.getMessage("i18n.username", null, locale);
		System.out.println(message);
		return "i18n";
	}
	
	
	@RequestMapping(value = "testUpload", method = RequestMethod.POST)
	public String testFileUpload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("desc") String desc) throws Exception{
		System.out.println("desc:" + desc);
		System.out.println("originalFilename:" + multipartFile.getOriginalFilename());
		System.out.println("inputStream:" + multipartFile.getInputStream());
		return "success";
	}
	
	/**
	 * 1.在 @ExceptionHandler 方法的入参中，可以加入 Exception 类型的参数，该参数即对应发生的异常对象
	 * 2.在 @ExceptionHandler 方法的入参中不能传入map。若希望把异常信息传到页面上，需要使用 ModelAndView 作为返回值
	 * 3. @ExceptionHandler 方法标记的异常有优先级问题。
	 * 4. @ControllerAdvice ： 如果在当前Handler中找不到 @ExceptionHandler 方法来处理当前方法出现的异常，
	 * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常。
	 * @param ex
	 * @param map
	 * @return
	 */
//	@ExceptionHandler({ArithmeticException.class})
//	public ModelAndView handlerArithmeticException(Exception ex) {
//		System.out.println("出错了：" + ex);
//		ModelAndView modelAndView = new ModelAndView("error");
//		modelAndView.addObject("exception", ex);
//		return modelAndView;
//	}
//	
//	@ExceptionHandler({RuntimeException.class})
//	public ModelAndView handlerArithmeticException2(Exception ex) {
//		System.out.println("【出错了】：" + ex);
//		ModelAndView modelAndView = new ModelAndView("error");
//		modelAndView.addObject("exception", ex);
//		return modelAndView;
//	}
	
	@RequestMapping(value = "testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(@RequestParam("i") int i) {
		System.out.println("result: " + (10/i));
		return "success";
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "测试")
	@RequestMapping(value = "testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver(@RequestParam("i") int i) {
		if (i == 13) {
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatusExceptionResolver...");
		return "success";
	}
}
