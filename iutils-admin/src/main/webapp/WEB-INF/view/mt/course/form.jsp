<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>排课表</title>
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
                            <div class="widget-title am-fl">排课表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="course" action="${ctx}/mt/course/<c:choose><c:when test="${empty course.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>" method="post">
                            <input type="hidden" name="id" value="${course.id}"/>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">排id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="platoonId" placeholder="排id" value="${course.platoonId}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">排名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="platoonName" placeholder="排名称" value="${course.platoonName}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">课程id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="projectId" placeholder="课程id" value="${course.projectId}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">课程名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="projectName" placeholder="课程名称" value="${course.projectName}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">周次：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="week" placeholder="周次" value="${course.week}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">节次：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="classes" placeholder="节次" value="${course.classes}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">备注：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="remarks" placeholder="备注" value="${course.remarks}" required/>
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
