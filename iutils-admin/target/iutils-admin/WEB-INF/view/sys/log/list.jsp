<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>日志记录</title>
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
                            <div class="widget-title am-fl">访问日志</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <shiro:hasPermission name="sys:slog:edit"><!-- 设置是否有删除权限标注 --><c:set
                                            var="hasDel" value="true"></c:set>
                                        <button type="button" id="alldelete"
                                                class="am-btn am-btn-default am-btn-danger"><span
                                                class="am-icon-trash-o"></span> 删除
                                        </button>
                                        </shiro:hasPermission>
                                    </div>
                                </div>
                            </div>
                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                                <form id="searchForm" action="${ctx}/slog" method="post">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <input id="orderBy" name="orderBy" type="hidden" value="${page.entity.orderBy}"/>
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <div class="tagsinput">
                                            <c:if test="${not empty page.entity.username}"><span class="tags"><input
                                                    type="hidden" name="username"
                                                    value="${page.entity.username}"/>用户=${page.entity.username} <a
                                                    href="javascript:;"
                                                    onclick="$(this).parent().remove()">x</a></span></c:if>
                                            <span class="am-select am-input-group-sm">
                                                     <input type="text" class="am-select-input" autocomplete="off"
                                                            style="border: none;"
                                                            placeholder="关键字"
                                                            am-data='[{"field":"username","desc":"用户","type":"string"}]'>
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
                                <form class="am-form">
                                    <table id="contentTable"
                                           class="am-table am-table-compact am-table-striped tpl-table-black">
                                        <thead>
                                        <tr>
                                            <c:if test="${hasDel}">
                                                <th><input name="checkboxall" type="checkbox"
                                                           style="margin-top: -17px;"/></th>
                                            </c:if>
                                            <th>序号</th>
                                            <th class="orderBy" onclick="order('b.username')">用户 <c:if
                                                    test="${page.entity.orderBy=='b.username asc'}"><i
                                                    class="am-icon-sort-alpha-asc"></i></c:if><c:if
                                                    test="${page.entity.orderBy=='b.username desc'}"><i
                                                    class="am-icon-sort-alpha-desc"></i></c:if></th>
                                            <%--<th>菜单</th>--%>
                                            <th>操作IP</th>
                                            <th>请求地址</th>
                                            <th>操作方式</th>
                                            <th>耗时</th>
                                            <th>是否异常</th>
                                            <th class="orderBy" onclick="order('a.create_date')">记录时间 <c:if
                                                    test="${page.entity.orderBy=='a.create_date asc'}"><i
                                                    class="am-icon-sort-alpha-asc"></i></c:if><c:if
                                                    test="${page.entity.orderBy=='a.create_date desc'}"><i
                                                    class="am-icon-sort-alpha-desc"></i></c:if></th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="slog" varStatus="status">
                                            <tr>
                                                <c:if test="${hasDel}">
                                                    <td><input name="checkbox" type="checkbox" value="${slog.id}"/></td>
                                                </c:if>
                                                <td>${status.index+1}</td>
                                                <td>${slog.user.username}</td>
                                                    <%--<td>${slog.menu}</td>--%>
                                                <td>${slog.remoteAddr}</td>
                                                <td title="${slog.requestUri}">
                                                    <c:choose>
                                                        <c:when test="${fn:length(slog.requestUri)>30}">
                                                            <c:out value="${fn:substring(slog.requestUri, 0, 27)}..."/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${slog.requestUri}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${slog.method}</td>
                                                <td><span
                                                        class="am-badge am-badge-secondary am-radius">${slog.timeConsuming}</span>
                                                </td>
                                                <td>${slog.exception==null?'<span class="am-badge am-badge-success am-radius">正常</span>':'<span class="am-badge am-badge-danger am-radius">异常</span>'}</td>
                                                <td><fmt:formatDate value="${slog.createDate}"
                                                                    pattern="MM-dd HH:mm:ss"/></td>
                                                <td><a href="javascript:;"
                                                       onclick="openModel(false,'${ctx}/slog/update?id=${slog.id}')"
                                                       title="查看详细"><span class="am-icon-eye"></span></a></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </form>
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
<script type="text/javascript">
    $(document).ready(function () {
        //多选按钮的全选和反选
        $("input[name='checkboxall']").click(function () {
            $("input[name='checkbox']").prop("checked", $(this).is(":checked"));
        });
        //批量删除
        $("#alldelete").click(function () {
            var ids = "";
            $("input[name='checkbox']:checked").each(function () {
                ids += $(this).val() + ",";
            });
            if (ids != '') {
                confirm('确认要批量删除数据吗？', '${ctx}/slog/delete?ids=' + ids + "&pageNo=" + $("#pageNo").val() + "&pageSize=" + $("#pageSize").val());
            } else {
                showMsg('请勾选要删除的数据');
            }
        });
    });
</script>
</body>
</html>
