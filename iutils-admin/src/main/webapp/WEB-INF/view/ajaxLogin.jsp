<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><c:set var="iutilsName" value='${fnc:getConfig("iutils.name")}'/>${iutilsName} - 用户登录1</title>
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
            <form class="am-form tpl-form-line-form" id="loginForm" method="post">
                <div class="am-form-group">
                    <div class="color-form-group color-form-line color-floating-label">
                        <input class="color-form-control tpl-form-input" type="text" name="username" id="username"
                               required>
                        <label class="color-form-control-label">用户名/邮箱/手机号码</label>
                    </div>
                </div>
                <div class="am-form-group">
                    <div class="color-form-group color-form-line color-floating-label">
                        <input class="color-form-control tpl-form-input" type="password" name="password" id="password"
                               required>
                        <label class="color-form-control-label">密码</label>
                    </div>
                </div>
                <div class="am-form-group">
                    <div class="am-g">
                        <div class="am-u-sm-6" style="padding-left: 1.5rem;padding-right: 1.5rem;">
                            <div class="color-form-group color-form-line color-floating-label">
                                <input class="color-form-control tpl-form-input" type="text" id="jcaptchaCode"
                                       name="jcaptchaCode" required>
                                <label class="color-form-control-label">验证码</label>
                            </div>
                        </div>
                        <div class="am-u-sm-6">
                            <div class="jcaptcha-box" style="margin-top: 20px;">
                                <img class="jcaptcha-btn jcaptcha-img"
                                     src="${pageContext.request.contextPath}/jcaptcha.code" title="点击更换验证码">
                                <a class="jcaptcha-btn" href="javascript:;">换一张</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="am-form-group">
                    <button type="button" id="login"
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
        $("#login").click(function () {
            var username = $("#username").val();
            if (username == "") {
                showMsg("用户名不能为空");
                return;
            }
            var password = $("#password").val();
            if (password == "") {
                showMsg("密码不能为空");
                return;
            }
            var jcaptchaCode = $("#jcaptchaCode").val();
            if (jcaptchaCode == "") {
                showMsg("验证码不能为空");
                return;
            }
            post("${ctx}/login", {username: username, password: password, jcaptchaCode: jcaptchaCode}, function (data) {
                console.log(data);
            });
        });
    });
    var rest = '${rest}';
</script>
</body>
</html>