<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--JS-->
<script src="${ctx}/vendors/select2/vendor/jquery-2.1.0.js"></script>
<!-- Bootstrap -->
<script src="${ctx}/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="${ctx}/vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="${ctx}/vendors/nprogress/nprogress.js"></script>
<!-- iCheck -->
<script src="${ctx}/vendors/iCheck/icheck.min.js"></script>
<!-- iCheck -->
<!-- Custom Theme Scripts -->
<script src="${ctx}/js/common/bootstrap-table.min.js"></script>
<script src="${ctx}/js/common/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx}/layer/layer.js"></script>
<script src="${ctx}/js/moment/moment.min.js"></script>
<script src="${ctx}/js/datepicker/daterangepicker.js"></script>
<script src="${ctx}/vendors/select2/dist/js/select2.full.min.js"></script>
<script src="${ctx}/js/common/echarts.common.min.js"></script>
<script>
    window.alert=function (json) {
        var title=(json.title==null)?"温馨提示":json.title;
        layer.alert(json.content,{icon:1,title:title},function (index) {
            layer.close(index);
            if(json.ok!=null){
                json.ok();
            }
        });
    };
    window.confirm=function (json) {
        layer.confirm(json.content, {
            btn: ['确认','取消'] //按钮
        }, function(index){
            layer.close(index);
            if(json.ok!=null){
                json.ok();
            }
        }, function(index){
            layer.close(index);
            if(json.cancel!=null){
                json.cancel();
            }
        });
    };
    window.dialog=function(json){
        layer.open({
            type: 1,
            area: [json.width, json.height],
            shadeClose: true,
            content: '<div style="padding:20px;">'+json.content+'</div>'
        });
    };
    window.ajaxDialog=function (json) {
        $.ajax({
            type: 'POST',
            url: json.url,
            async:true,
            success: function (result) {
                layer.open({
                    title:json.title,
                    type: 1,
                    area: [json.width, json.height],
                    shadeClose: true,
                    content: result
                });
            }
        });
    };
    window.sidebar=function (json) {
        var $div=$("<div class=\"menu_section\"></div>");
        $div.append("<h3>General</h3>");
        var $ul=$("<ul class=\"nav side-menu\" id='menu'></ul>");
        $.ajax({
            type: 'POST',
            url: json.url,
            dataType:"json",
            async:false,
            success: function (result) {
                for (var i=0;i<result.data.length;i++){
                    var module=result.data[i];
                    var $li=$("<li></li>");
                    var $a=$("<a><i class=\"fa fa-home\"></i>"+module.name+"<span class=\"fa fa-chevron-down\"></span></a>");
                    $li.append($a);
                    var $$ul=$("<ul class=\"nav child_menu\" ></ul>");
                    for(var j=0;j<module.children.length;j++){
                        var $$li=$("<li><a href=\"javascript:void(0);\" onclick=\"loadContent('${ctx}"+module.children[j].url+"');\">"+module.children[j].name+"</a></li>");
                        $$ul.append($$li);
                    }
                    $li.append($$ul);
                    $ul.append($li);
                }
                $div.append($ul);
            }
        });
        $("#"+json.id).html($div);
    };
    window.sidebar({
        id:"sidebar-menu",
        url:"${ctx}/client/module/queryModuleList"
    });
    window.loadContent=function(url,data) {
        $("#menu").children("li").children("ul").children(".active").removeClass("active");
        $("#rowContent").load(url,data);
    };
    function initOptions(title,keyArray_,valueArray_) {
        return {
            title: {
                text: '',
                subtext:"注:统计图最大值均为100"
            },
            color: ['#FFA07A'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            toolbox: {
                // y: 'bottom',
                feature: {
                    saveAsImage: {
                        pixelRatio: 1
                    }
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                data: keyArray_,
                axisLabel:{
                    interval: 0,
                    rotate:40
                }
            },
            yAxis: {
                min:function () {
                    return 0;
                },
                axisLabel:{
                    formatter: '{value} %'
                }
            },
            series: [{
                type: 'bar',
                data: valueArray_,
                barWidth: '20%',
                radius : '10%',
                label: {
                    normal: {
                        show: true,
                        position: 'outside',
                        color:'black',
                        formatter: '{c} %'
                    }
                }
            }],
        };
    };
    //初始化表格
    function initChart(id,title,keyArray_,valueArray_){
        var myChart = echarts.init(document.getElementById(id));
        // 指定图表的配置项和数据
        var option =initOptions(title,keyArray_,valueArray_);
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.onresize = myChart.resize;
    }
    //判断中文
    function checkChinese(val){
        var reg = new RegExp("^[a-zA-Z]+$","g");
        return reg.test(val);
    }
</script>
<script src="${ctx}/build/js/custom.min.js"></script>

