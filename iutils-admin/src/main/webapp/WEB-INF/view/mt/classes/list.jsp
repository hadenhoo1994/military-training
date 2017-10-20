<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>班级表</title>
    <%@ include file="../../include/head.jsp"%>
    <link href="${ctxStatic}/custom/css/amazeui.select.css" type="text/css" rel="stylesheet" charset="UTF-8" />
    <style>
        .tpl-content-wrapper{margin-left:0}
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
                            <div class="widget-title am-fl">班级表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <button type="button" class="am-btn am-btn-default am-btn-success"
                                                onclick="openModel(false,'${ctx}/mt/classes/create')"><span class="am-icon-plus"></span> 新增
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                                <form id="searchForm" action="${ctx}/mt/classes" method="post">
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <div class="tagsinput">
                                            <c:if test="${not empty page.key}"><span class="tags"><input type="hidden" name="key" value="${page.key}" />关键字=${page.key} <a href="javascript:;" onclick="$(this).parent().remove()">x</a></span></c:if>
                                                <span class="am-select am-input-group-sm">
                                                     <input type="text" class="am-select-input" autocomplete="off" style="border: none;"
                                                            placeholder="关键字" am-data='[{"field":"key","desc":"关键字","type":"string"}]'>
                                                    <ul class="am-select-ul"></ul>
                                                </span>
                                        </div>
                                        <span class="am-input-group-btn">
                                            <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="submit" onclick="initSearchForm()"></button>
                                        </span>
                                    </div>
                                </form>
                            </div>

                            <div class="am-u-sm-12">
                                <table id="contentTable" class="am-table am-table-compact am-table-striped tpl-table-black">
                                    <thead>
                                    <tr>
                                            <th>主键id</th>
                                            <th>班级名称</th>
                                            <th>班级所属专业id</th>
                                            <th>班级所属专业名称</th>
                                            <th>班级所属系id</th>
                                            <th>班级所属系名称</th>
                                            <th>第几届id</th>
                                            <th>第几届</th>
                                            <th>班级学生数</th>
                                            <th>班级班主任id</th>
                                            <th>班级班主任名称</th>
                                            <th>创建人</th>
                                            <th>创建时间</th>
                                            <th>修改人</th>
                                            <th>修改时间</th>
                                            <th>备注</th>
                                            <th>状态 0:正常 1:删除</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="classes" varStatus="status">
                                        <tr>
                                                <td>${classes.id}</td>
                                                <td>${classes.name}</td>
                                                <td>${classes.professionId}</td>
                                                <td>${classes.professionName}</td>
                                                <td>${classes.departmentId}</td>
                                                <td>${classes.departmentName}</td>
                                                <td>${classes.gradeId}</td>
                                                <td>${classes.gradeName}</td>
                                                <td>${classes.studentNum}</td>
                                                <td>${classes.directorId}</td>
                                                <td>${classes.directorName}</td>
                                                <td>${classes.createBy}</td>
                                                <td>${classes.createDate}</td>
                                                <td>${classes.updateBy}</td>
                                                <td>${classes.updateDate}</td>
                                                <td>${classes.remarks}</td>
                                                <td>${classes.status}</td>
                                            <td>
                                                <a href="javascript:;" onclick="openModel('修改班级表','${ctx}/mt/classes/update?id=${classes.id}')" title="编辑"><span class="am-icon-pencil"></span></a>
                                                <a href="${ctx}/mt/classes/${classes.id}/delete?pageNo=${page.pageNo}&pageSize=${page.pageSize}" onclick="return confirm('确认要删除该条数据吗？', this.href)" title="删除"><span class="am-text-danger am-icon-trash-o"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="am-u-lg-12 am-cf">
                                <%@ include file="../../utils/pagination.jsp"%>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../include/bottom.jsp"%>
<script type="text/javascript" src="${ctxStatic}/custom/js/amazeui.select.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if(msg!=''){
            showMsg(msg);
        }
    });
</script>
</body>
</html>
