<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 	导入Struts 2标签库 -->
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HelloWorld</title>
</head>
<body>
<div>
	<s:fielderror/>
	<s:fielderror fieldName="name"/>
</div>
<!-- <form action="login.action" method="post">
		请输入您的姓名：
		<input name="name" type="text"><br/>
		请输入您的密码
		<input name="password" type="password"/><br/>
		<input type="submit" value="提交"/>
	</form> -->
	<s:form action="login.action" method="post" theme="xhtml">
		<s:textfield name="name" value="xx" label="用户名"></s:textfield>
		<s:password name="password" label="密码"></s:password>
		<s:submit value="提交"/>
	</s:form>
	
</body>
</html>