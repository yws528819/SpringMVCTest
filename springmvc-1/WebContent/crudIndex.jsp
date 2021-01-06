<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script>
	$(function() {
		$("#testJson").click(function () {
			var url = this.href;
			var args = {};
			$.post(url, args, function(data) {
				if (data) {
					for (var i=0; i<data.length; i++) {
						var id = data[i].id;
						var lastName = data[i].lastName;
						//alert("id:" + id + ",lastName:" + lastName);
					}
				}
			});
			return false;
		});
	});
</script>
</head>
<body>
	<a href="emps">List All Employees</a><br><br>
	
	<a id="testJson" href="testJson">Test Json</a><br><br>
	
	<form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
		File: <input type="file" name="file" /><br>
		Desc: <input type="text" name="desc"/><br>
		<input type="submit" value="Submit"/>
	</form>
	<br>
	
	<a href=testResponseEntity>TestResponseEntity</a><br><br>
		
	<!--  
		关于国际化：
		1.在页面上能够根据浏览器语言设置的情况对文本（不是内容）、时间、数值进行本地化处理
		2.可以在bean中获取国际化资源文件Locale对应的消息
		3.可以通过超链接切换Locale，而不再依赖于浏览器的语言设置情况
		
		解决：
		1.使用JSTL的fmt标签
		2.在bean中注入ResourceBundleMessageSource的实例，使用其对应的getMessage方法即可
		3.配置LocaleResolver和LocaleChangeInterceptor
	-->
	<a href="i18n">i18n PAGE</a><br><br>
	
	<h4>TestUpload</h4>
	<form action="testUpload" method="post" enctype="multipart/form-data">
		File: <input type="file" name="file" /><br>
		Desc: <input type="text" name="desc"/><br>
		<input type="submit" value="Submit"/>
	</form>
	<br>
	
	<a href="testExceptionHandlerExceptionResolver?i=5">Test ExceptionHandlerExceptionResolver</a><br><br>
	<a href="testResponseStatusExceptionResolver?i=5">Test ResponseStatusExceptionResolver</a><br><br>
	<a href="testDefaultHandlerExceptionResolver">Test DefaultHandlerExceptionResolver</a><br><br>
	<a href="testSimpleMappingExceptionResolver?i=10">Test SimpleMappingExceptionResolver</a><br><br>
</body>
</html>