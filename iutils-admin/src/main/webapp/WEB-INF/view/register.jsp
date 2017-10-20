<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><c:set var="iutilsName" value='${fnc:getConfig("iutils.name")}'/>${iutilsName} - 用户注册</title>
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
            <div class="tpl-login-title">注册用户</div>
            <span class="tpl-login-content-info">
                  创建一个新的用户
              </span>
            <form class="am-form tpl-form-line-form" action="${ctx}/register" method="post">
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
                <div class="am-form-group">
                    <div class="color-form-group color-form-line color-floating-label">
                        <input class="color-form-control tpl-form-input" type="password" name="rpassword" required>
                        <label class="color-form-control-label">再次输入密码</label>
                    </div>
                </div>
                <div class="am-form-group tpl-login-remember-me">
                    <input id="remember-me" type="checkbox">
                    <label for="remember-me">
                        我已阅读并同意 <a href="javascript:;">《用户注册协议》</a>
                    </label>
                </div>
                <div class="am-form-group">
                    <button type="submit"
                            class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">注 册
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
</script>
</body>
</html>