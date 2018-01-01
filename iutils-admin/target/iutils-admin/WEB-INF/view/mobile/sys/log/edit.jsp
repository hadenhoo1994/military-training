<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>日志记录</title>
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
                            <div class="widget-title am-fl">日志详情</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" modelAttribute="log" method="post">
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">客户端信息：</label>
                                    <div class="am-u-sm-12">
                                        <textarea name="params" rows="5">${log.userAgent}</textarea>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">提交的参数：</label>
                                    <div class="am-u-sm-12">
                                        <textarea name="params" rows="5">${log.params}</textarea>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">异常信息：</label>
                                    <div class="am-u-sm-12">
                                        <textarea name="exception" rows="5">${log.exception}</textarea>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-12">
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
</body>
</html>
