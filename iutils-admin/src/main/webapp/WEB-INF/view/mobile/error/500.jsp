<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>404</title>
    <%@ include file="../include/head.jsp" %>
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
            <div class="widget am-cf">
                <div class="widget-body">
                    <div class="tpl-page-state">
                        <div class="tpl-page-state-title am-text-center tpl-error-title">500</div>
                        <div class="tpl-error-title-info">Internal Server Error</div>
                        <div class="tpl-page-state-content tpl-error-content">
                            <p>内部服务器错误，请联系管理员</p>
                            <button type="button" class="am-btn am-btn-secondary am-radius tpl-error-btn"
                                    onclick="top.location='${ctx}'">返回主页
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../include/bottom.jsp" %>
</body>
</html>