<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="springmvc/rest/1" method="post">
		<input type="submit" value="删除">
		<input type="hidden" name="_method" value="DELETE">
	</form>
	<form action="springmvc/rest/1" method="post">
		<input type="submit" value="修改">
		<input type="hidden" name="_method" value="PUT">
	</form>
	<form action="springmvc/rest" method="post">
		<input type="submit" value="新增">
	</form>
	
	<a href="springmvc/rest/1">查询</a> <br/><br/>
	
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