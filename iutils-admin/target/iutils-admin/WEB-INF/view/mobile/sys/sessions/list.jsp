<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<head>
    <title>会话管理</title>
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
                            <div class="widget-title am-fl">会话列表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-scrollable-horizontal">
                                <table id="contentTable"
                                       class="am-table am-table-compact am-text-nowrap tpl-table-black" width="100%">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>用户</th>
                                        <th>主机地址</th>
                                        <th>最后访问时间</th>
                                        <th>已强制退出</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${sessions}" var="session" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${fnc:principal(session)}</td>
                                            <td>${session.host}</td>
                                            <td><fmt:formatDate value="${session.lastAccessTime}"
                                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>${fnc:isForceLogout(session) ? '<span class="am-badge am-badge-danger am-radius">是</span>':'<span class="am-badge am-badge-success am-radius">否</span>'}</td>
                                            <td><c:if test="${not fnc:isForceLogout(session)}">
                                                <a href="${ctx}/sessions/${session.id}/forceLogout"
                                                   onclick="return confirm('确认要强制退出吗？', this.href)" title="强制退出"><span
                                                        class="am-text-danger am-icon-sign-out"></span></a>
                                            </c:if></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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