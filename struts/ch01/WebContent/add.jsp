<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:property value="message"/>

<s:set name="age" value="10" scope="request"/>  
<s:set name="username" value="'jason'" scope="session"/><!--如果value里面的值没有单引号，就代表寻找一个jason属性，而不是jason字符串。-->  
<s:set name="count" value="5" scope="application"/>  
#request.age:<s:property value="#request.age"/><br/>  
#session.username:<s:property value="#session.username"/><br/>  
#application.count:<s:property value="#application.count"/><br/>  
#attr.count:<s:property value="#attr.count"/><br/>  
<s:set name="country1" value="'China'"/>  
<s:set name="country2" value="'China'"/>  
#country1:<s:property value="#country1"/><br/>  
#country2:<s:property value="#country2"/><br/>  
#request.country2:<s:property value="#request.country2"/><br/>  
<s:debug/>

<s:set name="myurl" value="'http://www.baidu.com'"/>
输出1：<s:property value="%{#myurl}"/>
<br/>
输出2:<s:url value="%{#myurl}">
<s:param name="id" value="'123'"/>
<s:param name="name" value="'admin'"/>
</s:url>

<br/>

<s:date name="timeDate" format="yyyy/MM/dd" id="today"/><!-- 在上下文中增加一个非值栈的today对象 -->
<s:property value="today"/><!-- 显示2018/05/09 -->

</body>
</html>