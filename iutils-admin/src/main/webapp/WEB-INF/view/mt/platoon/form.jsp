<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>军排表</title>
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
                            <div class="widget-title am-fl">军排表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="platoon" action="${ctx}/mt/platoon/<c:choose><c:when test="${empty platoon.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>" method="post">
                            <input type="hidden" name="id" value="${platoon.id}"/>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">排名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="name" placeholder="排名称" value="${platoon.name}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">排全称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="fullName" placeholder="排全称" value="${platoon.fullName}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">连id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="companyId" placeholder="连id" value="${platoon.companyId}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">连名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="companyName" placeholder="连名称" value="${platoon.companyName}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">营id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="battalionId" placeholder="营id" value="${platoon.battalionId}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">营名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="battalionName" placeholder="营名称" value="${platoon.battalionName}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">学生数：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="studentNum" placeholder="学生数" value="${platoon.studentNum}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">备注：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="remarks" placeholder="备注" value="${platoon.remarks}" required/>
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
