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
                <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                    <label for="status">状态</label>
                    <select name="status" id="status" class="select2_single form-control">
                        <option value="">请选择</option>
                        <option value="0">未删除</option>
                        <option value="1">已删除</option>
                    </select>
                </div>
            </div>
        </form>
      <div class="form-inline pull-left">
        <div class="form-group" style="margin-left: 5px;">
          <button type="button" class="btn btn-primary" id="search">搜索</button>
          <button type="button" class="btn btn-danger" id="remove">删除</button>
          <button type="button" class="btn btn-danger" id="removeByCondition">按条件删除</button>
          <button type="button" class="btn btn-danger" id="clearAll">全部清空</button>
        </div>
      </div>
      <table id="table"></table>
    </div>
  </div>
</div>
<script>
    $(function () {
        queryDataType();
        queryTask();
    });
   var table=$('#table').bootstrapTable({
        url: '${ctx}/client/keyword/search',
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
            field: 'typeName',
            title: '数据类型',
        },
        {
            field: 'taskName',
            title: '任务名称'
        },
            {
                field: 'status',
                title: '状态',
                width:"60px",
                formatter:function (value,row,index) {
                    if(value=='0'){
                        return '未删除';
                    }else if(value=='1'){
                        return '已删除';
                    }
                }
            },
        {
            title: '操作',
            width:"100px",
            formatter:function (value,row,index) {
                var view="<a href='#' class='btn btn-info btn-xs' onclick=\"editKeyword('"+row.id+"')\"><i class='fa fa-pencil'></i> 查看 </a>";
                return view;
            }
        }],
        queryParams: function queryParams(params) {  //设置查询参数
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                pageSize: params.limit,   //页面大小
                pageNo:(params.offset/params.limit)+1,
                sortOrder: params.order,
                name:$("#name").val(),
                dataType:$("#dataType").val(),
                taskId:$("#taskId").val(),
                status:$("#status").val()
            };
            return temp;
        },
        responseHandler:function (res) {
            return {"rows":res.rows,"total":res.total};
        },
    });
   $("#search").click(function () {
       $('#table').bootstrapTable('refresh', null);
   });
   $("#add").click(function () {
       window.loadContent("${ctx}/keyword/addKeyword.jsp",null);
   });
   $("#remove").click(function () {
      var ids="";
      var selectItems=$('#table').bootstrapTable('getSelections', null);
      for (var i=0;i<selectItems.length;i++){
          ids=ids+","+selectItems[i].id;
      }
      if(ids==''){
          alert({"title":"提示","content":"请选择要删除的数据"});
      }else{
          confirm({
              title:"批量删除确认",
              content:"请确认是否要执行批量删除操作？",
              ok:function () {
                  $.ajax({
                      type: 'POST',
                      url: '${ctx}/client/keyword/batchRemoveKeyword',
                      data: {ids:ids},
                      dataType:"json",
                      success: function (result) {
                          if(result.status=="0"){
                              $('#table').bootstrapTable('refresh', null);
                          }else{
                              alert({
                                  title:"操作提示",
                                  content:result.message
                              })
                          }
                      }
                  });
              },
          });
      }
   });
   //查看关键词
   function viewKeyword(content) {
       dialog({
           width:"606px",
           height:"200px",
           content:content
       });
   }
   //编辑关键词
   function editKeyword(id) {
       window.loadContent("${ctx}/keyword/updateKeyword.jsp?id="+id,null);
   }
   //删除关键词
   function removeKeyword(id) {
       confirm({
           title:"删除关键词提示",
           content:"请确认是否删除信息?",
           ok:function () {
               $.ajax({
                   type: 'POST',
                   url: '${ctx}/client/keyword/removeKeywordById',
                   data: {id:id},
                   dataType:"json",
                   success: function (result) {
                       if(result.status=="0"){
                           $('#table').bootstrapTable('refresh', null);
                       }else{
                           alert({"title":"删除提示","content":result.message});
                       }
                   }
               });
           }
       });
   }
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