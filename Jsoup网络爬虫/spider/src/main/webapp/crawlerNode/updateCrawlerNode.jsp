<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
  <div class="x_panel">
      <form id="updateForm" data-parsley-validate class="form-horizontal form-label-left">
          <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">节点名称</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <input type="text" id="name" name="name" required="required" class="form-control col-md-7 col-xs-12">
                  <input type="hidden" id="ruleId" name="ruleId" required="required" class="form-control col-md-7 col-xs-12">
                  <input type="hidden" id="parent" name="parent" required="required" class="form-control col-md-7 col-xs-12">
                  <input type="hidden" id="level" name="level" required="required" class="form-control col-md-7 col-xs-12">
              </div>
          </div>
          <div class="form-group">
              <label class="col-md-3 col-sm-3 col-xs-12 control-label">是否有子节点</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <div class="radio">
                          <label>
                              <input type="radio"  value="1" id="hasNext0" name="hasNext">有
                          </label>
                          <label>
                              <input type="radio"  value="0" id="hasNext1" name="hasNext">无
                          </label>
                  </div>
              </div>
          </div>
          <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="saveCloumn">保存对应字段</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <select name="saveCloumn" id="saveCloumn" required="required" class="form-control col-md-7 col-xs-12">
                  </select>
              </div>
          </div>
          <div class="form-group">
              <label class="col-md-3 col-sm-3 col-xs-12 control-label">是否是超链接</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <div class="radio">
                      <label>
                          <input type="radio"  value="0" id="isHref0" name="isHref">否
                      </label>
                      <label>
                          <input type="radio"  value="1" id="isHref1" name="isHref">是
                      </label>
                  </div>
              </div>
          </div>
          <div class="form-group">
              <label for="type" class="col-md-3 col-sm-3 col-xs-12 control-label">节点类型</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <select name="type" id="type" class="form-control col-md-7 col-xs-12" onchange="showSelector();">
                      <option value="1">jsoup选择</option>
                      <option value="2">字符串截取</option>
                      <option value="3">正则表达式</option>
                  </select>
              </div>
          </div>
          <div class="form-group" id="classReg-form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="classReg">jsuop选择表达式</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <input type="text" id="classReg" name="classReg" required="required" class="form-control col-md-7 col-xs-12">
              </div>
          </div>
          <div class="form-group" id="startStr-form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="startStr">开始字符串</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <input type="text" id="startStr" name="startStr" required="required" class="form-control col-md-7 col-xs-12">
              </div>
          </div>
          <div class="form-group" id="endStr-form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="endStr">结束字符串</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <input type="text" id="endStr" name="endStr" required="required" class="form-control col-md-7 col-xs-12">
              </div>
          </div>
          <div class="form-group">
              <label for="position" class="col-md-3 col-sm-3 col-xs-12 control-label">元素位置</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <select name="position" id="position" class="form-control col-md-7 col-xs-12">
                      <option value="0">全部元素</option>
                      <option value="1">首元素</option>
                      <option value="2">末元素</option>
                      <option value="3">指定位置元素</option>
              </select>
              </div>
          </div>

          <div class="form-group">
              <label for="position" class="col-md-3 col-sm-3 col-xs-12 control-label">指定元素位置</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <input type="text" id="selfPosition" name="selfPosition" required="required" class="form-control col-md-7 col-xs-12">
              </div>
          </div>

          <div class="form-group" id="reg-form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="reg">正则表达式</label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                  <input type="text" id="reg" name="reg" required="required" class="form-control col-md-7 col-xs-12">
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
      getCrawlerNodeById();
      queryFieldType();
      $("#submit").click(function () {
          var id=$("#id").val();
          var name=$("#name").val();
          var type=$("#type").val();
          var parent=$("#parent").val();
          var hasNext=$("input[name='hasNext']:checked").val();
          var saveCloumn=$("#saveCloumn").val();
          var classReg=$("#classReg").val();
          var startStr=$("#startStr").val();
          var endStr=$("#endStr").val();
          var position=$("#position").val();
          var isHref=$("input[name='isHref']:checked").val();
          var reg=$("#reg").val();
          var ruleId=$("#ruleId").val();
          var selfPosition=$("#selfPosition").val();
          var params={};
          params['id']=id;
          params['name']=name;
          params['type']=type;
          params['parent']=parent;
          params['hasNext']=hasNext;
          params['saveCloumn']=saveCloumn;
          params['classReg']=classReg;
          params['startStr']=startStr;
          params['endStr']=endStr;
          params['position']=position;
          params['isHref']=isHref;
          params['reg']=reg;
          params['ruleId']=ruleId;
          params['selfPosition']=selfPosition;
          $.ajax({
              type: 'POST',
              url: '${ctx}/client/crawlerNode/saveOrUpdateCrawlerNode',
              data: params,
              dataType:"json",
              success: function (result) {
                  if(result.status==0){
                      alert({"title":"操作提示","content":"操作成功",ok:function(){
                          $('#parent_table').bootstrapTable('refresh', null);
                          $('#child_table').bootstrapTable('refresh', null);
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

  function showSelector() {
      var type=$("#type").val();
      if(type==1){
          $("#reg-form-group").hide();
          $("#startStr-form-group").hide();
          $("#endStr-form-group").hide();
          $("#classReg-form-group").show();
      }else if(type==2){
          $("#reg-form-group").hide();
          $("#startStr-form-group").show();
          $("#endStr-form-group").show();
          $("#classReg-form-group").hide();
      }else if(type==3){
          $("#reg-form-group").show();
          $("#startStr-form-group").hide();
          $("#endStr-form-group").hide();
          $("#classReg-form-group").hide();
      }
  }

  function getCrawlerNodeById() {
      var id=$("#id").val();
      $.ajax({
          type: 'POST',
          url: '${ctx}/client/crawlerNode/getCrawlerNodeById',
          data: {"id":id},
          dataType:"json",
          success: function (result) {
              if(result.status=="0"){
                  //普通输入框
                  $("#id").val(result.data.id);
                  //普通输入框
                  $("#name").val(result.data.name);
                  //普通输入框
                  $("#type").val(result.data.type);
                  //普通输入框
                  $("#parent").val(result.data.parent);
                  //单选框
                  $("input[name='hasNext'][value="+result.data.hasNext+"]").attr("checked",true);
                  //普通输入框
                  $("#saveCloumn").val(result.data.saveCloumn);
                  //普通输入框
                  $("#classReg").val(result.data.classReg);
                  //普通输入框
                  $("#startStr").val(result.data.startStr);
                  //普通输入框
                  $("#endStr").val(result.data.endStr);
                  //普通输入框
                  $("#position").val(result.data.position);
                  //单选框
                  $("input[name='isHref'][value="+result.data.isHref+"]").attr("checked",true);
                  //普通输入框
                  $("#reg").val(result.data.reg);
                  //普通输入框
                  $("#ruleId").val(result.data.ruleId);
                  $("#selfPosition").val(result.data.selfPosition);
                  showSelector();
              }else{
                  alert("加载失败");
              }
          }
      });
  }
  //加载数据类型
  function queryFieldType() {
      $.ajax({
          type: 'POST',
          url: '${ctx}/queryFieldType',
          dataType:"json",
          success: function (result) {
              var options="<option value=''>请选择</option>";
              for(var i=0;i<result.data.length;i++){
                  options=options+"<option value="+result.data[i].value+">"+result.data[i].name+"</option>";
              }
              $("#saveCloumn").html(options);
          }
      });
  }
</script>