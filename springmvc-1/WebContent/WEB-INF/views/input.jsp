<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		1.WHY使用form标签?
		可以更快速的开发出表单页面，而且可以更方便的进行表单值的回显
		2.注意：
		可以通过 modelAttribute属性指定绑定的模型属性，
		若没有指定该属性，则默认从request域中读取command的表单bean，如果该属性值也不存在，则会发生错误。
	 -->
	 <form:form action="emp" method="POST" modelAttribute="employee">
	 	<!-- path属性对应html表单标签的name属性值 -->
	 	lastName: <form:input path="lastName"/><br>
	 	email: <form:input path="email"/><br>
	 	<%
	 		Map<String, String> genders = new HashMap();
	 		genders.put("0", "Femail");
	 		genders.put("1", "Mail");
	 		request.setAttribute("genders", genders);
	 	%>
	 	gender: <form:radiobuttons path="gender" items="${genders }"/><br>
	 	department: <form:select path="department" 
	 		items="${departments }" itemLabel="departmentName" itemValue="id"></form:select><br>
	 	<input type="submit" value="Submit">
	 </form:form>
</body>
</html>