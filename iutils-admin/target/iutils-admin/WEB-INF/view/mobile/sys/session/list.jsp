<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>session管理</title>
    <%@ include file="../../include/head.jsp" %>
    <link href="${ctxStatic}/custom/css/amazeui.select.css" type="text/css" rel="stylesheet" charset="UTF-8"/>
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
                            <div class="widget-title am-fl">会话管理</div>
                        </div>
                        <div class="widget-body am-fr">
                            <%--<div class="am-u-sm-12 am-u-md-3 am-u-lg-3">--%>
                            <%--<div class="am-btn-toolbar">--%>
                            <%--<div class="am-btn-group am-btn-group-xs">--%>
                            <%--<button type="button" class="am-btn am-btn-default am-btn-success"--%>
                            <%--onclick="openModel(false,'${ctx}/sys/session/create')"><span class="am-icon-plus"></span> 新增--%>
                            <%--</button>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="am-u-sm-12 am-u-md-9 am-u-lg-9">--%>
                            <%--<form id="searchForm" action="${ctx}/sys/session" method="post">--%>
                            <%--<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>--%>
                            <%--<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>--%>
                            <%--<div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">--%>
                            <%--<div class="tagsinput">--%>
                            <%--<c:if test="${not empty page.key}"><span class="tags"><input type="hidden" name="key" value="${page.key}" />关键字=${page.key} <a href="javascript:;" onclick="$(this).parent().remove()">x</a></span></c:if>--%>
                            <%--<span class="am-select am-input-group-sm">--%>
                            <%--<input type="text" class="am-select-input" autocomplete="off" style="border: none;"--%>
                            <%--placeholder="关键字" am-data='[{"field":"key","desc":"关键字","type":"string"}]'>--%>
                            <%--<ul class="am-select-ul"></ul>--%>
                            <%--</span>--%>
                            <%--</div>--%>
                            <%--<span class="am-input-group-btn">--%>
                            <%--<button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="submit" onclick="initSearchForm()"></button>--%>
                            <%--</span>--%>
                            <%--</div>--%>
                            <%--</form>--%>
                            <%--</div>--%>

                            <div class="am-u-sm-12">
                                <table id="contentTable"
                                       class="am-table am-table-compact am-table-striped tpl-table-black">
                                    <thead>
                                    <tr>
                                        <th>会话编号</th>
                                        <th>用户编号</th>
                                        <th>IP地址</th>
                                        <th>过期时间(毫秒)</th>
                                        <th>启动时间</th>
                                        <th>最后访问时间</th>
                                        <th>已强制退出</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="session" varStatus="status">
                                        <tr>
                                            <td>${session.id}</td>
                                            <td>${session.user.username}</td>
                                            <td>${session.ip}</td>
                                            <td>${session.timeout}</td>
                                            <td><fmt:formatDate value="${session.createDate}"
                                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td><fmt:formatDate value="${session.updateDate}"
                                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>${fnc:isDbForceLogout(session) ? '<span class="am-badge am-badge-danger am-radius">是</span>':'<span class="am-badge am-badge-success am-radius">否</span>'}</td>
                                            <td>
                                                <c:if test="${not fnc:isDbForceLogout(session)}">
                                                    <a href="${ctx}/sys/session/forceLogout?id=${session.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                       onclick="return confirm('确认要强制退出吗？', this.href)"
                                                       title="强制退出"><span
                                                            class="am-text-danger am-icon-sign-out"></span></a>
                                                </c:if>
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
<script type="text/javascript" src="${ctxStatic}/custom/js/amazeui.select.js"></script>
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
