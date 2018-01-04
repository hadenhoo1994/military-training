<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>登入</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="/static/res/layui/css/layui.css">
  <link rel="stylesheet" href="/static/res/css/global.css">
</head>
<body>

<div class="fly-header layui-bg-black">
  <div class="layui-container">
    <a class="fly-logo" href="/fly">
      <img src="/static/res/images/logo.png" alt="layui">
    </a>
    
    <ul class="layui-nav fly-nav-user">
      <!-- 未登入的状态 -->
      <li class="layui-nav-item">
        <a class="iconfont icon-touxiang layui-hide-xs" href="user/login.html"></a>
      </li>
      <li class="layui-nav-item">
        <a href="">登入</a>
      </li>
      <li class="layui-nav-item">
        <a href="/fly/reg">注册</a>
      </li>
    </ul>
  </div>
</div>

<div class="layui-container fly-marginTop">
  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title">
        <li class="layui-this">登入</li>
        <li><a href="reg.html">注册</a></li>
      </ul>
      <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <div class="layui-form layui-form-pane">
            <%--<form method="post" action="/fly/users/login">--%>
              <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">手机号码</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_email" name="mobileNumber" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="password" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <button class="layui-btn" lay-filter="*" onclick="login()">立即登录</button>
                <%--<span style="padding-left:20px;">--%>
                  <%--<a href="forget.html">忘记密码？</a>--%>
                <%--</span>--%>
              </div>

            <%--</form>--%>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="/static/3rd-lib/jquery/2.2.3/jquery.min.js"></script>
<script src="/static/res/layui/layui.js"></script>
<script>
  function login(){
      var account = $("#L_email").val()
      var password = $("#L_pass").val();
      $.ajax({
          type: "POST",
          url: "/fly/users/login",
          data: {mobileNumber: account, password:password},
          dataType: "json",
          cache: false,
          async: false,
          success: function (result) {
              if (result.code == 200){
                  //登录成功
                  window.location.href="/fly";
              }else{
                  layer.msg(result.results)
              }
          }, error: function (data) {
              alert(JSON.stringify(data));
          }
      });

  }
</script>



<script>
layui.cache.page = 'user';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '/static/res/images/avatar/00.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "3.0.0"
  ,base: '/static/res/mods/'
}).extend({
  fly: 'index'
}).use('fly');
</script>

</body>
</html>