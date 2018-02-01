<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>排课表</title>
    <%@ include file="../../include/head.jsp" %>
    <style>
        .tpl-content-wrapper {
            margin-left: 0
        }
    </style>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">排课表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="course"
                                  action="${ctx}/mt/course/<c:choose><c:when test="${empty course.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>"
                                  method="post">
                                <input type="hidden" name="id" value="${course.id}"/>
                                <input type="hidden" id="bid" value="${course.battalionId}"/>
                                <input type="hidden" id="cid" value="${course.companyId}"/>
                                <input type="hidden" id="pid" value="${course.platoonId}"/>
                                <input type="hidden" id="pjid" value="${course.projectId}"/>
                                <div class="am-form-group" hidden>
                                    <label class="am-u-sm-3 am-form-label">营名称：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="battalionName" id="battalionName" placeholder="营名称"
                                               hidden="true"/>
                                    </div>
                                </div>
                                <div class="am-form-group" hidden>
                                    <label class="am-u-sm-3 am-form-label">连名称：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="companyName" id="companyName" placeholder="连名称"
                                               hidden="true"/>
                                    </div>
                                </div>
                                <div class="am-form-group" hidden>
                                    <label class="am-u-sm-3 am-form-label">排名称：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="platoonName" id="platoonName" placeholder="排名称"
                                               value="${course.platoonName}" hidden="true"/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">选择营连排：</label>
                                    <div class="am-u-sm-9">
                                        <select id="battalionId" name="battalionId">
                                        </select>
                                        <select id="companyId" name="companyId">
                                        </select>
                                        <select id="platoonId" name="platoonId">
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">选择周次：</label>
                                    <div class="am-u-sm-9">
                                        <select name="week" id="week">
                                            <option value="1"  <c:if test="${course.week == 1}"></c:if> >第1周</option>
                                            <option value="2"  <c:if test="${course.week == 2}"></c:if>>第2周</option>
                                            <option value="3"  <c:if test="${course.week == 3}"></c:if>>第3周</option>
                                            <option value="4"  <c:if test="${course.week == 4}"></c:if>>第4周</option>
                                            <option value="5"  <c:if test="${course.week == 5}"></c:if>>第5周</option>
                                            <option value="6"  <c:if test="${course.week == 6}"></c:if>>第6周</option>
                                            <option value="7"  <c:if test="${course.week == 7}"></c:if>>第7周</option>
                                            <option value="8"  <c:if test="${course.week == 8}"></c:if>>第8周</option>
                                            <option value="9"  <c:if test="${course.week == 9}"></c:if>>第9周</option>
                                            <option value="10" <c:if test="${course.week == 10}"></c:if>>第10周</option>
                                            <option value="11" <c:if test="${course.week == 11}"></c:if>>第11周</option>
                                            <option value="12" <c:if test="${course.week == 12}"></c:if>>第12周</option>
                                            <option value="13" <c:if test="${course.week == 13}"></c:if>>第13周</option>
                                            <option value="14" <c:if test="${course.week == 14}"></c:if>>第14周</option>
                                            <option value="15" <c:if test="${course.week == 15}"></c:if>>第15周</option>
                                            <option value="16" <c:if test="${course.week == 16}"></c:if>>第16周</option>
                                            <option value="17" <c:if test="${course.week == 17}"></c:if>>第17周</option>
                                            <option value="18" <c:if test="${course.week == 18}"></c:if>>第18周</option>
                                            <option value="19" <c:if test="${course.week == 19}"></c:if>>第19周</option>
                                            <option value="20" <c:if test="${course.week == 20}"></c:if>>第20周</option>
                                        </select>
                                    </div>
                                    <%--<div class="am-u-sm-4">--%>
                                        <%--<button type="button" onclick="getWeek()">获取周次</button>--%>
                                    <%--</div>--%>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">选择时间：</label>
                                    <div class="am-u-sm-9">
                                        <select name="days" id="days">
                                            <option value="1" <c:if test="${course.days == 1}">selected</c:if>>星期一</option>
                                            <option value="2" <c:if test="${course.days == 2}">selected</c:if>>星期二</option>
                                            <option value="3" <c:if test="${course.days == 3}">selected</c:if>>星期三</option>
                                            <option value="4" <c:if test="${course.days == 4}">selected</c:if>>星期四</option>
                                            <option value="5" <c:if test="${course.days == 5}">selected</c:if>>星期五</option>
                                            <option value="6" <c:if test="${course.days == 6}">selected</c:if>>星期六</option>
                                            <option value="7" <c:if test="${course.days == 7}">selected</c:if>>星期日</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">选择课程：</label>
                                    <div class="am-u-sm-9">
                                            <input type="hidden" name="projectName" id="projectName" value="${course.projectName}"/>
                                            <select name="projectId" id="projectId"></select>
                                    </div>
                                </div>


                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="submit" class="am-btn am-btn-primary">保存</button>
                                        <button type="button" class="am-btn am-btn-danger" onclick="closeModel(false)">
                                            关闭
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../include/bottom.jsp" %>
<script type="text/javascript" src="/static/assets/js/rea.js"></script>
<script src="/static/3rd-lib/jquery/2.2.3/jquery.min.js"></script>
<script>
//    //选择周次
//    function getWeek() {
//        var week = $("#week");
//        var platoonId = $("#platoonId").find("option:selected").val();
//        if (platoonId == null || platoonId == '') {
//            platoonId = $("#platoonId option:first").val();
//        }
////        alert(platoonId)
//        $.ajax({
//            type: "POST",
//            url: "/admin/mt/course/getWeek",
//            data: {
//                platoonId: platoonId
//            },
//            dataType: "json",
//            cache: false,
//            async: false,
//            success: function (result) {
////                console.log(result);
//                if (result.length > 0) {
//                    //获取成功
//                    var str = '';
//                    for (var i = 0; i < result.length; i++) {
//                        if (result[i] == undefined) {
//                            break;
//                        }
//                        str += ' <option value="' + result[i] + '">第' + result[i] + '周</option>';
//                    }
//                    //先清空
//                    week.html('')
//                    week.append(str)
//
//                } else {
//                    layer.msg("所有周次都有排课了")
//                }
//            }
//            , error: function (data) {
//                alert(JSON.stringify(data));
//            }
//        })
//    }

    //获取课程
    $(document).ready(function () {
        var projectId = $("#pjid").val();
        var select = $("#projectId");
        var project = getProject();
        for (var i = 0; i <= project.length; i++) {
            if (project[i] == undefined) {
                break;
            }
            if (projectId == project[i].id) {
                var str = ' <option value="' + project[i].id + '" selected>' + project[i].projectName + '</option>';
                $(".battalionName").val(project[i].name);
            } else {
                var str = ' <option value="' + project[i].id + '" >' + project[i].projectName + '</option>';
            }
            select.append(str)
        }
        //如果select没有选中 选择第一个为默认的选择项
        if ($(select).find("option:selected").text() == undefined) {
            $("#projectId option:first").attr("selected", true);
            var name = $("#projectId").find("option:selected").text();
            $("#projectName").val(name);
        } else {
            var name = $("#projectId").find("option:selected").text();
            $("#projectName").val(name);
        }
    });

    //查询营
    $(document).ready(function () {
        var battalionId = $("#bid").val();
        var select = $("#battalionId");
        var battalion = getBattalion();
        for (var i = 0; i <= battalion.length; i++) {
            if (battalion[i] == undefined) {
                break;
            }
            if (battalionId == battalion[i].id) {
                var str = ' <option value="' + battalion[i].id + '" selected>' + battalion[i].name + '</option>';
                $(".battalionName").val(battalion[i].name);
            } else {
                var str = ' <option value="' + battalion[i].id + '" >' + battalion[i].name + '</option>';
            }
            select.append(str)
        }
        //如果select没有选中 选择第一个为默认的选择项
        if ($(select).find("option:selected").text() == undefined) {
            $("#battalionId option:first").attr("selected", true);
            var name = $("#battalionId").find("option:selected").text();
            $("#battalionName").val(name);
        } else {
            var name = $("#battalionId").find("option:selected").text();
            $("#battalionName").val(name);
        }
        //将连查出来
        selectCompany()
    })
    $("#battalionId").change(function () {
        //当前选择的option
        var name = $("#battalionId").find("option:selected").text();
        $("#battalionName").val(name);
        //顺手查询连
        selectCompany()
    })
</script>

<script>
    //查询连
    function selectCompany() {
        var battalionId = $("#battalionId").find("option:selected").val();
        var companyId = $("#cid").val();
        var select = $("#companyId");
        select.empty();
        var company = getCompany(battalionId);
        for (var i = 0; i <= company.length; i++) {
            if (company[i] == undefined) {
                break;
            }
            if (companyId == company[i].id) {
                var str = ' <option value="' + company[i].id + '" selected>' + company[i].name + '</option>';
                $(".battalionName").val(company[i].name);
            } else {
                var str = ' <option value="' + company[i].id + '" >' + company[i].name + '</option>';
            }
            select.append(str)
        }
        //如果select没有选中 选择第一个为默认的选择项
        if ($(select).find("option:selected").text() == undefined) {
            $("#companyId option:first").attr("selected", true);
            var name = $("#companyId").find("option:selected").text();
            $("#companyName").val(name);
        } else {
            var name = $("#companyId").find("option:selected").text();
            $("#companyName").val(name);
        }
        selectPlatoon()
    }


    $("#companyId").change(function () {
        //当前选择的option
        var name = $("#companyId").find("option:selected").text();
        $("#companyName").val(name);
        selectPlatoon()
    })
</script>

<script>
    //查询排
    function selectPlatoon() {
        var companyId = $("#companyId").find("option:selected").val();
        var platoonId = $("#pid").val();
        var select = $("#platoonId");
        select.empty();
        var platoon = getPlatoon(companyId);
        for (var i = 0; i <= platoon.length; i++) {
            if (platoon[i] == undefined) {
                break;
            }
            if (platoonId == platoon[i].id) {
                var str = ' <option value="' + platoon[i].id + '" selected>' + platoon[i].name + '</option>';
                $(".battalionName").val(platoon[i].name);
            } else {
                var str = ' <option value="' + platoon[i].id + '" >' + platoon[i].name + '</option>';
            }
            select.append(str)
        }
        //如果select没有选中 选择第一个为默认的选择项
        if ($(select).find("option:selected").text() == undefined) {
            $("#platoonId option:first").attr("selected", true);
            var name = $("#platoonId").find("option:selected").text();
            $("#platoonName").val(name);
        } else {
            var name = $("#platoonId").find("option:selected").text();
            $("#platoonName").val(name);
        }
    }
    $("#platoonId").change(function () {
        //当前选择的option
        var name = $("#platoonId").find("option:selected").text();
        $("#platoonName").val(name);
    })
</script>


<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
            closeModel(true);//关闭窗口
        }
    });
</script>
</body>
</html>
