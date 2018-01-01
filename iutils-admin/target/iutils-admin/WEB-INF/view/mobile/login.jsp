<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><c:set var="iutilsName" value='${fnc:getConfig("iutils.name")}'/>${iutilsName} - 用户登录</title>
    <%@ include file="include/head.jsp" %>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 风格切换 -->
    <div class="tpl-skiner">
        <div class="tpl-skiner-toggle am-icon-cog">
        </div>
        <div class="tpl-skiner-content">
            <div class="tpl-skiner-content-title">
                选择主题
            </div>
            <div class="tpl-skiner-content-bar">
                <span class="skiner-color skiner-white" data-color="theme-white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black"></span>
            </div>
        </div>
    </div>
    <div class="tpl-login">
        <div class="tpl-login-content">
            <div class="tpl-login-logo">
            </div>
            <form class="am-form tpl-form-line-form" action="${ctx}/login" method="post">
                <div class="am-form-group">
                    <div class="color-form-group color-form-line color-floating-label">
                        <input class="color-form-control tpl-form-input" type="text" name="username" required>
                        <label class="color-form-control-label">请输入账号</label>
                    </div>
                </div>
                <div class="am-form-group">
                    <div class="color-form-group color-form-line color-floating-label">
                        <input class="color-form-control tpl-form-input" type="password" name="password" required>
                        <label class="color-form-control-label">请输入密码</label>
                    </div>
                </div>
                <div class="am-form-group tpl-login-remember-me">
                    <input id="remember-me" name="rememberMe" type="checkbox">
                    <label for="remember-me">
                        记住密码
                    </label>
                </div>
                <div class="am-form-group">
                    <button type="submit"
                            class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">登 录
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="include/bottom.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
        }
    });
    var rest = '${rest}';
    // 如果在框架或在对话框中，则弹出提示并跳转到首页
    if (top.location !== self.location) {
        alert("未登录或登录超时。请重新登录，谢谢！");
        top.location = "${ctx}";
    }
</script>
</body>
</html>