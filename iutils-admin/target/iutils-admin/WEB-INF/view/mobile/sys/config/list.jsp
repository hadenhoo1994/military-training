<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>公共配置表</title>
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
                            <div class="widget-title am-fl">公共配置</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <button type="button" class="am-btn am-btn-default am-btn-success"
                                                onclick="openModel(false,'${ctx}/sys/config/create')"><span
                                                class="am-icon-plus"></span> 新增
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                                <form id="searchForm" action="${ctx}/sys/config" method="post">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <div class="tagsinput">
                                            <c:if test="${not empty page.entity.sysName}"><span class="tags"><input
                                                    type="hidden" name="sysName"
                                                    value="${page.entity.sysName}"/>系统名称=${page.entity.sysName} <a
                                                    href="javascript:;"
                                                    onclick="$(this).parent().remove()">x</a></span></c:if>
                                            <c:if test="${not empty page.entity.moduleName}"><span class="tags"><input
                                                    type="hidden" name="moduleName" value="${page.entity.moduleName}"/>模块名称=${page.entity.moduleName} <a
                                                    href="javascript:;"
                                                    onclick="$(this).parent().remove()">x</a></span></c:if>
                                            <span class="am-select am-input-group-sm">
                                                     <input type="text" class="am-select-input" autocomplete="off"
                                                            style="border: none;"
                                                            placeholder="关键字"
                                                            am-data='[{"field":"sysName","desc":"系统名称","type":"string"},{"field":"moduleName","desc":"模块名称","type":"string"}]'>
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
                                        <th>系统名称</th>
                                        <th>模块名称</th>
                                        <th>key</th>
                                        <th>value</th>
                                        <th>备注</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="config" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${config.sysName}</td>
                                            <td>${config.moduleName}</td>
                                            <td>${config.configName}</td>
                                            <td>${config.configValue}</td>
                                            <td>${config.remarks}</td>
                                            <td>
                                                <a href="javascript:;"
                                                   onclick="openModel(false,'${ctx}/sys/config/update?id=${config.id}')"
                                                   title="编辑"><span class="am-icon-pencil"></span></a>
                                                <a href="${ctx}/sys/config/${config.id}/delete?pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                   onclick="return confirm('确认要删除该条数据吗？', this.href)" title="删除"><span
                                                        class="am-text-danger am-icon-trash-o"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
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
