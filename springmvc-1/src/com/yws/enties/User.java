package com.yws.enties;

public class User {
	private Integer id;
	private String username;
	private String password;
	private Integer age;
	private Address address;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	@Override
//	public String toString() {
//		return "User [username=" + username + ", password=" + password + ", age=" + age + ", address=" + address + "]";
//	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", age=" + age + "]";
	}
	
	public User() {
	}
	
	public User(String username, String password, Integer age, Address address) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.address = address;
	}
	public User(Integer id, String username, String password, Integer age) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
	}
	
}
