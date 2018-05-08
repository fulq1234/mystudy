<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <s:form action="listDataTransfer">
    	<s:textfield name="hobbies" label="爱好："/>
    	<s:textfield name="hobbies" label="爱好："/>
    	<s:textfield name="hobbies" label="爱好："/>
    	
    	<s:textfield name="numbers[0]" label="数字："/>
    	<s:textfield name="numbers[1]" label="数字："/>
    	<s:textfield name="numbers[2]" label="数字："/>
    	
    	<s:textfield name="users.name" label="姓名："/>
    	<s:textfield name="users.name" label="姓名："/>
    	<s:textfield name="users.name" label="姓名："/>
    	<s:submit value="提交"/>
    </s:form>
  </body>
</html>
