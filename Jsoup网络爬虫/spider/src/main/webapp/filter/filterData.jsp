<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<div class="">
  <div class="x_panel">
    <div class="x_title">
      <h2>列表页配置</h2>
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
                  <label for="title">招聘标题</label>
                  <input type="text" class="form-control" id="title" name="title" placeholder="请输入招聘标题">
              </div>
              <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                  <label for="dataType">职位分类</label>
                  <select name="dataType" id="dataType" class="select2_single form-control">
                      <option value="">请选择</option>
                      <option value="pd">前端</option>
                      <option value="bd">大数据</option>
                      <option value="java">java</option>
                      <option value="NET">net</option>
                  </select>
              </div>
              <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                  <label for="status">状态</label>
                  <select name="status" id="status" class="select2_single form-control">
                      <option value="">请选择</option>
                      <option value="0">未删除</option>
                      <option value="1">已删除</option>
                  </select>
              </div>
          </div>
      </form>
      <div class="form-inline pull-left">
          <div class="form-group" style="margin-left: 5px;">
              <!-- 标准的按钮 -->
              <button type="button" class="btn btn-success">删除无效数据</button>
              <!-- 提供额外的视觉效果，标识一组按钮中的原始动作 -->
              <button type="button" class="btn btn-success">统计过滤词</button>
          </div>
      </div>
  </div>
  </div>
</div>