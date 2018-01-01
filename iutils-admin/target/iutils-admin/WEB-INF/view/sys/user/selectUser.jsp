<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>用户选择</title>
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
                            <div class="widget-title am-fl">用户列表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-form-group">
                                        <button type="button" class="am-btn am-btn-danger am-btn-xs"
                                                onclick="closeModel(false)">取消选择
                                        </button>
                                        <button type="button" id="okSelect" class="am-btn am-btn-primary am-btn-xs"
                                                onclick="">确认选择
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                                <form id="searchForm" action="${ctx}/user/selectUser" method="post">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <input id="organizationId" name="organizationId" type="hidden"
                                           value="${page.entity.organizationId}"/>
                                    <input id="users" name="users" type="hidden"
                                           value="<c:forEach items="${users}" var="item">${item},</c:forEach>"/>
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <div class="tagsinput">
                                            <c:if test="${not empty page.entity.username}"><span class="tags"><input
                                                    type="hidden" name="username"
                                                    value="${page.entity.username}"/>账号=${page.entity.username} <a
                                                    href="javascript:;"
                                                    onclick="$(this).parent().remove()">x</a></span></c:if>
                                            <c:if test="${not empty page.entity.name}"><span class="tags"><input
                                                    type="hidden" name="name"
                                                    value="${page.entity.name}"/>姓名=${page.entity.name} <a
                                                    href="javascript:;"
                                                    onclick="$(this).parent().remove()">x</a></span></c:if>
                                            <c:if test="${not empty page.entity.mobile}"><span class="tags"><input
                                                    type="hidden" name="mobile"
                                                    value="${page.entity.mobile}"/>联系方式=${page.entity.mobile} <a
                                                    href="javascript:;"
                                                    onclick="$(this).parent().remove()">x</a></span></c:if>
                                            <span class="am-select am-input-group-sm">
                                                     <input type="text" class="am-select-input" autocomplete="off"
                                                            style="border: none;"
                                                            placeholder="关键字"
                                                            am-data='[{"field":"username","desc":"账号","type":"string"},{"field":"name","desc":"姓名","type":"string"},{"field":"mobile","desc":"联系方式","type":"number"}]'>
                                                    <ul class="am-select-ul"></ul>
                                                </span>
                                        </div>
                                        <span class="am-input-group-btn">
                                        <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search"
                                                type="submit" onclick="initSearchForm()"></button>
                                      </span>
                                    </div>
                                </form>
                            </div>

                            <div class="am-u-sm-12">
                                <table id="contentTable"
                                       class="am-table am-table-compact am-table-striped tpl-table-black">
                                    <thead>
                                    <tr>
                                        <th>选择</th>
                                        <th>归属机构</th>
                                        <th>账号</th>
                                        <th>姓名</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="user" varStatus="status">
                                        <tr>
                                            <td><input value="${user.id}" type="checkbox" onclick="clickSelect(this)"/>
                                            </td>
                                            <td>${user.organization.name}</td>
                                            <td>${user.username}</td>
                                            <td>${user.name}</td>
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
                    $("#organizationId").val(treeNode.id);
                    initSearchForm();
                    $("#searchForm").submit();
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

            //确认选择
            $("#okSelect").click(function () {
                var ids = $("#users").val();
                if (ids != '') {
                    //过滤处理
                    var users = ids.split(",");
                    var idss = "";
                    for (var i = 0; i < users.length; i++) {
                        if (users[i] != "") {
                            idss += users[i] + ",";
                        }
                    }
                    //调用父级的处理方法
                    if (idss != "") {
                        parent.callSelectUser(idss);
                        closeModel(false);
                    } else {
                        showMsg('请先选择用户');
                    }
                } else {
                    showMsg('请先选择用户');
                }
            });

            //初始化选择
            var idArray = $("#users").val().split(",");
            for (var i = 0; i < idArray.length; i++) {
                $("input[value='" + idArray[i] + "']").attr("checked", "checked");
            }
        });
    });

    //点击选择
    function clickSelect(o) {
        var ids = $("#users").val();
        if ($(o).is(':checked')) {
            ids += $(o).val() + ",";
        } else {
            var idArray = $("#users").val().split(",");
            ids = removeArray(idArray, $(o).val());
        }
        $("#users").val(ids);
    }

    //删除数组
    function removeArray(vals, val) {
        var temp = [];
        for (var i = 0; i < vals.length - 1; i++) {
            if (vals[i] != val) {
                temp.push(vals[i])
            }
        }
        if (temp.length > 0) {
            return temp.toString() + ",";
        } else {
            return "";
        }

    }
</script>
</body>
</html>
