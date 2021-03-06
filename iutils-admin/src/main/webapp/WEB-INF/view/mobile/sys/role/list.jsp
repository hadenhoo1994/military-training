<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>角色管理</title>
    <%@ include file="../../include/head.jsp" %>
    <style type="text/css">
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
                            <div class="widget-title am-fl">角色管理</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <shiro:hasPermission name="sys:role:edit">
                                            <button type="button" class="am-btn am-btn-default am-btn-success"
                                                    onclick="openModel(false,'${ctx}/role/update')"><span
                                                    class="am-icon-plus"></span> 新增
                                            </button>
                                        </shiro:hasPermission>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                                <form id="searchForm" action="${ctx}/role" method="post" style="display: none;">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">

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
                                        <th>角色名称</th>
                                        <th>角色标识</th>
                                        <th>是否可用</th>
                                        <th>备注</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="role" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${role.organization.name}</td>
                                            <td>${role.name}</td>
                                            <td>${role.role}</td>
                                            <td>${role.available?'<span class="am-badge am-badge-success am-radius">可用</span>':'<span class="am-badge am-badge-danger am-radius">禁用</span>'}</td>
                                            <td>${role.remarks}</td>
                                            <td>
                                                <shiro:hasPermission name="sys:role:view"><a href="javascript:;"
                                                                                             onclick="openModel(false,'${ctx}/role/update?id=${role.id}')"
                                                                                             title="编辑"><span
                                                        class="am-icon-pencil"></span></a></shiro:hasPermission>
                                                <shiro:hasPermission name="sys:role:edit"><c:if test="${role.id>1}">
                                                    <a href="${ctx}/role/delete?id=${role.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                       onclick="return confirm('确认要删除该条数据吗？', this.href)"
                                                       title="删除"><span
                                                            class="am-text-danger am-icon-trash-o"></span></a>
                                                </c:if></shiro:hasPermission>
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
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
        }
    });
</script>
</body>
</html>