<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<div class="col-md-12 col-sm-12 col-xs-12">
  <div class="x_panel">
    <div class="x_title">
      <h2>编辑</h2>
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
      <br />
      <form id="updateForm" data-parsley-validate class="form-horizontal form-label-left">
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="taskName">任务名称</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="taskName" name="taskName" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ruleId">规则</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <select name="ruleId" id="ruleId" class="form-control col-md-7 col-xs-12"></select>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ruleId">数据类型</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <select name="dataType" id="dataType" class="form-control col-md-7 col-xs-12">
                          </select>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="url">采集URL</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="url" name="url" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="startPage">开始页数</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="startPage" name="startPage" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="endPage">结束页数</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="endPage" name="endPage" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="threadCount">线程数目</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="threadCount" name="threadCount" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="progress">进度</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="progress" name="progress" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="description">任务描述</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="description" name="description" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="threadCount">关键词</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="keyword" name="keyword" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="threadCount">是否分页</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <select name="isNeedPage" id="isNeedPage" class="form-control col-md-7 col-xs-12">
                              <option value="1">分页</option>
                              <option value="0">不分页</option>
                          </select>
                      </div>
                  </div>
        <div class="ln_solid"></div>
        <div class="form-group">
          <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
             <input type="hidden" id="id" name="id" value="${param.id}">
             <button type="button" class="btn btn-primary" id="cancel">取消</button>
            <button type="button" class="btn btn-success" id="submit">修改</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<script>
  $(function () {
      loadRuleList();
      getCrawlerTaskById();
      queryDataType();
      $("#submit").click(function () {
          var id=$("#id").val();
          var taskName=$("#taskName").val();
          var ruleId=$("#ruleId").val();
          var url=$("#url").val();
          var startPage=$("#startPage").val();
          var endPage=$("#endPage").val();
          var threadCount=$("#threadCount").val();
          var description=$("#description").val();
          var keyword=$("#keyword").val();
          var isNeedPage=$("#isNeedPage").val();
          var dataType=$("#dataType").val();
          var params={};
          params['id']=id;
          params['taskName']=taskName;
          params['ruleId']=ruleId;
          params['url']=url;
          params['startPage']=startPage;
          params['endPage']=endPage;
          params['threadCount']=threadCount;
          params['description']=description;
          params['keyword']=keyword;
          params['isNeedPage']=isNeedPage;
          params['dataType']=dataType;
          $.ajax({
              type: 'POST',
              url: '${ctx}/client/crawlerTask/saveOrUpdateCrawlerTask',
              data: params,
              dataType:"json",
              success: function (result) {
                  if(result.status==0){
                      alert({"title":"操作提示","content":"操作成功",ok:function(){
                          window.loadContent("${ctx}/crawlerTask/crawlerTaskList.jsp",null);
                      }});
                  }else{
                      alert({"title":"操作提示","content":result.message});
                  }
              }
          });
      });
      $("#cancel").click(function () {
          window.loadContent("${ctx}/crawlerTask/crawlerTaskList.jsp",null);
      });
  });
  function getCrawlerTaskById() {
      var id=$("#id").val();
      $.ajax({
          type: 'POST',
          url: '${ctx}/client/crawlerTask/getCrawlerTaskById',
          data: {"id":id},
          dataType:"json",
          success: function (result) {
              if(result.status=="0"){
                  //普通输入框
                  $("#id").val(result.data.id);
                  //普通输入框
                  $("#taskName").val(result.data.taskName);
                  //普通输入框
                  $("#ruleId").val(result.data.ruleId);
                  //普通输入框
                  $("#url").val(result.data.url);
                  //普通输入框
                  $("#startPage").val(result.data.startPage);
                  //普通输入框
                  $("#endPage").val(result.data.endPage);
                  //普通输入框
                  $("#threadCount").val(result.data.threadCount);
                  //普通输入框
                  $("#description").val(result.data.description);
                  //关键词
                  $("#keyword").val(result.data.keyword);
                  //是否分页
                  $("#isNeedPage").val(result.data.isNeedPage);
                  $("#dataType").val(result.data.dataType);
              }else{
                  alert("加载失败");
              }
          }
      });
  }
  function loadRuleList() {
      $.ajax({
          type: 'POST',
          url: '${ctx}/client/crawlerRules/list',
          dataType:"json",
          success: function (result) {
              var options="<option value='-1'>全部</option>";
              for(var i=0;i<result.length;i++){
                  options=options+"<option value='"+result[i].id+"'>"+result[i].ruleName+"</option>";
              }
              $("#ruleId").html(options);
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
</script>