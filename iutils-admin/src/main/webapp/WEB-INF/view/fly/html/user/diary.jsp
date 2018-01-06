<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户中心</title>
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
      <c:choose>
        <c:when test="${user==null}">
          <!-- 未登入的状态 -->
          <li class="layui-nav-item">
            <a class="iconfont icon-touxiang layui-hide-xs" href="user/login.html"></a>
          </li>
          <li class="layui-nav-item">
            <a href="/fly/login">登入</a>
          </li>
          <li class="layui-nav-item">
            <a href="/fly/reg">注册</a>
          </li>
        </c:when>
        <c:otherwise>
          <!-- 登入后的状态 -->
          <li class="layui-nav-item">
            <a class="fly-nav-avatar" href="javascript:;">
              <cite class="layui-hide-xs">${user.name}</cite>
              <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>
              <c:choose>
                <c:when test="${user.imgUrl==null||user.imgUrl==''}"><img
                        src="/static/assets/img/user.png"></c:when>
                <c:otherwise>
                  <img src="${user.imgUrl}">
                </c:otherwise>
              </c:choose>
            </a>
            <dl class="layui-nav-child">
              <hr style="margin: 5px 0;">
              <dd><a href="/fly/logout" style="text-align: center;">退出</a></dd>
            </dl>
          </li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
</div>

<div class="layui-container fly-marginTop fly-user-main">
  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
    <li class="layui-nav-item layui-this">
      <a href="/fly/my/diary">
        <i class="layui-icon">&#xe63c;</i>
        我的日记
      </a>
    </li>
    <li class="layui-nav-item ">
      <a href="/fly/my/monent">
        <i class="layui-icon">&#xe634;</i>
        我的图志
      </a>
    </li>
  </ul>

  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>

  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>


  <div class="fly-panel fly-panel-user" pad20>
    <!--
    <div class="fly-msg" style="margin-top: 15px;">
      您的邮箱尚未验证，这比较影响您的帐号安全，<a href="activate.html">立即去激活？</a>
    </div>
    -->
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li data-type="mine-jie" class="layui-this">我的日记（<span>${diaryList.size()}</span>）</li>
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <ul class="mine-view jie-row">
            <c:forEach var="diary" items="${diaryList}">
              <li>
                <a class="jie-title" href="/flyDiary/diarys?id=${diary.id}" target="_blank">${diary.title}</a>
                <i>创建于：<fmt:formatDate value="${diary.createDate}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></i>
              </li>
            </c:forEach>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>


<script src="/static/res/layui/layui.js"></script>
<script>
    layui.cache.page = 'user';
    layui.cache.user = {
        username: '游客'
        , uid: -1
        , avatar: '/static/res/images/avatar/00.jpg'
        , experience: 83
        , sex: '男'
    };
    layui.config({
        version: "3.0.0"
        , base: '/static/res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
</script>

</body>
</html>