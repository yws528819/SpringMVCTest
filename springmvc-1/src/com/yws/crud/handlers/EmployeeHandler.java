package com.yws.crud.handlers;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yws.crud.dao.DepartmentDao;
import com.yws.crud.dao.EmployeeDao;
import com.yws.crud.entities.Employee;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao DepartmentDao;
	
	@RequestMapping(value = "/emps")
	public String list(Map<String, Object> map) {
		Collection<Employee> emps = employeeDao.getAll();
		map.putIfAbsent("emps", emps);
		return "list";
	}
	
	@RequestMapping(value = "/emp")
	public String input(Map<String, Object> map) {
		map.put("departments", DepartmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}
	
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String save(Employee employee, BindingResult result) {
		System.out.println(employee);
		
		if (result.getErrorCount() > 0) {
			System.out.println("出错了");
			result.getFieldErrors().stream().forEach( e -> {
				System.out.println(e.getField() + ":" + e.getDefaultMessage());
			});
		}
		
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	@RequestMapping(value = "/emp/{id}")
	public String emp(@PathVariable("id") Integer id, Map<String, Object> map) {
		Employee employee = employeeDao.get(id);
		map.put("employee", employee);
		map.put("departments", DepartmentDao.getDepartments());
		return "input";
	}
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("employee", employeeDao.get(id));
		}
	}
	
	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public String update(Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("lastName");
	}
}
