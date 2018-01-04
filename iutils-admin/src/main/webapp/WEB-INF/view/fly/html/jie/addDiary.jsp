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
                    <li class="layui-this">发表日记<!-- 编辑帖子 --></li>
                </ul>
                <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <div class="layui-row layui-col-space15 layui-form-item">
                            <div class="layui-col-md15">
                                <label for="title" class="layui-form-label">标题</label>
                                <div class="layui-input-block">
                                    <input type="text" id="title" name="title" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                    <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                                </div>
                            </div>
                        </div>
                        <div class="layui-row layui-col-md15 layui-form-item">
                                <textarea name="content" id="content" required lay-verify="required" placeholder="请输入日记内容"
                                          class="layui-textarea"></textarea>
                        </div>
                        <div class="layui-col-md15">
                            <button type="button" class="layui-btn" id="upload"><i class="layui-icon">&#xe67c;</i>上传图片(最多三张)
                            </button>
                        </div>
                        <hr>
                        <span id="uploadNum" hidden>1</span>
                        <div class="layui-col-md15" id="imgDiv"></div>
                        <div class="layui-form-item">
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
            elem: '#upload' //绑定元素
            , url: '/file/upload' //上传接口
            , accept: 'images' //允许上传的文件类型
            , exts: "jpg|png|gif|bmp|jpeg"
            , done: function (res) {
                //上传完毕回调
                var url = res.results.fileName;
                bdImg(url);
            }
            , error: function () {
                //请求异常回调
            }
        });
    });

    //展示图片
    function bdImg(url) {
        //上传之前判断是否已经上传了三张图了
        var uploadNum = parseInt($("#uploadNum").text());
        if (uploadNum > 3) {
            layer.msg("你已经上传了三张图片了")
            return;
        }
        var imgDiv = $("#imgDiv");
        var str = '<div class="layui-col-md15"><img src="/static/upload/img/' + url + '"id="imgs' + uploadNum + '"></div>'
        imgDiv.append(str);
        $("#uploadNum").text(uploadNum + 1);
    }
</script>
<script>
    function putDiary() {
        //提交日记
        var title = $("#title").val();
        if (title == null || title == "") {
            layer.msg("标题不能为空")
            return;
        }
        var content = $("#content").val();
        if (content == null || content == "") {
            layer.msg("内容不能为空")
            return;
        }
        var imgs1 = $("#imgs1").attr("src");
        var imgs2 = $("#imgs2").attr("src");;
        var imgs3 = $("#imgs3").attr("src");;
        $.ajax({
            type: "POST",
            url: "/flyDiary/putDiary",
            data: {title: title, content: content, img1: imgs1, img2: imgs2, img3: imgs3},
            dataType: "json",
            cache: false,
            async: false,
            success: function (result) {
                if (result.code == 200) {
                    //提交成功
                    layer.msg("提交成功")
                    window.location.href = "/fly/my/diary";
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