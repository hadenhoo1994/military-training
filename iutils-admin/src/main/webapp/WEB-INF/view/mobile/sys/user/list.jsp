<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>用户管理</title>
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
                                    <div class="am-btn-group am-btn-group-xs">
                                        <shiro:hasPermission name="sys:user:create">
                                            <button type="button" class="am-btn am-btn-default am-btn-success"
                                                    onclick="openModel(false,'${ctx}/user/create?organizationId=${page.entity.organizationId}')">
                                                <span class="am-icon-plus"></span> 新增
                                            </button>
                                        </shiro:hasPermission>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                                <form id="searchForm"
                                      action="${ctx}/user/list?organizationId=${page.entity.organizationId}"
                                      method="post">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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

                            <div class="am-u-sm-12 am-scrollable-horizontal">
                                <table id="contentTable"
                                       class="am-table am-table-compact am-text-nowrap tpl-table-black" width="100%">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>归属机构</th>
                                        <th>账号</th>
                                        <th>姓名</th>
                                        <th>联系方式</th>
                                        <th>是否部门管理员</th>
                                        <th>是否锁定</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="user" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${user.organization.name}</td>
                                            <td>${user.username}</td>
                                            <td>${user.name}</td>
                                            <td>${user.mobile}</td>
                                            <td>${user.isDept?'<span class="am-badge am-badge-danger am-radius">是</span>':'<span class="am-badge am-badge-success am-radius">否</span>'}</td>
                                            <td>${user.locked?'<span class="am-badge am-badge-danger am-radius">是</span>':'<span class="am-badge am-badge-success am-radius">否</span>'}</td>
                                            <td>
                                                <shiro:hasPermission name="sys:user:update"><a href="javascript:;"
                                                                                               onclick="openModel(false,'${ctx}/user/update?id=${user.id}')"
                                                                                               title="编辑"><span
                                                        class="am-icon-pencil"></span></a></shiro:hasPermission>
                                                <shiro:hasPermission name="sys:user:delete"><c:if
                                                        test="${user.id!=1 && user.id!=fnc:getLoginUser().id}">
                                                    <a href="${ctx}/user/delete?id=${user.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                       onclick="return confirm('确认要删除该条数据吗？', this.href)"
                                                       title="删除"><span
                                                            class="am-text-danger am-icon-trash-o"></span></a>
                                                </c:if></shiro:hasPermission>
                                                <a href="javascript:;"
                                                   onclick="openModel(false,'${ctx}/user/${user.id}/changePassword')"
                                                   title="改密"><span class="am-text-success am-icon-key"></span></a>
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
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
        }
    });
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
                    location.href = "${ctx}/user/list?organizationId=" + treeNode.id;
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
</body>
</html>