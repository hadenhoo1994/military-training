<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>任务调度</title>
    <%@ include file="../../include/head.jsp" %>
    <link href="${ctxStatic}/custom/css/amazeui.select.css" type="text/css" rel="stylesheet" charset="UTF-8"/>
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
                            <div class="widget-title am-fl">任务调度</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <button type="button" class="am-btn am-btn-default am-btn-success"
                                                onclick="openModel(false,'${ctx}/scheduleJob/create')"><span
                                                class="am-icon-plus"></span> 新增
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                                <form id="searchForm" action="${ctx}/scheduleJob" method="post">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <div class="tagsinput">
                                            <c:if test="${not empty page.entity.jobName}"><span class="tags"><input
                                                    type="hidden" name="jobName"
                                                    value="${page.entity.jobName}"/>任务名称=${page.entity.jobName} <a
                                                    href="javascript:;"
                                                    onclick="$(this).parent().remove()">x</a></span></c:if>
                                            <span class="am-select am-input-group-sm">
                                                     <input type="text" class="am-select-input" autocomplete="off"
                                                            style="border: none;"
                                                            placeholder="关键字"
                                                            am-data='[{"field":"jobName","desc":"任务名称","type":"string"}]'>
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
                                        <th>任务名称</th>
                                        <th>任务分组</th>
                                        <th>表达式</th>
                                        <th>任务状态</th>
                                        <th>执行方法</th>
                                        <th>修改时间</th>
                                        <th>描述</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="scheduleJob" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${scheduleJob.jobName}</td>
                                            <td>${scheduleJob.jobGroup}</td>
                                            <td>${scheduleJob.cron}</td>
                                            <td>${scheduleJob.status=='1'?'<span class="am-badge am-badge-success am-radius">启用</span>':'<span class="am-badge am-badge-danger am-radius">停用</span>'}</td>
                                            <td>${scheduleJob.methodName}</td>
                                            <td><fmt:formatDate value="${scheduleJob.updateDate}"
                                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>${scheduleJob.remarks}</td>
                                            <td>
                                                <c:if test="${scheduleJob.status=='2'}">
                                                    <a href="${ctx}/scheduleJob/resumeJob?id=${scheduleJob.id}&status=1&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                       onclick="return confirm('确认要恢复该任务吗？', this.href)"
                                                       title="恢复"><span
                                                            class="am-text-success am-icon-reply"></span></a>
                                                </c:if>
                                                <c:if test="${scheduleJob.status=='1'}">
                                                    <a href="${ctx}/scheduleJob/runAJobNow?id=${scheduleJob.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                       onclick="return confirm('确认要立即执行该任务吗？', this.href)" title="立即执行"><span
                                                            class="am-text-success am-icon-play-circle"></span></a>
                                                    <a href="${ctx}/scheduleJob/changeJobStatus?id=${scheduleJob.id}&status=0&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                       onclick="return confirm('确认要停止该任务吗？', this.href)"
                                                       title="停止"><span class="am-text-danger am-icon-stop"></span></a>
                                                    <a href="${ctx}/scheduleJob/pauseJob?id=${scheduleJob.id}&status=2&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                       onclick="return confirm('确认要暂停该任务吗？', this.href)"
                                                       title="暂停"><span
                                                            class="am-text-warning am-icon-pause"></span></a>
                                                </c:if>
                                                <c:if test="${scheduleJob.status=='0'}">
                                                <a href="${ctx}/scheduleJob/changeJobStatus?id=${scheduleJob.id}&status=1&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                   onclick="return confirm('确认要启用该任务吗？', this.href)" title="启用"><span
                                                        class="am-text-success am-icon-play"></span></a>
                                                <a href="javascript:;"
                                                   onclick="openModel(false,'${ctx}/scheduleJob/update?id=${scheduleJob.id}')"
                                                   title="修改"><span class="am-icon-pencil"></span></a>
                                                <a href="${ctx}/scheduleJob/delete?id=${scheduleJob.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                   onclick="return confirm('确认要删除该条数据吗？', this.href)" title="删除"><span
                                                        class="am-text-danger am-icon-trash-o"></span></a></td>
                                            </c:if>

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
