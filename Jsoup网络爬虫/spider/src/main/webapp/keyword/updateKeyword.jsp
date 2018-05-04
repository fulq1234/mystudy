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
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="rid"></label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="rid" name="rid" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name"></label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <textarea class="form-control" rows="5" id="name" name="name"></textarea>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dataType"></label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="dataType" name="dataType" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="uuid"></label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="uuid" name="uuid" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="taskId"></label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="taskId" name="taskId" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website"></label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="website" name="website" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="status"></label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="status" name="status" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="jobDescription"></label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="jobDescription" name="jobDescription" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="createdTime">创建时间</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="createdTime" name="createdTime" required="required" class="form-control has-feedback-left">
                          <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="updatedTime">更新时间</label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="updatedTime" name="updatedTime" required="required" class="form-control has-feedback-left">
                          <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
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
    $('#createdTime').daterangepicker({
        singleDatePicker: true,
        calender_style: "picker_2",
        format: 'YYYY-MM-DD'
    });
    $('#updatedTime').daterangepicker({
        singleDatePicker: true,
        calender_style: "picker_2",
        format: 'YYYY-MM-DD'
    });
      getKeywordById();
      $("#submit").click(function () {
              var id=$("#id").val();
              var rid=$("#rid").val();
              var name=$("#name").val();
              var dataType=$("#dataType").val();
              var uuid=$("#uuid").val();
              var taskId=$("#taskId").val();
              var website=$("#website").val();
              var status=$("#status").val();
              var jobDescription=$("#jobDescription").val();
              var createdTime=$("#createdTime").val();
              var updatedTime=$("#updatedTime").val();
          var params={};
          params['id']=id;
          params['rid']=rid;
          params['name']=name;
          params['dataType']=dataType;
          params['uuid']=uuid;
          params['taskId']=taskId;
          params['website']=website;
          params['status']=status;
          params['jobDescription']=jobDescription;
          params['createdTime']=createdTime;
          params['updatedTime']=updatedTime;
          $.ajax({
              type: 'POST',
              url: '${ctx}/client/keyword/saveOrUpdateKeyword',
              data: params,
              dataType:"json",
              success: function (result) {
                  if(result.status==0){
                      alert({"title":"操作提示","content":"操作成功",ok:function(){
                          window.loadContent("${ctx}/keyword/keywordList.jsp",null);
                      }});
                  }else{
                      alert({"title":"操作提示","content":result.message});
                  }
              }
          });
      });
      $("#cancel").click(function () {
          window.loadContent("${ctx}/keyword/keywordList.jsp",null);
      });
  });
  function getKeywordById() {
      var id=$("#id").val();
      $.ajax({
          type: 'POST',
          url: '${ctx}/client/keyword/getKeywordById',
          data: {"id":id},
          dataType:"json",
          success: function (result) {
              if(result.status=="0"){
                          //普通输入框
                          $("#id").val(result.data.id);
                          //普通输入框
                          $("#rid").val(result.data.rid);
                          //普通输入框
                          $("#name").val(result.data.name);
                          //普通输入框
                          $("#dataType").val(result.data.dataType);
                          //普通输入框
                          $("#uuid").val(result.data.uuid);
                          //普通输入框
                          $("#taskId").val(result.data.taskId);
                          //普通输入框
                          $("#website").val(result.data.website);
                          //普通输入框
                          $("#status").val(result.data.status);
                          //普通输入框
                          $("#jobDescription").val(result.data.jobDescription);
                          //普通输入框
                          $("#createdTime").val(result.data.createdTime);
                          //普通输入框
                          $("#updatedTime").val(result.data.updatedTime);
              }else{
                  alert("加载失败");
              }
          }
      });
  }
</script>