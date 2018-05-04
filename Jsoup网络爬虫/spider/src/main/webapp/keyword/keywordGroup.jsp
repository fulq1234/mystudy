<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<div class="">
  <div class="x_panel">
    <div class="x_title">
      <h2>列表</h2>
      <ul class="nav navbar-right panel_toolbox">
        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Settings 1</a>
            </li>
            <li><a href="#">Settings 2</a>
            </li>
          </ul>
        </li>
        <li><a class="close-link"><i class="fa fa-close"></i></a>
        </li>
      </ul>
      <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form class="form-horizontal form-label-left input_mask" role="form">
            <div class="form-group">
                <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                    <label for="name">关键词</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="请输入">
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                    <label for="dataType">数据类型</label>
                    <select name="dataType" id="dataType" class="select2_single form-control">
                    </select>
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                    <label for="taskId">爬虫任务</label>
                    <select name="taskId" id="taskId" class="select2_single form-control"></select>
                </div>
            </div>
        </form>
      <div class="form-inline pull-left">
        <div class="form-group" style="margin-left: 5px;">
          <button type="button" class="btn btn-primary" id="search">统计</button>
          <button type="button" class="btn btn-primary" id="export">导出</button>
          <button type="button" class="btn btn-primary" id="generateEchart">生成图表</button>
        </div>
      </div>
      <table id="table"></table>
    </div>
  </div>
</div>
<script>
    var pageNumber=null;
    var pageSize=null;
    $(function () {
        queryDataType();
        queryTask();
    });
   var table=$('#table').bootstrapTable({
        url: '${ctx}/client/keyword/statisticsByParam',
        sidePagination:"server",
        pageList: [10, 25, 50, 100],
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination:true,//是否分页
        queryParamsType:'limit',//查询参数组织方式
        pageSize:10,//单页记录数
        pageList:[5,10,20,30],//分页步进值
        clickToSelect: true,//是否启用点击选中行
        toolbarAlign:'right',//工具栏对齐方式
        buttonsAlign:'right',//按钮对齐方式
        showColumns:true,
        showRefresh:true,
        searchAlign:'right',
        checkboxHeader:true,
        columns: [{
            checkbox:true,
            clickToSelect:true,
        },
        {
            field: 'name',
            title: '技能'
        },
        {
            field: 'count',
            title: '数目'
        },
        {
            field: 'percent',
            title: '占比'
        }],
        queryParams: function queryParams(params) {  //设置查询参数
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                pageSize: params.limit,   //页面大小
                pageNo:(params.offset/params.limit)+1,
                name:$("#name").val(),
                dataType:$("#dataType").val(),
                taskId:$("#taskId").val(),
            };
            pageNumber=(params.offset/params.limit)+1;
            pageSize=params.limit;
            return temp;
        },
        responseHandler:function (res) {
            return {"rows":res.rows,"total":res.total};
        },
    });
    $("#search").click(function () {
       $('#table').bootstrapTable('refresh', null);
    });
    $("#generateEchart").click(function () {
        var name=$("#name").val();
        var dataType=$("#dataType").val();
        var taskId=$("#taskId").val();
        var url="${ctx}/keyword/keywordChart.jsp?name="+name+"&dataType="+dataType+"&taskId="+taskId+"&pageNumber="+pageNumber+"&pageSize="+pageSize;
        window.ajaxDialog({
            title:"关键词统计图",
            width:"1000px",
            height:"500px",
            url:url,
        });
    });
    $("#export").click(function () {
        var name=$("#name").val();
        var dataType=$("#dataType").val();
        var taskId=$("#taskId").val();
        window.open("${ctx}/client/keyword/exportDataExcel?name="+name+"&dataType="+dataType+"&taskId="+taskId);
    });
    function queryDataType() {
        $.ajax({
            type: 'POST',
            url: '${ctx}/queryDataType',
            dataType:"json",
            success: function (result) {
                var options="<option value=''>请选择</option>";
                for(var i=0;i<result.data.length;i++){
                    options=options+"<option value="+result.data[i].id+">"+result.data[i].typeName+"</option>";
                }
                $("#dataType").html(options);
            }
        });
    }
    function queryTask() {
        $.ajax({
            type: 'POST',
            url: '${ctx}/client/crawlerTask/queryCrawlerTaskList',
            dataType:"json",
            success: function (result) {
                var options="<option value=''>请选择</option>";
                if(result.status=="0"){
                    for(var i=0;i<result.data.length;i++){
                        options=options+"<option value="+result.data[i].id+">"+result.data[i].taskName+"</option>";
                    }
                    $("#taskId").html(options);
                }
            }
        });
    }
</script>