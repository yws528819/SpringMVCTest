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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yws.crud.dao.EmployeeDao;
import com.yws.crud.entities.Employee;

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
	
	
	@ExceptionHandler({ArithmeticException.class})
	public String handlerArithmeticException(Exception ex, Map<String, Object> map) {
		System.out.println("出错了");
		map.put("exception", ex);
		return "error";
	}
	
	@RequestMapping(value = "testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(@RequestParam("i") int i) {
		System.out.println("result: " + (10/i));
		return "success";
	}
}
