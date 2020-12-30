package com.yws.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.yws.crud.entities.Employee;

@Component
public class EmployeeConverter implements Converter<String, Employee>{

	@Override
	public Employee convert(String source) {
		if (source != null) {
			String[] vals = source.split("-");
		}
		return null;
	}

}
