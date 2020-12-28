<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="springmvc/testRedirect">testRedirect</a> <br/><br/>
	
	<a href="springmvc/testView">testView</a> <br/><br/>
	
	<a href="springmvc/testViewAndViewResolver">Test ViewAndViewResolver</a> <br/><br/>

	<form action="springmvc/testModeAttribute" method="post">
		<input type="hidden" name="id" value="1">
		username: <input type="text" name="username" value="zs"/><br>
		email: <input type="text" name="email" value="123@123.com"/><br>
		age: <input type="text" name="age" value="20"/><br>
		<input type="submit" value="submmit(testModeAttribute)" />
	</form>
	<br>

	<a href="springmvc/testSessionAttribute">testSessionAttribute</a> <br/><br/>
	
	<a href="springmvc/testMap">testMap</a> <br/><br/>
	
	<a href="springmvc/testModeAndView">testModeAndView</a> <br/><br/>
	
	<a href="springmvc/testServletAPI">testServletAPI</a> <br/><br/>

	<form action="springmvc/testPojo" method="post">
		username: <input type="text" name="username" /><br>
		password: <input type="password" name="password" /><br>
		email: <input type="text" name="email" /><br>
		age: <input type="text" name="age" /><br>
		city: <input type="text" name="address.city" /><br>
		provice: <input type="text" name="address.province" /><br>
		<input type="submit" value="submmit" />
	</form>
	<br/>

	<a href="springmvc/testCookieValue">testCookieValue</a> <br/><br/>
	
	<a href="springmvc/testRequestHeader">testRequestHeader</a> <br/><br/>
	
	<a href="springmvc/testRequestParam?username=admin&age=12">testRequestParam</a> <br/><br/>

	<form action="springmvc/rest/1" method="post">
		<input type="submit" value="testRest删除">
		<input type="hidden" name="_method" value="DELETE">
	</form>
	<form action="springmvc/rest/1" method="post">
		<input type="submit" value="testRest修改">
		<input type="hidden" name="_method" value="PUT">
	</form>
	<form action="springmvc/rest" method="post">
		<input type="submit" value="testRest新增">
	</form>
	
	<a href="springmvc/rest/1">testRest查询</a> <br/><br/>
	
	<a href="springmvc/testPathVariable/1">testPathVariable</a> <br/><br/>
	
	<a href="springmvc/testAntPath/123/abc">testAntPath</a> <br/><br/>
	
	<a href="springmvc/testParamsAndHeaders">testParamsAndHeaders</a> <br/><br/>	

	<form action="springmvc/testMethod" method="post">
		<input type="submit"/>
	</form>
	<br/>
	
	<a href="springmvc/testRequestMapping">testRequestMapping</a> <br/><br/>
	<a href="hello">hello</a>
</body>
</html>