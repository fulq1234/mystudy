<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<div class="row">
  <div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>添加代理IP</h2>
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
        <form id="addForm" data-parsley-validate class="form-horizontal form-label-left">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ip">ip</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="ip" name="ip" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="port">端口</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="port" name="port" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">网站</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="website" name="website" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="creatdTime">创建时间</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="creatdTime" name="creatdTime" required="required" class="form-control has-feedback-left">
                                <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="creatdTime">更新时间</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="updatedTime" name="updatedTime" required="required" class="form-control has-feedback-left">
                                <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 col-sm-3 col-xs-12 control-label">状态</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <div class="checkbox">
                                        <label>
                                            <input type="checkbox"  value="0" id="testCheck0" name="testCheck">启用
                                        </label>
                                        <label>
                                            <input type="checkbox"  value="1" id="testCheck1" name="testCheck">禁用
                                        </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="testSelect" class="col-md-3 col-sm-3 col-xs-12 control-label">状态</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="testSelect" id="testSelect" class="form-control col-md-7 col-xs-12">
                                        <option value="0">选择0</option>
                                        <option value="1">选择1</option>
                                        <option value="2">选择2</option>
                                        <option value="3">选择3</option>
                                </select>
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
              <button type="button" class="btn btn-primary" id="cancel">取消</button>
              <button type="button" class="btn btn-success" id="submit">添加</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script>
    $(function () {
        $('#creatdTime').daterangepicker({
            singleDatePicker: true,
            calender_style: "picker_2",
            format: 'YYYY-MM-DD'
        });
        $('#updatedTime').daterangepicker({
            singleDatePicker: true,
            calender_style: "picker_2",
            format: 'YYYY-MM-DD'
        });
    });
    $("#submit").click(function () {
            var id=$("#id").val();
            var ip=$("#ip").val();
            var port=$("#port").val();
            var website=$("#website").val();
            var creatdTime=$("#creatdTime").val();
            var updatedTime=$("#updatedTime").val();
            var testCheck=null;
            $('input[name="testCheck"]:checked').each(function(){
                testCheck=testCheck+","+$(this).val();
            });
            testCheck=testCheck+",";
            var testSelect=$("#testSelect").val();
            var status=$("input[name='status']:checked").val();
        var params={};
        params['id']=id;
        params['ip']=ip;
        params['port']=port;
        params['website']=website;
        params['creatdTime']=creatdTime;
        params['updatedTime']=updatedTime;
        params['testCheck']=testCheck;
        params['testSelect']=testSelect;
        params['status']=status;
        $.ajax({
          type: 'POST',
          url: '${ctx}/client/proxyIp/saveOrUpdateProxyIp',
          data: params,
          dataType:"json",
          success: function (result) {
              if(result.status==0){
                  alert({"title":"操作提示","content":"操作成功",ok:function(){
                      window.loadContent("${ctx}/proxyIp/proxyIpList.jsp",null);
                  }});
              }else{
                  alert({"title":"操作提示","content":result.message});
              }
          }
      });
    });
  $("#cancel").click(function () {
      window.loadContent("${ctx}/proxyIp/proxyIpList.jsp",null);
  });
</script>