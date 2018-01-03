<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>用户表</title>
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
                            <div class="widget-title am-fl">用户表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <button type="button" class="am-btn am-btn-default am-btn-success"
                                                onclick="openModel(false,'${ctx}/mt/userInfo/create')"><span class="am-icon-plus"></span> 新增
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
                                <form id="searchForm" action="${ctx}/mt/userInfo" method="post">
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
                                            <th>序号</th>
                                            <th>性别</th>
                                            <th>姓名</th>
                                            <th>班级名称</th>
                                            <th>专业名称</th>
                                            <th>系别名称</th>
                                            <th>排名称</th>
                                            <th>连名称</th>
                                            <th>营名称</th>
                                            <th>手机号码</th>
                                            <th>qq号码</th>
                                            <th>微信号</th>
                                            <th>用户身份</th>
                                            <th>创建时间</th>
                                            <th>修改时间</th>
                                            <th>备注</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="userInfo" varStatus="status">
                                        <tr>
                                                <td>${status.index+1}</td>
                                            <c:if test="${userInfo.gender==0}">  <td>男</td> </c:if>
                                            <c:if test="${userInfo.gender==1}">  <td>女</td> </c:if>
                                            <td>${userInfo.name}</td>
                                            <td>${userInfo.className}</td>
                                                <td>${userInfo.professionName}</td>
                                                <td>${userInfo.departmentName}</td>
                                                <td>${userInfo.platoonName}</td>
                                                <td>${userInfo.companyName}</td>
                                                <td>${userInfo.battalionName}</td>
                                                <td>${userInfo.mobileNumber}</td>
                                                <td>${userInfo.qq}</td>
                                                <td>${userInfo.wx}</td>
                                            <c:if test="${userInfo.identity==0}">  <td>学生</td> </c:if>
                                            <c:if test="${userInfo.identity==1}">  <td>老师</td> </c:if>
                                            <c:if test="${userInfo.identity==2}">  <td>教官</td> </c:if>
                                            <td><fmt:formatDate value="${userInfo.createDate}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
                                            <td><fmt:formatDate value="${userInfo.updateDate}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
                                                <td>${userInfo.remarks}</td>
                                            <td>
                                                <a href="javascript:;" onclick="openModel('修改用户表','${ctx}/mt/userInfo/update?id=${userInfo.id}')" title="编辑"><span class="am-icon-pencil"></span></a>
                                                <a href="${ctx}/mt/userInfo/${userInfo.id}/delete?pageNo=${page.pageNo}&pageSize=${page.pageSize}" onclick="return confirm('确认要删除该条数据吗？', this.href)" title="删除"><span class="am-text-danger am-icon-trash-o"></span></a></td>
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
