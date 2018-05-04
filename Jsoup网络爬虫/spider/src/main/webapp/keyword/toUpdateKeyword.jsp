<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="col-md-12 col-sm-12 col-xs-12">
  <div class="x_panel">
    <div class="x_title">
      <h2>编辑关键词</h2>
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
          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">名称
          </label>
          <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="hidden" value="${param.id}" id="id" name="id"/>
            <input type="text" id="name" name="name" required="required" class="form-control col-md-7 col-xs-12">
          </div>
        </div>
        <div class="form-group">
          <label for="website" class="control-label col-md-3 col-sm-3 col-xs-12">爬取网站</label>
          <div class="col-md-6 col-sm-6 col-xs-12">
            <select class="form-control" name="website" id="website">
              <option value="">请选择</option>
              <option value="lp">猎聘</option>
              <option value="zl">智联</option>
            </select>
          </div>
        </div>
        <div class="form-group">
          <label for="dataType" class="control-label col-md-3 col-sm-3 col-xs-12">数据类型</label>
          <div class="col-md-6 col-sm-6 col-xs-12">
            <select class="form-control" id="dataType" name="dataType">
              <option value="">请选择</option>
              <option value="qd">前端</option>
              <option value="java">JAVA</option>
              <option value="bd">大数据</option>
            </select>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 col-sm-3 col-xs-12 control-label">性别</label>
          <div class="col-md-9 col-sm-9 col-xs-12">
            <div class="radio">
              <label>
                <input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios">男&nbsp;
              </label>
              <label>
                <input type="radio" value="option2" id="optionsRadios2" name="optionsRadios">女&nbsp;
              </label>
            </div>
          </div>
        </div>
        <div class="ln_solid"></div>
        <div class="form-group">
          <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
            <button type="button" class="btn btn-primary" id="cancel">取消</button>
            <button type="button" class="btn btn-success" id="submit">添加</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<script>
  $(function () {
      getKeywordById();
      $("#submit").click(function () {
          var params=$("#updateForm").serialize();
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
                  $("#name").val(result.data.name);
                  $("#website").val(result.data.website);
                  $("#dataType").val(result.data.dataType);
              }else{
                  alert("加载失败");
              }
          }
      });
  }
</script>