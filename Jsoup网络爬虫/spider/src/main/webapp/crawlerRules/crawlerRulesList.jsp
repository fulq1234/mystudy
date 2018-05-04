<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<div class="">
  <div class="x_panel">
      <div class="x_content">
          <form class="form-horizontal form-label-left input_mask" role="form">
              <div class="form-group">
                  <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                      <label for="search_ruleName">规则名称</label>
                      <input type="text" class="form-control" id="search_ruleName" name="search_ruleName" placeholder="请输入规则名称">
                  </div>
                  <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                      <label for="search_reqMethod">请求方式</label>
                      <select name="search_reqMethod" id="search_reqMethod" class="select2_single form-control">
                          <option value="-1">请选择</option>
                          <option value="1">GET</option>
                          <option value="2">POST</option>
                      </select>
                  </div>
                  <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                      <label for="search_isNeedProxy">是否需要代理</label>
                      <select name="search_isNeedProxy" id="search_isNeedProxy" class="select2_single form-control">
                          <option value="-1">请选择</option>
                          <option value="1">代理</option>
                          <option value="0">默认访问</option>
                      </select>
                  </div>
                  <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                      <label for="search_isNeedPage">是否需要分页</label>
                      <select name="search_isNeedPage" id="search_isNeedPage" class="select2_single form-control">
                          <option value="-1">请选择</option>
                          <option value="1">需要</option>
                          <option value="2">不需要</option>
                      </select>
                  </div>
                  <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                      <label for="search_status">状态</label>
                      <select name="search_status" id="search_status" class="select2_single form-control">
                          <option value="-1">请选择</option>
                          <option value="0">启用</option>
                          <option value="1">禁用</option>
                      </select>
                  </div>
              </div>
          </form>
          <div class="form-inline pull-left">
              <div class="form-group" style="margin-left: 5px;">
                  <button type="button" class="btn btn-primary" id="rule_search">搜索</button>
                  <button type="button" class="btn btn-info" id="rule_add">新增爬虫配置</button>
                  <button type="button" class="btn btn-danger" id="rule_remove">删除</button>
              </div>
          </div>
          <table id="rule_table"></table>
      </div>
  </div>
</div>
<script>
   var table=$('#rule_table').bootstrapTable({
        url: '${ctx}/client/crawlerRules/search',
        sidePagination:"server",
        pageList: [10, 25, 50, 100],
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination:true,//是否分页
        queryParamsType:'limit',//查询参数组织方式
        pageSize:10,//单页记录数
        pageList:[5,10,20,30],//分页步进值
        clickToSelect: false,//是否启用点击选中行
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
            field: 'ruleName',
            title: '规则名称'
        },
        {
            field: 'reqMethod',
            title: '请求方式',
            formatter:function (value,row,index) {
                    if(value=='1'){
                        return 'GET';
                    }
                    if(value=='2'){
                        return 'POST';
                    }
            }
        },
        {
            field: 'isNeedProxy',
            title: '是否需要代理',
            formatter:function (value,row,index) {
                    if(value=='1'){
                        return '代理';
                    }
                    if(value=='0'){
                        return '默认访问';
                    }
            }
        },
        {
            field: 'status',
            title: '状态',
            formatter:function (value,row,index) {
                    if(value=='0'){
                        return '启用';
                    }
                    if(value=='1'){
                        return '禁用';
                    }
            }
        },
        {
            title: '操作',
            width:"200px",
            formatter:function (value,row,index) {
                var edit="<a href='#' class='btn btn-info btn-xs' onclick=\"editCrawlerRules('"+row.id+"')\"><i class='fa fa-pencil'></i> 编辑 </a>";
                var del="<a href='#' class='btn btn-danger btn-xs' onclick=\"removeCrawlerRules('"+row.id+"')\"><i class='fa fa-trash-o'></i> 删除 </a>";
                var set="<a href='#' class='btn btn-danger btn-xs' onclick=\"setCrawlerNode('"+row.id+"')\"><i class='fa fa-trash-o'></i>配置</a>";
                return edit+del+set;
            }
        }],
        queryParams: function queryParams(params) {  //设置查询参数
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                pageSize: params.limit,   //页面大小
                pageNo:(params.offset/params.limit)+1,
                sortOrder: params.order,
                ruleName:$("#search_ruleName").val(),
                url:$("#search_url").val(),
                reqMethod:$("#search_reqMethod").val(),
                startPageNo:$("#search_startPageNo").val(),
                endPageNo:$("#search_endPageNo").val(),
                isNeedProxy:$("#search_isNeedProxy").val(),
                isNeedPage:$("#search_isNeedPage").val(),
                description:$("#search_description").val(),
                status:$("#search_status").val()
            };
            return temp;
        },
        responseHandler:function (res) {
            return {"rows":res.rows,"total":res.total};
        },
    });
   $("#rule_search").click(function () {
       $('#rule_table').bootstrapTable('refresh', null);
   });
   $("#rule_add").click(function () {
       window.ajaxDialog({
           title:"添加爬虫网站规则配置",
           width:"500px",
           height:"400px",
           url:"${ctx}/crawlerRules/addCrawlerRules.jsp",
       });
   });
   $("#rule_remove").click(function () {
      var ids="";
      var selectItems=$('#rule_table').bootstrapTable('getSelections', null);
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
                      url: '${ctx}/client/crawlerRules/batchRemoveCrawlerRules',
                      data: {ids:ids},
                      dataType:"json",
                      success: function (result) {
                          if(result.status=="0"){
                              $('#rule_table').bootstrapTable('refresh', null);
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
   function viewCrawlerRules(content) {
       dialog({
           width:"606px",
           height:"200px",
           content:content
       });
   }
   //编辑关键词
   function editCrawlerRules(id) {
       window.ajaxDialog({
           title:"添加爬虫网站",
           width:"500px",
           height:"400px",
           url:"${ctx}/crawlerRules/updateCrawlerRules.jsp?id="+id
       });
   }
   //删除关键词
   function removeCrawlerRules(id) {
       confirm({
           title:"删除关键词提示",
           content:"请确认是否删除信息?",
           ok:function () {
               $.ajax({
                   type: 'POST',
                   url: '${ctx}/client/crawlerRules/removeCrawlerRulesById',
                   data: {id:id},
                   dataType:"json",
                   success: function (result) {
                       if(result.status=="0"){
                           $('#rule_table').bootstrapTable('refresh', null);
                       }else{
                           alert({"title":"删除提示","content":result.message});
                       }
                   }
               });
           }
       });
   }
    //设置规则
    function setCrawlerNode(id) {
        tabLoadContent("node1","${ctx}/crawlerNode/parentList.jsp?ruleId="+id);
        $('#myTab li:eq(1) a').tab('show');
    }
</script>

