package com.yws.crud.handlers;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String save(Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}
}
