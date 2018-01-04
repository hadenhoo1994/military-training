<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>基于 layui 的极简社区页面模版</title>
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
                <c:when test="${user.imgUrl==null||user.imgUrl==''}"><img src="/static/assets/img/user.png"></c:when>
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

<div class="fly-panel fly-column">
  <div class="layui-container">
    <ul class="layui-clear">
      <li class="layui-this"><a href="">图志</a></li>
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li> 
      
      <!-- 用户登入后显示 -->
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="../user/index.html">我的日记</a></li>
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="../user/index.html#collection">我的图志</a></li>
    </ul> 

    <div class="fly-column-right layui-hide-xs">
      <a href="/fly/my/addMonent" class="layui-btn">发布图志</a>
      <a href="/fly/my/addDiary" class="layui-btn">提交日记</a>
    </div>
    <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
      <a href="add.html" class="layui-btn">发表新帖</a>
    </div>
  </div>
</div>

<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md15">
      <div class="fly-panel" style="margin-bottom: 0;">

        <ul class="fly-list">          
          <li>
            <a href="user/home.html" class="fly-avatar">
              <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">
            </a>
            <h2>
              <a class="layui-badge">分享</a>
              <a href="detail.html">基于 layui 的极简社区页面模版</a>
            </h2>
            <div class="fly-list-info">
              <a href="user/home.html" link>
                <cite>贤心</cite>
              </a>
              <span>刚刚</span>
              
              <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>
              <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
              <span class="fly-list-nums"> 
                <i class="iconfont icon-pinglun1" title="回答"></i> 66
              </span>
            </div>
            <div class="fly-list-badge">
              <span class="layui-badge layui-bg-black">置顶</span>
              <!--<span class="layui-badge layui-bg-red">精帖</span>-->
            </div>
          </li>
        </ul>
        
        <!-- <div class="fly-none">没有相关数据</div> -->
    
        <div style="text-align: center">
          <div class="laypage-main"><span class="laypage-curr">1</span><a href="/jie/page/2/">2</a><a href="/jie/page/3/">3</a><a href="/jie/page/4/">4</a><a href="/jie/page/5/">5</a><span>…</span><a href="/jie/page/148/" class="laypage-last" title="尾页">尾页</a><a href="/jie/page/2/" class="laypage-next">下一页</a></div>
        </div>

      </div>
    </div>





    </div>
  </div>
</div>


<script src="/static/res/layui/layui.js"></script>
<script>
layui.cache.page = 'jie';
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