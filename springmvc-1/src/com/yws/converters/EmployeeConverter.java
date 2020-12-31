package com.yws.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.yws.crud.entities.Department;
import com.yws.crud.entities.Employee;

@Component
public class EmployeeConverter implements Converter<String, Employee>{

	@Override
	public Employee convert(String source) {
		if (source != null) {
			String[] vals = source.split("-");
			//GG-gg@133.com-0-105
			String lastName = vals[0];
			String email = vals[1];
			Integer gender = Integer.valueOf(vals[2]);
			Department department = new Department();
			department.setId(Integer.valueOf(vals[3]));
			Employee employee = new	Employee(null, lastName, email, gender, department);
			System.out.println(source + "--convert--" + employee);
			return employee;
		}
		return null;
	}

}
