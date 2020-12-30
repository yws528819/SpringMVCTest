<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Insert title here</title>
<!-- 
	SpringMVC处理静态资源：
	1.为什么会有这样的问题？
	优雅的REST风格资源URL，不希望带.do或者.html等后缀
	若将DispatcherServlet请求映射配置为/，
	则SpringMVC将捕获WEB容器的所有请求，包括静态资源的请求，SpringMVC会将他们当成一个普通的请求处理，
	因找不到对应处理器将报错。
	2.解决：在SpringMVC的配置文件中配置 <mvc:default-servlet-handler />
	
 -->
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script>
	$(function() {
		$(".delete").click(function() {
			var href = $(this).attr("href");
			$("form").attr("action", href).submit();
			return false;
		});
	})
</script>
</head>
<body>
	<form action="" method="post">
		<input type="hidden" name="_method" value="DELETE" />
	</form>

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
					<td><a href="emp/${emp.id }">Edit</a></td>
					<td><a class="delete" href="emp/${emp.id }">Delete</a></td>
				</tr>
				
			</c:forEach>
		</table>
	
	</c:if>
	<br>
	<a href="emp">Add New Employee</a>
</body>
</html>