<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计</title>
<script src="${ctx}/vendors/select2/vendor/jquery-2.1.0.js"></script>
 <script type="text/javascript" src="${ctx}/js/common/echarts.common.min.js" ></script>
    </head>
    <body>
        <div id="chartmain" style="width:600px; height: 400px;"></div>
        <script type="text/javascript">
        $(function(){

            var namearr = [];
            var valuearr = [];
            
            $.ajax({
            	 type : "post",        //post请求方式
                 async : true,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                 url : "${ctx}/tongji",    //请求发送到ShowInfoIndexServlet处             
                 dataType : "json",        //返回数据形式为json
                 success : function(result) {
                	 for(var i =0;i<result.length;i++){
                    	 console.log(i,result[i].name);
                    	 namearr.push(result[i].name);
                    	 valuearr.push(result[i].count);
                
                    	 tongji(namearr,valuearr);
                	 }
                	 
                	 
                	 
                 }
            });
        });
        
        function tongji(namearr,valuearr){
        	//指定图标的配置和数据
            var option = {
                title:{
                    text:'ECharts 数据统计'
                },
                tooltip:{},
                legend:{
                    data:['用户来源']
                },
                xAxis:{
                    data:namearr//["伊芙丽","Only","乐町","秋水伊人","OECE"]
                },
                yAxis:{

                },
                series:[{
                    name:'访问量',
//                  type:图形形状----bar:柱状,line:折线
                    type:'bar',
                    data:valuearr//[500,200,360,200,280]
                }]
            };
            //初始化echarts实例
            var myChart = echarts.init(document.getElementById('chartmain'));

            //使用制定的配置项和数据显示图表
            myChart.setOption(option);
        }
      
         </script>
    </body>
</body>
</html>