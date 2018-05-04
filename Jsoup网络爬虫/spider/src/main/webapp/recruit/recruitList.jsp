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
                    <label for="status">爬虫任务</label>
                    <select name="taskId" id="taskId" class="select2_single form-control"></select>
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                    <label for="title">招聘标题</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="请输入招聘标题">
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                    <label for="dataType">职位分类</label>
                        <select name="dataType" id="dataType" class="select2_single form-control">
                        </select>
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                    <label for="status">状态</label>
                        <select name="status" id="status" class="select2_single form-control">
                            <option value="">请选择</option>
                            <option value="0">未删除</option>
                            <option value="1">已删除</option>
                            <option value="2">已备份</option>
                        </select>
                </div>
            </div>
        </form>
      <div class="form-inline pull-left">
        <div class="form-group" style="margin-left: 5px;">
          <button type="button" class="btn btn-primary" id="search">搜索</button>
          <button type="button" class="btn btn-danger" id="remove">批量删除</button>
          <button type="button" class="btn btn-danger" id="removeByCondition">删除(按条件)</button>
            <button type="button" class="btn btn-danger" id="shieldByCondition">屏蔽无效数据</button>
            <button type="button" class="btn btn-danger" id="clearAll">全部清空</button>
            <button type="button" class="btn btn-info" id="backByCondition">备份(按条件)</button>
            <button type="button" class="btn btn-info" id="rollBackByCondition">还原(按条件)</button>
            <button type="button" class="btn btn-info" id="exportByCondition">按条件导出</button>
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
        url: '${ctx}/client/recruit/search',
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
            field: 'title',
            title: '招聘标题'
        },
        {
            field: 'city',
            title: '关键词'
        },
        {
            field: 'taskName',
            width:"100px",
            title: '任务名称'
        },
        {
            field: 'companyName',
            width:"150px",
            title: '公司名称'
        },
        {
            field: 'companyType',
            title: '公司类型'
        },
        {
            field: 'monthlySalary',
            width:"100px",
            title: '职位月薪'
        },
        {
            field: 'experience',
            title: '工作经验'
        },
        {
            field: 'eucation',
            title: '学历'
        },
        {
            field: 'typeName',
            title: '数据类型',
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
                }else if(value=='2'){
                    return '已备份';
                }
            }
        },
        {
            title: '操作',
            width:"130px",
            formatter:function (value,row,index) {
                var edit="<a href='#' class='btn btn-info btn-xs' onclick=\"editRecruit('"+row.id+"')\"><i class='fa fa-pencil'></i> 编辑 </a>";
                var del="<a href='#' class='btn btn-danger btn-xs' onclick=\"removeRecruit('"+row.id+"')\"><i class='fa fa-trash-o'></i> 删除 </a>";
                return edit+del;
            }
        }],
        queryParams: function queryParams(params) {  //设置查询参数
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                pageSize: params.limit,   //页面大小
                pageNo:(params.offset/params.limit)+1,
                sortOrder: params.order,
                title:$("#title").val(),
                companyName:$("#companyName").val(),
                companyType:$("#companyType").val(),
                companyAddress:$("#companyAddress").val(),
                monthlySalary:$("#monthlySalary").val(),
                minSalary:$("#minSalary").val(),
                maxSalary:$("#maxSalary").val(),
                releaseDate:$("#releaseDate").val(),
                experience:$("#experience").val(),
                eucation:$("#eucation").val(),
                uuid:$("#uuid").val(),
                dataType:$("#dataType").val(),
                status:$("#status").val(),
                taskId:$("#taskId").val()
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
       window.loadContent("${ctx}/recruit/addRecruit.jsp",null);
   });
   $("#exportByCondition").click(function () {
      var title=$("#title").val();
      var status=$("#status").val();
      var name=$("#name").val();
      var dataType=$("#dataType").val();
      var taskId=$("#taskId").val();
      window.open("${ctx}/client/recruit/exportRecuitDataExcel?name="+name+"&dataType="+dataType+"&taskId="+taskId+"&title="+title+"&status="+status);
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
                        url: '${ctx}/client/recruit/batchRemoveRecruit',
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
   //按照条件删除
   $("#removeByCondition").click(function () {
       var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
           dataType:$("#dataType").val(),
           status:$("#status").val(),
           taskId:$("#taskId").val()
       };
       confirm({
           title:"按条件删除数据提示",
           content:"请确认是否删除指定条件的数据?",
           ok:function () {
               $.ajax({
                   type: 'POST',
                   url: '${ctx}/client/recruit/removeRecruitByCondition',
                   data: temp,
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
           }
       });
   });
    //全部删除
    $("#clearAll").click(function () {
        confirm({
            title:"全部删除数据提示",
            content:"确认要删除全部数据吗?",
            ok:function () {
                $.ajax({
                    type: 'POST',
                    url: '${ctx}/client/recruit/removeRecruitByCondition',
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
            }
        });
    });
    //清空全部数据
    $("#clearAll").click(function () {
        confirm({
            title:"全部删除数据提示",
            content:"确认要删除全部数据吗?",
            ok:function () {
                $.ajax({
                    type: 'POST',
                    url: '${ctx}/client/recruit/removeRecruitByCondition',
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
            }
        });
    });
   //按条件备份
   $("#backByCondition").click(function () {
       var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
           dataType:$("#dataType").val(),
           status:$("#status").val(),
           taskId:$("#taskId").val()
       };
       confirm({
           title:"按条件备份提示",
           content:"确认要按所选条件备份数据吗?",
           ok:function () {
               $.ajax({
                   type: 'POST',
                   url: '${ctx}/client/recruit/backByCondition',
                   dataType:"json",
                   data:temp,
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
           }
       });
    });
    //按条件还原
    $("#rollBackByCondition").click(function () {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            dataType:$("#dataType").val(),
            status:$("#status").val(),
            taskId:$("#taskId").val()
        };
        confirm({
            title:"按条件还原提示",
            content:"确认要按所选条件还原数据吗?",
            ok:function () {
                $.ajax({
                    type: 'POST',
                    url: '${ctx}/client/recruit/rollBackByCondition',
                    dataType:"json",
                    data:temp,
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
            }
        });
    });
    $("#shieldByCondition").click(function () {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            dataType:$("#dataType").val(),
            status:$("#status").val(),
            taskId:$("#taskId").val()
        };
        confirm({
            title:"过滤无效数据",
            content:"确认要按所选条件过滤数据吗?",
            ok:function () {
                $.ajax({
                    type: 'POST',
                    url: '${ctx}/deleteInvalidData',
                    dataType:"json",
                    data:temp,
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
            }
        });
    });
   //查看关键词
   function viewRecruit(content) {
       dialog({
           width:"606px",
           height:"200px",
           content:content
       });
   }
   //编辑关键词
   function editRecruit(id) {
       window.loadContent("${ctx}/recruit/updateRecruit.jsp?id="+id,null);
   }
   //删除关键词
   function removeRecruit(id) {
       confirm({
           title:"删除关键词提示",
           content:"请确认是否删除信息?",
           ok:function () {
               $.ajax({
                   type: 'POST',
                   url: '${ctx}/client/recruit/removeRecruitById',
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