<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<%
    response.setStatus(HttpServletResponse.SC_OK);
    String path = request.getContextPath();
%>
<html lang="zh">
<head>
    <title>系统提示 - 500</title>
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
                        <div class="am-g" style="padding: 0px 30px;">
                            <div>系统执行发生错误，信息描述如下：</div>
                            <div>错误状态代码是：${pageContext.errorData.statusCode}</div>
                            <div>错误发生页面是：${pageContext.errorData.requestURI}</div>
                            <div>错误信息：${pageContext.exception}</div>
                            <div>
                                错误堆栈信息：<br/>
                                <c:forEach var="trace"
                                           items="${pageContext.exception.stackTrace}">
                                    <div>${trace}</div>
                                </c:forEach>
                            </div>
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