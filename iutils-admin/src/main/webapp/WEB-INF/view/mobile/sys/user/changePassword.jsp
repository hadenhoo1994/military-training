<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>用户编辑</title>
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
                            <div class="widget-title am-fl">修改密码</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator
                                  action="${ctx}/user/${id}/changePassword" method="post">
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">原密码：</label>
                                    <div class="am-u-sm-12">
                                        <input type="password" name="password" placeholder="原密码" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">新密码：</label>
                                    <div class="am-u-sm-12">
                                        <input type="password" name="newPassword" placeholder="新密码" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-12">
                                        <button type="submit" class="am-btn am-btn-primary">修改密码</button>
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
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
            if (msg == "修改密码成功") {
                closeModel(true);//关闭窗口
            }
        }
    });
</script>
</body>
</html>