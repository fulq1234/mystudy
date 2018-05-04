<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<div class="">
  <div class="x_panel">
      <ul id="myTab" class="nav nav-tabs  disabled">
          <li class="active">
              <a href="#rule" data-toggle="tab">
                  爬虫规则管理
              </a>
          </li>
          <li><a href="#node1" data-toggle="pill" >列表页规则配置</a></li>
          <li><a href="#node2" data-toggle="pill" >内容页规则配置</a></li>
      </ul>
      <div id="myTabContent" class="tab-content">
          <div class="tab-pane fade in active" id="rule">

          </div>
          <div class="tab-pane fade " id="node1">

          </div>
          <div class="tab-pane fade" id="node2">

          </div>
      </div>
  </div>
</div>
<script>
   //加載tab的ifream
   function tabLoadContent(divId,url) {
       $.ajax({
           type: 'GET',
           url: url,
           async: false,
           success: function (result) {
               $("#"+divId).html(result);
           }
       });
   }
    //弹出层tab
   tabLoadContent("rule","${ctx}/crawlerRules/crawlerRulesList.jsp")
</script>