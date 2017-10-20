<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>班级表</title>
    <%@ include file="../../include/head.jsp"%>
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
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="classes" action="${ctx}/mt/classes/<c:choose><c:when test="${empty classes.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>" method="post">
                            <input type="hidden" name="id" value="${classes.id}"/>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">班级名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="name" placeholder="班级名称" value="${classes.name}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">班级所属专业id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="professionId" placeholder="班级所属专业id" value="${classes.professionId}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">班级所属专业名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="professionName" placeholder="班级所属专业名称" value="${classes.professionName}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">班级所属系id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="departmentId" placeholder="班级所属系id" value="${classes.departmentId}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">班级所属系名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="departmentName" placeholder="班级所属系名称" value="${classes.departmentName}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">第几届id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="gradeId" placeholder="第几届id" value="${classes.gradeId}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">第几届：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="gradeName" placeholder="第几届" value="${classes.gradeName}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">班级学生数：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="studentNum" placeholder="班级学生数" value="${classes.studentNum}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">班级班主任id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="directorId" placeholder="班级班主任id" value="${classes.directorId}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">班级班主任名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="directorName" placeholder="班级班主任名称" value="${classes.directorName}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">备注：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="remarks" placeholder="备注" value="${classes.remarks}" required/>
                                        </div>
                                    </div>
                            <div class="am-form-group">
                                <div class="am-u-sm-9 am-u-sm-push-3">
                                    <button type="submit" class="am-btn am-btn-primary">保存</button>
                                    <button type="button" class="am-btn am-btn-danger" onclick="closeModel(false)">关闭</button>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../include/bottom.jsp"%>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if(msg!=''){
            showMsg(msg);
            closeModel(true);//关闭窗口
        }
    });
</script>
</body>
</html>
