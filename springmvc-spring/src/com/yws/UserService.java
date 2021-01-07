package com.yws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
//	@Autowired
//	private HelloHandler helloHandler;
	
	public UserService() {
		System.out.println("UserService Construct...");
	}
}
