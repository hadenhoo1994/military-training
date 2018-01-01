<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>任务调度</title>
    <%@ include file="../../include/head.jsp" %>
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
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="scheduleJob"
                                  action="${ctx}/scheduleJob/<c:choose><c:when test="${empty scheduleJob.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>"
                                  method="post">
                                <input type="hidden" name="id" value="${scheduleJob.id}"/>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">任务名称：</label>
                                    <div class="am-u-sm-12">
                                        <input type="text" name="jobName" placeholder="任务名称"
                                               value="${scheduleJob.jobName}" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">任务分组：</label>
                                    <div class="am-u-sm-12">
                                        <input type="text" name="jobGroup" placeholder="任务分组"
                                               value="${scheduleJob.jobGroup}" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">cron表达式：</label>
                                    <div class="am-u-sm-12">
                                        <input type="text" name="cron" placeholder="cron表达式"
                                               value="${scheduleJob.cron}" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">任务执行方法：</label>
                                    <div class="am-u-sm-12">
                                        <input type="text" name="beanClass" placeholder="包名+类名"
                                               value="${scheduleJob.beanClass}" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">是否同步：</label>
                                    <div class="am-u-sm-12">
                                        <select name="isConcurrent" required data="${scheduleJob.isConcurrent}">
                                            <option value="1">是</option>
                                            <option value="0">否</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">任务调用的方法名：</label>
                                    <div class="am-u-sm-12">
                                        <input type="text" name="methodName" placeholder="任务调用的方法名"
                                               value="${scheduleJob.methodName}" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">描述：</label>
                                    <div class="am-u-sm-12">
                                        <textarea name="remarks">${scheduleJob.remarks}</textarea>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-12">
                                        <button type="submit" class="am-btn am-btn-primary">保存</button>
                                        <button type="button" class="am-btn am-btn-danger" onclick="closeModel(false)">
                                            关闭
                                        </button>
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
<%@ include file="../../include/bottom.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        //消息提醒
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
            closeModel(true);//关闭窗口
        }
        initSelectValue(true);//初始化下拉框的值
    });
</script>
</body>
</html>
