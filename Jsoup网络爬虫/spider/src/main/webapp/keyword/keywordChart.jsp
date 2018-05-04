<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
  response.setHeader("Access-Control-Allow-Origin", "*");
%>
<div id="main" style="width: 900px;height:400px;margin: auto;"></div>
<script type="text/javascript">
    var keyArray=new Array();
    var valueArray=new Array();
    //初始化柱状图
    var temp={
        pageSize: ${param.pageSize},   //页面大小
        pageNo:${param.pageNumber},
        name:'${param.name}',
        dataType:'${param.dataType}',
        taskId:'${param.taskId}',
    };
    $(function () {
        eChartByParam();
    });
    function eChartByParam() {
        $.ajax({
            type: 'POST',
            url: '${ctx}/client/keyword/statisticsByParam',
            dataType:"json",
            data:temp,
            success: function (result) {
                var rows=result.rows;
                for(var i=0;i<rows.length;i++){
                    var data=rows[i];
                    keyArray.push(data.name);
                    valueArray.push(data.percent);
                }
                initChart("main",'技能点排行',keyArray,valueArray);
            }
        });
    }
</script>