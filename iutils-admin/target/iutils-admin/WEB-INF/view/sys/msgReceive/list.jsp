<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title><c:choose><c:when
            test="${page.entity.msgSend.type=='mail'}">站内信</c:when><c:otherwise>通知</c:otherwise></c:choose></title>
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
                            <div class="widget-title am-fl"><c:choose><c:when
                                    test="${page.entity.msgSend.type=='mail'}">站内信</c:when><c:otherwise>通知</c:otherwise></c:choose></div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                                <div class="am-btn-toolbar">
                                    <shiro:hasPermission name="sys:msgSend:create">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <button type="button" class="am-btn am-btn-default"
                                                    onclick="openModel(false,'${ctx}/sys/msgSend/create?type=${page.entity.msgSend.type}')">
                                                <span class="am-icon-plus"></span> 新建
                                            </button>
                                        </div>
                                    </shiro:hasPermission>
                                    <div class="am-btn-group am-btn-group-xs" style="margin-left: 10px;">
                                        <a href="${ctx}/sys/msgReceive?msgSend.type=${page.entity.msgSend.type}&status=-1"
                                           class="am-btn am-btn-default ${page.entity.status=='-1'?'am-active':''}">全部</a>
                                        <a href="${ctx}/sys/msgReceive?msgSend.type=${page.entity.msgSend.type}&status=0"
                                           class="am-btn am-btn-default ${page.entity.status=='0'?'am-active':''}">未读</a>
                                        <a href="${ctx}/sys/msgReceive?msgSend.type=${page.entity.msgSend.type}&status=1"
                                           class="am-btn am-btn-default ${page.entity.status=='1'?'am-active':''}">已读</a>
                                    </div>
                                    <div class="am-btn-group am-btn-group-xs" style="margin-left: 10px;">
                                        <a href="${ctx}/sys/msgSend?type=${page.entity.msgSend.type}&status=1"
                                           class="am-btn am-btn-default">已发送</a>
                                        <a href="${ctx}/sys/msgSend?type=${page.entity.msgSend.type}&status=0"
                                           class="am-btn am-btn-default">草稿</a>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                                <form id="searchForm" method="post">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <div class="tagsinput">
                                            <c:if test="${not empty page.key}"><span class="tags"><input type="hidden"
                                                                                                         name="key"
                                                                                                         value="${page.key}"/>关键字=${page.key} <a
                                                    href="javascript:;"
                                                    onclick="$(this).parent().remove()">x</a></span></c:if>
                                            <span class="am-select am-input-group-sm">
                                                     <input type="text" class="am-select-input" autocomplete="off"
                                                            style="border: none;"
                                                            placeholder="关键字"
                                                            am-data='[{"field":"key","desc":"关键字","type":"string"}]'>
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
                                        <th>序号</th>
                                        <th>发件人</th>
                                        <th>收件人</th>
                                        <th>时间</th>
                                        <th>标题</th>
                                        <th>内容</th>
                                        <th>级别</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="msgReceive" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${msgReceive.createUser.name}</td>
                                            <td>${msgReceive.updateUser.name}</td>
                                            <td><fmt:formatDate value="${msgReceive.updateDate}"
                                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>${msgReceive.msgSend.title}</td>
                                            <td>${fnc:replaceHtml(msgReceive.msgSend.content)}</td>
                                            <td><c:choose><c:when
                                                    test="${msgReceive.msgSend.level==1}">高</c:when><c:when
                                                    test="${msgReceive.msgSend.level==-1}">低</c:when><c:otherwise>普通</c:otherwise></c:choose></td>
                                            <td><c:choose><c:when
                                                    test="${msgReceive.status=='1'}">已读</c:when><c:otherwise>未读</c:otherwise></c:choose></td>
                                            <td>
                                                <a href="javascript:;"
                                                   onclick="openModel(false,'${ctx}/sys/msgReceive/update?id=${msgReceive.id}')"
                                                   title="阅读消息"><span class="am-icon-eye"></span></a>
                                                <a href="${ctx}/sys/msgReceive/delete?id=${msgReceive.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                   onclick="return confirm('确认要删除该条数据吗？', this.href)" title="删除"><span
                                                        class="am-text-danger am-icon-trash-o"></span></a></td>
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
