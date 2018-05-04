<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<div class="col-md-12 col-sm-12 col-xs-12">
  <div class="x_panel">
    <div class="x_title">
      <h2>编辑模块</h2>
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
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">名称</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="name" name="name" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="url">访问地址</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="url" name="url" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="parent">父模块</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <select name="parent" id="parent" class="form-control col-md-7 col-xs-12">

                          </select>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-md-3 col-sm-3 col-xs-12 control-label">状态</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <div class="radio">
                                  <label>
                                      <input type="radio"  value="0" id="status0" name="status">未删除
                                  </label>
                                  <label>
                                      <input type="radio"  value="1" id="status1" name="status">已删除
                                  </label>
                          </div>
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
      queryModuleByParent();
      getModuleById();
      $("#submit").click(function () {
          var id=$("#id").val();
          var name=$("#name").val();
          var url=$("#url").val();
          var parent=$("#parent").val();
          var status=$("input[name='status']:checked").val();
          var params={};
          params['id']=id;
          params['name']=name;
          params['url']=url;
          params['parent']=parent;
          params['status']=status;
          $.ajax({
              type: 'POST',
              url: '${ctx}/client/module/saveOrUpdateModule',
              data: params,

              dataType:"json",
              success: function (result) {
                  if(result.status==0){
                      alert({"title":"操作提示","content":"操作成功",ok:function(){
                          window.loadContent("${ctx}/module/moduleList.jsp",null);
                      }});
                  }else{
                      alert({"title":"操作提示","content":result.message});
                  }
              }
          });
      });
      $("#cancel").click(function () {
          window.loadContent("${ctx}/module/moduleList.jsp",null);
      });
  });
  function getModuleById() {
      var id=$("#id").val();
      $.ajax({
          type: 'POST',
          url: '${ctx}/client/module/getModuleById',
          data: {"id":id},
          dataType:"json",
          success: function (result) {
              if(result.status=="0"){
                  //普通输入框
                  $("#id").val(result.data.id);
                  //普通输入框
                  $("#name").val(result.data.name);
                  //普通输入框
                  $("#url").val(result.data.url);
                  $("#parent").val(result.data.parent);
                  //单选框
                  $("input[name='status'][value="+result.data.status+"]").attr("checked",true);
              }else{
                  alert("加载失败");
              }
          }
      });
  }
  function queryModuleByParent() {
      $.ajax({
          type: 'POST',
          url: '${ctx}/client/module/queryModuleByParent',
          data: {"parent":0},
          dataType:"json",
          success: function (result) {
             var options="<option value='0'>无</option>";
             for(var i=0;i<result.data.length;i++){
                 options=options+"<option value="+result.data[i].id+">"+result.data[i].name+"</option>";
                 $("#parent").html(options);
             }
          }
      });
  }
</script>

