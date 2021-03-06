<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
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
      <li class="layui-this"><a href="">心情</a></li>
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>

      <!-- 用户登入后显示 -->
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="/fly/my/diary">我的日记</a></li>
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="/fly/my/monent">我的心情</a></li>
    </ul>

    <div class="fly-column-right layui-hide-xs">
      <a href="/fly/my/addMonent" class="layui-btn">发布心情</a>
      <a href="/fly/my/addDiary" class="layui-btn">提交日记</a>
    </div>
    <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
      <a href="add.html" class="layui-btn">发表新帖</a>
    </div>
  </div>
</div>

<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
      <div class="fly-panel" style="margin-bottom: 0;">

        <ul class="fly-list">
          <c:forEach items="${list}" var="monent">
          <li>
            <a href="#" class="fly-avatar" disabled="">
              <c:choose>
                <c:when test="${monent.userInfo.imgUrl==null||monent.userInfo.imgUrl==''}"><img src="/static/assets/img/user.png"></c:when>
                <c:otherwise>
                  <img src="${monent.userInfo.imgUrl}">
                </c:otherwise>
              </c:choose>
            </a>
            <h2>
              <a class="layui-badge">心情</a>
              <a href="#" disabled="">${monent.content}</a>
            </h2>
            <div class="fly-list-info">
              <a href="user/home.html" link>
                <cite>${monent.userInfo.name}</cite>
              </a>
              <span>发表于：<fmt:formatDate value="${monent.createDate}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></span>
              <span class="fly-list-nums" id="likes" onclick="addNum(this)" data-type="0">
                <i class="layui-icon" title="点赞"  style="font-size: 20px;color: #6c6c6c;">&#xe6c6;</i>
                  <span style="font-size: 20px;" data-id="${monent.id}" >${monent.likeNum}</span>
              </span>
            </div>
          </li>
            <img src="${monent.imgUrl}" style="padding-left: 15px;max-width: 90%;">
            <hr>
          </c:forEach>
        </ul>
        <div class="layui-col-space15">
          <div class="layui-col-md5"></div>

            <div id="pages"></div>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="/static/3rd-lib/jquery/2.2.3/jquery.min.js"></script>
<script src="/static/res/layui/layui.js"></script>
<script>
    var pageNo = ${page.pageNo};    //当前页面
    var pageSize = ${page.pageSize};  //页面显示 条数
    var total = ${page.total};      //总数据条数
    layui.use('laypage', function(){
        var laypage = layui.laypage;
        //执行一个laypage实例
        laypage.render({
            elem: 'pages' //注意，这里的 test1 是 ID，不用加 # 号
            ,count:total
            ,limit:pageSize
            ,curr:pageNo
            ,jump: function(obj, first){
                //obj包含了当前分页的所有参数，比如
                //首次不执行
                if(!first){
                    var pageNo = obj.curr;
                    //跳转链接
                    window.location.href="/fly?pageSize="+pageSize+"&pageNo="+pageNo;
                }
            }
        });
    });
</script>
<script>
    function addNum(likeBtn){
        var sp=likeBtn;
        var flag = $(sp).attr("data-type");
        if(flag>0){
            return;
        }
        var likeEle = sp.childNodes[3];//span 标签
        var id = $(likeEle).attr("data-id");
        var likeNum = parseInt($(likeEle).text());
        //点赞后修改点赞按钮颜色 并+1
        //调用接口 添加赞数
        $.ajax({
                type: "POST",
                url: "/rest/monentLike",
                data: {id: id},
                dataType: "json",
                cache: false,
                async: false,
                success: function (result) {
                    if (result.code == 200){
                        //点赞成功
                        //修改点赞按钮颜色
                        var likeLog = sp.childNodes[1];//i标签
                        $(likeLog).css("color","#b60008");
                        //点赞字数+1
                        $(likeEle).text(likeNum+1);
                        //禁用此按钮
                        $(sp).attr("data-type",1);
                    }else{
                        layer.msg(result.msg)
                    }
                }
                , error: function (data) {
                alert(JSON.stringify(data));
                }
            });
    }

</script>
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

<script type="text/javascript">
    var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='"
        + cnzz_protocol
        + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));
</script>

</body>
</html>