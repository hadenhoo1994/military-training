<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>发表问题 编辑问题 公用</title>
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

<div class="layui-container fly-marginTop">
    <div class="fly-panel" pad20 style="padding-top: 5px;">
        <!--<div class="fly-none">没有权限</div>-->
        <div class="layui-form layui-form-pane">
            <div class="layui-tab layui-tab-brief" lay-filter="user">
                <ul class="layui-tab-title">
                    <li class="layui-this">发表心情<!-- 编辑帖子 --></li>
                </ul>
                <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <div class="layui-row layui-col-space15 layui-form-item">
                            <div class="layui-col-md15">
                                <label for="content" class="layui-form-label">心情</label>
                                <div class="layui-input-block">
                                    <input type="text" id="content" name="content" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                    <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                                </div>
                            </div>
                        </div>
                        <hr>
                        <span id="uploadNum" hidden>1</span>
                        <div class="layui-col-md15" id="showDiv">
                            <img src="/static/assets/img/uploadImg.png" id="imgLog">
                            <img src="" id="imgDiv" hidden style="max-width: 90%;">
                        </div>
                        <button class="layui-btn" onclick="putDiary()">立即发布</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<script src="/static/res/layui/layui.js"></script>
<script src="/static/3rd-lib/jquery/2.2.3/jquery.min.js"></script>

<script>
    layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#imgLog' //绑定元素
            , url: '/file/upload' //上传接口
            , accept: 'images' //允许上传的文件类型
            , exts: "jpg|png|gif|bmp|jpeg"
            , done: function (res) {
                //上传完毕回调
                var url = "/static/upload/" + res.results.fileName;
                var imgDiv = $("#imgDiv");
                $("#imgLog").hide();
                //显示进度条
                var showDiv = $("#showDiv");
                showDiv.append('<div class="layui-progress layui-progress-big" lay-filter="demo" lay-showPercent="true" id="progress">' +
                    '<div class="layui-progress-bar" lay-percent="0%"></div>' +
                    '</div>');
                //进度条动态
                layui.use('element', function () {
                    var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
                    //模拟loading
                    var n = 0, timer = setInterval(function () {
                        n = n + 20;
                        if (n > 100) {
                            n = 100;
                            clearInterval(timer);
                        }
                        element.progress('demo', n + '%');
                    }, 1000);
                });
                setTimeout(function(){showImg(url)},5000);
            }
            , error: function () {
                //请求异常回调
            }
        });
    });

    function showImg(url){
        var imgDiv = $("#imgDiv");
        $("#progress").hide();
        imgDiv.attr("src", url);
        imgDiv.show();
    }
</script>
<script>
    function putDiary() {
        var content = $("#content").val();
        if (content == null || content == "") {
            layer.msg("心情不能为空")
            return;
        }
        var imgDiv = $("#imgDiv").attr("src");
        if (imgDiv == null || imgDiv == "") {
            layer.msg("需上传一张图片")
            return;
        }
        $.ajax({
            type: "POST",
            url: "/flyDiary/putMonent",
            data: {content: content, imgUrl: imgDiv},
            dataType: "json",
            cache: false,
            async: false,
            success: function (result) {
                if (result.code == 200) {
                    //提交成功
                    layer.msg("心情发布成功")
                    window.location.href = "/fly/my/monent";
                } else {
                    layer.msg(result.results)
                }
            }, error: function (data) {
                alert("日记提交失败");
            }
        });
    }

</script>
<script>
    layui.cache.page = 'jie';
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