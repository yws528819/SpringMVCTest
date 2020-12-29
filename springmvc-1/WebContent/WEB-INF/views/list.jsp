<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Insert title here</title>
</head>
<body>

	<c:if test="${empty requestScope.emps }">
		没有任何员工信息
	</c:if>
	<c:if test="${!empty requestScope.emps }">
		<table border="1" cellpadding="1" cellspacing="1">
			<tr>
				<th>ID</th>
				<th>LastName</th>
				<th>Email</th>
				<th>Gender</th>
				<th>Department</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${requestScope.emps }" var="emp">
				<tr>
					<td>${emp.id }</td>
					<td>${emp.lastName }</td>
					<td>${emp.email }</td>
					<td>${emp.gender == '0' ? 'Femail' : 'Mail' }</td>
					<td>${emp.department.departmentName }</td>
					<td><a href="">Edit</a></td>
					<td><a href="">Delete</a></td>
				</tr>
				
			</c:forEach>
		</table>
	
	</c:if>
	<a href="emp">Add New Employee</a>
</body>
</html>