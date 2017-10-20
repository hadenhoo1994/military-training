<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>资源管理列表</title>
    <%@ include file="../../include/head.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/3rd-lib/jquery-ztree/css/zTreeStyle.css">
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
                <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">资源树</div>
                        </div>
                        <div class="widget-body widget-body-lg am-fr">
                            <div class="am-scrollable-horizontal">
                                <ul id="tree" class="ztree"></ul><!-- 资源树 -->
                            </div>
                        </div>
                    </div>
                </div>

                <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">资源列表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <shiro:hasPermission name="sys:resource:edit">
                                            <button type="button" class="am-btn am-btn-default am-btn-success"
                                                    onclick="openModel(false,'${ctx}/resource/create?id=${page.entity.id}')">
                                                <span class="am-icon-plus"></span> 新增
                                            </button>
                                        </shiro:hasPermission>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                                <form id="searchForm" action="${ctx}/resource" method="post" style="display: none;">
                                    <input name="id" value="${page.entity.id}"/>
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">

                                    </div>
                                </form>
                            </div>

                            <div class="am-u-sm-12">
                                <table id="contentTable"
                                       class="am-table am-table-compact am-table-striped tpl-table-black">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>归属资源</th>
                                        <th>资源名称</th>
                                        <th>资源类型</th>
                                        <th>链接</th>
                                        <th>排序</th>
                                        <th>是否可用</th>
                                        <th>权限标识</th>
                                        <shiro:hasPermission name="sys:resource:edit">
                                            <th>操作</th>
                                        </shiro:hasPermission>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="resource" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${resource.resource.name}</td>
                                            <td>${resource.name}</td>
                                            <td><span
                                                    class="am-badge am-badge-primary am-radius">${resource.type.info}</span>
                                            </td>
                                            <td>${resource.url}</td>
                                            <td>${resource.sort}</td>
                                            <td>${resource.available?'<span class="am-badge am-badge-success am-radius">可用</span>':'<span class="am-badge am-badge-danger am-radius">禁用</span>'}</td>
                                            <td>${resource.permission}</td>
                                            <shiro:hasPermission name="sys:resource:edit">
                                                <td>
                                                    <a href="javascript:;"
                                                       onclick="openModel(false,'${ctx}/resource/update?id=${resource.id}')"
                                                       title="编辑"><span class="am-icon-pencil"></span></a>
                                                    <a href="${ctx}/resource/delete?id=${resource.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                       onclick="return confirm('确认要删除该条数据吗？', this.href)"
                                                       title="删除"><span
                                                            class="am-text-danger am-icon-trash-o"></span></a></td>
                                            </shiro:hasPermission>
                                        </tr>
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
                    location.href = "${ctx}/resource?id=" + treeNode.id;
                }
            }
        };
        var zNodes = [
            <c:forEach items="${resourceList}" var="o" varStatus="status">
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
    $(document).ready(function () {
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
        }
    });
</script>
</body>
</html>