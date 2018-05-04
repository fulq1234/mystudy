<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
  <div class="x_panel">
      <form id="updateForm" data-parsley-validate class="form-horizontal form-label-left">
          <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ruleName">规则名称</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <input type="text" id="ruleName" name="ruleName" required="required" class="form-control col-md-7 col-xs-12">
              </div>
          </div>
          <div class="form-group">
              <label class="col-md-3 col-sm-3 col-xs-12 control-label">是否需要代理</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <div class="radio">
                          <label>
                              <input type="radio"  value="1" id="isNeedProxy0" name="isNeedProxy">代理
                          </label>
                          <label>
                              <input type="radio"  value="0" id="isNeedProxy1" name="isNeedProxy">默认访问
                          </label>
                  </div>
              </div>
          </div>
          <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="description">规则描述</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <textarea class="form-control" rows="5" id="description" name="description"></textarea>
              </div>
          </div>
          <div class="form-group">
              <label class="col-md-3 col-sm-3 col-xs-12 control-label">状态</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <div class="radio">
                          <label>
                              <input type="radio"  value="0" id="status0" name="status">启用
                          </label>
                          <label>
                              <input type="radio"  value="1" id="status1" name="status">禁用
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
<script>
  $(function () {
      getCrawlerRulesById();
      $("#submit").click(function () {
          var id=$("#id").val();
          var ruleName=$("#ruleName").val();
          var isNeedProxy=$("input[name='isNeedProxy']:checked").val();
          var description=$("#description").val();
          var status=$("input[name='status']:checked").val();
          var params={};
          params['id']=id;
          params['ruleName']=ruleName;
          params['isNeedProxy']=isNeedProxy;
          params['description']=description;
          params['status']=status;
          $.ajax({
              type: 'POST',
              url: '${ctx}/client/crawlerRules/saveOrUpdateCrawlerRules',
              data: params,
              dataType:"json",
              success: function (result) {
                  if(result.status==0){
                      alert({"title":"操作提示","content":"操作成功",ok:function(){
                          $('#rule_table').bootstrapTable('refresh', null);
                          layer.closeAll();
                      }});
                  }else{
                      alert({"title":"操作提示","content":result.message});
                  }
              }
          });
      });
      $("#cancel").click(function () {
          layer.closeAll();
      });
  });
  function getCrawlerRulesById() {
      var id=$("#id").val();
      $.ajax({
          type: 'POST',
          url: '${ctx}/client/crawlerRules/getCrawlerRulesById',
          data: {"id":id},
          dataType:"json",
          success: function (result) {
              if(result.status=="0"){
                  //普通输入框
                  $("#id").val(result.data.id);
                  //普通输入框
                  $("#ruleName").val(result.data.ruleName);
                  //单选框
                  $("input[name='isNeedProxy'][value="+result.data.isNeedProxy+"]").attr("checked",true);
                  $("input[name='status'][value="+result.data.status+"]").attr("checked",true);
                  //普通输入框
                  $("#description").val(result.data.description);
                  //普通输入框
              }else{
                  alert("加载失败");
              }
          }
      });
  }
</script>