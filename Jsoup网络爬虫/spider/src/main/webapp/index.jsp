<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Gentellela Alela! | </title>
  <!-- Bootstrap -->
  <%@include  file="common/css-header.jsp"%>
  <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">
  <!-- Custom Theme Style -->
</head>
<body class="nav-md">
<div class="container body">
  <div class="main_container">
    <%@include  file="common/left.jsp"%>
    <!-- top navigation -->
    <%@include  file="common/top.jsp"%>
    <!-- /top navigation -->
    <!-- page content -->
    <div class="right_col" role="main">
      <div class="clearfix"></div>
      <div class="row" id="rowContent">

      </div>
    </div>
    <footer>
      <div class="pull-right">
        版权归北大青鸟所有 <a href="https://colorlib.com">Colorlib</a>
      </div>
      <div class="clearfix"></div>
    </footer>
  </div>
</div>
<!-- 模态框（Modal） -->
<%@include  file="common/js-header.jsp"%>
</body>
</html>