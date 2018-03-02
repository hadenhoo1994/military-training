<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>排课表</title>
    <%@ include file="../../include/head.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/3rd-lib/jquery-ztree/css/zTreeStyle.css">
    <link href="${ctxStatic}/custom/css/amazeui.select.css" type="text/css" rel="stylesheet" charset="UTF-8"/>
    <style>
        .tpl-content-wrapper {
            margin-left: 0
        }

        .theme-black .widget .ztree li a {
            color: #ffffff
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
                <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">组织机构</div>
                        </div>
                        <div class="widget-body widget-body-lg am-fr">
                            <div class="am-scrollable-horizontal">
                                <ul id="tree" class="ztree"></ul><!-- 组织机构列表 -->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">排课表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <button type="button" class="am-btn am-btn-default am-btn-success"
                                                onclick="toList()"><span class="am-icon-plus"></span> 完成
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                            </div>

                            <div class="am-u-sm-12">
                                <table id="contentTable"
                                       class="am-table am-table-compact am-table-striped tpl-table-black">
                                    <thead>
                                    <tr>
                                        <th>节次</th>
                                        <th>周一</th>
                                        <th>周二</th>
                                        <th>周三</th>
                                        <th>周四</th>
                                        <th>周五</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="course" varStatus="status">
                                        <tr>
                                            <td>第${course.days}节</td>
                                            <td>
                                                <button onclick="updateThis(this)" value="1,${status.index+1}">${course.classes1}</button>
                                            </td>
                                            <td>
                                                <button onclick="updateThis(this)" value="2,${status.index+1}">${course.classes2}</button>
                                            </td>
                                            <td>
                                                <button onclick="updateThis(this)" value="3,${status.index+1}">${course.classes3}</button>
                                            </td>
                                            <td>
                                                <button onclick="updateThis(this)" value="4,${status.index+1}">${course.classes4}</button>
                                            </td>
                                            <td>
                                                <button onclick="updateThis(this)" value="5,${status.index+1}">${course.classes5}</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="am-u-lg-12 am-cf">
                                <%@ include file="../../utils/pagination.jsp" %>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../include/bottom.jsp" %>
<script src="${ctxStatic}/3rd-lib/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/custom/js/amazeui.select.js"></script>
<script src="/static/res/layui/layui.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
        }
    });

    function toList() {
        var organizationId = '${organizationId}'
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.confirm('一经保存将生成学生的课程信息,请全部课程都设置好后再保存,是否已设置好全部课程?', {
                btn: ['确定', '取消'] //可以无限个按钮
                ,btn2: function(index, layero){
                    //按钮【按钮2】的回调
                }
            }, function(index, layero){
                //按钮【按钮1】的回调
                location.href = "${ctx}/mt/course?organizationId=" + organizationId;
            });
        });





    }
</script>
<script>
    $(function () {
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    location.href = "${ctx}/mt/course?organizationId=" + treeNode.id;
                }
            }
        };
        var zNodes = [
            <c:forEach items="${organizationList}" var="o" varStatus="status">
            {id:${o.id}, pId:${o.parentId}, name: "${o.name}", open:${o.rootNode}}<c:if test="${!status.last}">, </c:if>
            </c:forEach>
        ];
        $(document).ready(function () {
            var ztree = $.fn.zTree.init($("#tree"), setting, zNodes);
            ztree.expandAll(true);
        });
    });
</script>
<script>
    function updateThis(classes) {
        var thisButton = $(classes);
        //1.获取课程
        var classesArr = new Array()
        $.ajax({
            type: "POST",
            url: "/rest/getProject",
            dataType: "json",
            cache: false,
            async: false,
            success: function (result) {
                if (result.code == 200) {
                    //获取成功
                    classesArr = result.results;
                } else {
                    layer.msg(result.msg)
                }
            }
            , error: function (data) {
                alert(JSON.stringify(data));
            }
        })
        //将课程遍历成为一个个button
        var str = "";
        $.each(classesArr,function(n,value) {
            var projectName = value.projectName ;
            str += "<button onclick='setClassesed("+thisButton.val()+",this)'>" + projectName + "</button>";
        });
            //2.弹出课程选择框
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                title: ['请选择课程', 'font-size:18px;'],
                type: 1,
                content: str //这里content是一个普通的String
            });
        });
    }

    var setClassesed =  function (days,classes,thisBtn) {
        var organizationId = '${organizationId}'
        var projectName = $(thisBtn).html();
        console.log(organizationId)
        console.log(days);       //周几,节次
        console.log(classes)
        console.log(projectName);
        //ajax请求将课程修改 然后保存在重新请求当前页面
        $.ajax({
            type: "POST",
            url: "${ctx}/mt/course/setCourse",
            data:{
                organizationId:organizationId,
                days:classes,
                classes:days,
                projectName:projectName
            },
            dataType: "json",
            cache: false,
            async: false,
            success: function (result) {
                if (result.code == 200) {
                    //获取成功刷新当前页面
                    location.href = "${ctx}/mt/course/updateClasses?organizationId=" + organizationId;
                } else {
                    layer.msg(result.msg)
                }
            }
            , error: function (data) {
                alert(JSON.stringify(data));
            }
        })
    }
</script>
</body>
</html>
