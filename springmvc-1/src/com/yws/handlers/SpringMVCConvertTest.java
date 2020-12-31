package com.yws.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yws.crud.dao.EmployeeDao;
import com.yws.crud.entities.Employee;

@Controller
public class SpringMVCConvertTest {
	
	@Autowired
	private EmployeeDao employeeDao;

	@RequestMapping(value = "/testConversionServiceConverer", method = RequestMethod.POST)
	public String testConversionServiceConverer(Employee employee) {
		System.out.println("save:" + employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
}
