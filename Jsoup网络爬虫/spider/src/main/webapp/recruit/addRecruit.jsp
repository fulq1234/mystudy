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
        <h2>添加</h2>
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
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">招聘标题</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="title" name="title" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="city">来源城市</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="city" name="city" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="companyName">公司名称</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="companyName" name="companyName" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="companyType">公司类型</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="companyType" name="companyType" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="companyUrl">公司网址</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="companyUrl" name="companyUrl" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="welfare">福利</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="welfare" name="welfare" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="companyAddress">公司地址</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="companyAddress" name="companyAddress" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="monthlySalary">职位月薪</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="monthlySalary" name="monthlySalary" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="minSalary">最低月薪</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="minSalary" name="minSalary" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="maxSalary">最高月薪</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="maxSalary" name="maxSalary" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="releaseDate">发布日期</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="releaseDate" name="releaseDate" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="workNature">工作性质</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="workNature" name="workNature" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="experience">工作经验</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="experience" name="experience" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="eucation">学历</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="eucation" name="eucation" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="rNum">招聘人数</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="rNum" name="rNum" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="positionCategory">职位分类</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="positionCategory" name="positionCategory" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="jobDescription">工作描述</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="jobDescription" name="jobDescription" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="sourceWebsite">来源网站</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="sourceWebsite" name="sourceWebsite" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="sourceUrl">来源URL</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="sourceUrl" name="sourceUrl" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="uuid">随机字符串</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="uuid" name="uuid" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dataType" class="col-md-3 col-sm-3 col-xs-12 control-label">职位分类</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="dataType" id="dataType" class="form-control col-md-7 col-xs-12">
                                        <option value="pd">前端</option>
                                        <option value="bd">大数据</option>
                                        <option value="java">java</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">公司网址</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="website" name="website" required="required" class="form-control col-md-7 col-xs-12">
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

    });
    $("#submit").click(function () {
            var id=$("#id").val();
            var title=$("#title").val();
            var city=$("#city").val();
            var companyName=$("#companyName").val();
            var companyType=$("#companyType").val();
            var companyUrl=$("#companyUrl").val();
            var welfare=$("#welfare").val();
            var companyAddress=$("#companyAddress").val();
            var monthlySalary=$("#monthlySalary").val();
            var minSalary=$("#minSalary").val();
            var maxSalary=$("#maxSalary").val();
            var releaseDate=$("#releaseDate").val();
            var workNature=$("#workNature").val();
            var experience=$("#experience").val();
            var eucation=$("#eucation").val();
            var rNum=$("#rNum").val();
            var positionCategory=$("#positionCategory").val();
            var jobDescription=$("#jobDescription").val();
            var sourceWebsite=$("#sourceWebsite").val();
            var sourceUrl=$("#sourceUrl").val();
            var uuid=$("#uuid").val();
            var dataType=$("#dataType").val();
            var website=$("#website").val();
            var status=$("input[name='status']:checked").val();
            var createdTime=$("#createdTime").val();
            var updatedTime=$("#updatedTime").val();
        var params={};
        params['id']=id;
        params['title']=title;
        params['city']=city;
        params['companyName']=companyName;
        params['companyType']=companyType;
        params['companyUrl']=companyUrl;
        params['welfare']=welfare;
        params['companyAddress']=companyAddress;
        params['monthlySalary']=monthlySalary;
        params['minSalary']=minSalary;
        params['maxSalary']=maxSalary;
        params['releaseDate']=releaseDate;
        params['workNature']=workNature;
        params['experience']=experience;
        params['eucation']=eucation;
        params['rNum']=rNum;
        params['positionCategory']=positionCategory;
        params['jobDescription']=jobDescription;
        params['sourceWebsite']=sourceWebsite;
        params['sourceUrl']=sourceUrl;
        params['uuid']=uuid;
        params['dataType']=dataType;
        params['website']=website;
        params['status']=status;
        params['createdTime']=createdTime;
        params['updatedTime']=updatedTime;
        $.ajax({
          type: 'POST',
          url: '${ctx}/client/recruit/saveOrUpdateRecruit',
          data: params,
          dataType:"json",
          success: function (result) {
              if(result.status==0){
                  alert({"title":"操作提示","content":"操作成功",ok:function(){
                      window.loadContent("${ctx}/recruit/recruitList.jsp",null);
                  }});
              }else{
                  alert({"title":"操作提示","content":result.message});
              }
          }
      });
    });
  $("#cancel").click(function () {
      window.loadContent("${ctx}/recruit/recruitList.jsp",null);
  });
</script>