<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>消息详细</title>
    <%@ include file="../../include/head.jsp" %>
    <style>
        .tpl-content-wrapper {
            margin-left: 0
        }

        .blog-main {
            padding: 0 50px;
        }

        .blog-title, .blog-meta, .blog-content, .blog-footer {
            text-align: center;
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
                            <div class="widget-title am-fl">阅读消息</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="blog-main">
                                <h3 class="am-article-title blog-title">
                                    <a href="#">${msgReceive.msgSend.title}</a>
                                </h3>
                                <h4 class="am-article-meta blog-meta">发件人：${msgReceive.createUser.name}
                                    收件人：${msgReceive.updateUser.name} 时间：<fmt:formatDate
                                            value="${msgReceive.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></h4>
                                <div class="am-g blog-content">
                                    ${msgReceive.msgSend.content}
                                </div>
                            </div>
                            <div class="blog-footer">
                                <button type="button" class="am-btn am-btn-danger" onclick="closeModel(false)">关闭
                                </button>
                            </div>
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
