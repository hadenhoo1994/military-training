<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><c:set var="iutilsName" value='${fnc:getConfig("iutils.name")}'/>${iutilsName} - 后台管理</title>
    <%@ include file="include/head.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/custom/css/amazeui.select.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，平台暂不支持。 请 <a href="http://browsehappy.com/"
                                                                 target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <a href="${ctx}"><img src="${ctxStatic}/assets/img/logo.png" alt=""></a>
        </div>
        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">
            <!-- 侧边切换 -->
            <div class="am-fl tpl-header-switch-button am-icon-list">
                    <span>
                </span>
            </div>
            <!-- 搜索 -->
            <div class="am-fl tpl-header-search">
                <form class="tpl-header-search-form" action="javascript:;">
                    <button class="tpl-header-search-btn am-icon-search"></button>
                    <input class="tpl-header-search-box am-select-ui-input" type="text" placeholder="功能检索...">
                    <ul class="am-select-ui" style="width: 240px;margin-top: 5px;margin-left: 15px;"></ul>
                </form>
            </div>
            <!-- 其它功能-->
            <div class="am-fr tpl-header-navbar">
                <ul>
                    <!-- 欢迎语 -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="#${ctx}/user/userInfo" onclick="link('${ctx}/user/userInfo')">欢迎你,
                            <span>${empty loginUser.name?loginUser.username:loginUser.name}</span> </a>
                    </li>
                    <!-- 新邮件 -->
                    <li class="am-dropdown tpl-dropdown" data-am-dropdown id="mail">
                        <a href="javascript:;" class="am-dropdown-toggle tpl-dropdown-toggle" data-am-dropdown-toggle>
                            <i class="am-icon-envelope"></i>
                            <span class="am-badge am-badge-success am-round item-feed-badge" id="mailBadge"
                                  style="display: none;">0</span>
                        </a>
                        <!-- 弹出列表 -->
                        <ul class="am-dropdown-content tpl-dropdown-content" id="mailHtml">
                            <li class="tpl-dropdown-menu-messages">
                                <a href="#${ctx}/sys/msgReceive?msgSend.type=mail&status=-1"
                                   onclick="link('${ctx}/sys/msgReceive?msgSend.type=mail&status=-1');$('#mail').dropdown('close');"
                                   class="tpl-dropdown-menu-messages-item am-cf">
                                    <i class="am-icon-circle-o"></i> 进入列表…
                                </a>
                            </li>
                        </ul>
                    </li>

                    <!-- 新提示 -->
                    <li class="am-dropdown" data-am-dropdown id="notice">
                        <a href="javascript:;" class="am-dropdown-toggle" data-am-dropdown-toggle>
                            <i class="am-icon-bell"></i>
                            <span class="am-badge am-badge-warning am-round item-feed-badge" id="noticeBadge"
                                  style="display: none;">0</span>
                        </a>
                        <!-- 弹出列表 -->
                        <ul class="am-dropdown-content tpl-dropdown-content" id="noticeHtml">
                            <li class="tpl-dropdown-menu-notifications">
                                <a href="#${ctx}/sys/msgReceive?msgSend.type=notice&status=-1"
                                   onclick="link('${ctx}/sys/msgReceive?msgSend.type=notice&status=-1');$('#notice').dropdown('close');"
                                   class="tpl-dropdown-menu-notifications-item am-cf">
                                    <i class="am-icon-bell"></i> 进入列表…
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!-- 退出 -->
                    <li class="am-text-sm">
                        <a href="${ctx}/logout" onclick="return confirm('确认要退出吗？', this.href)">
                            <span class="am-icon-sign-out"></span> 退出
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </header>

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

    <!-- 侧边导航栏 -->
    <div class="left-sidebar">
        <!-- 用户信息 -->
        <div class="tpl-sidebar-user-panel">
            <div class="tpl-user-panel-slide-toggleable">
                <div class="tpl-user-panel-profile-picture">
                    <img src="${pageContext.request.contextPath}/${loginUser.photo}<c:if test="${empty loginUser.photo}">static/assets/img/user.png</c:if>"
                         alt="用户头像">
                </div>
                <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
              ${empty loginUser.name?loginUser.username:loginUser.name}
          </span>
                <a href="#${ctx}/user/userInfo" onclick="link('${ctx}/user/userInfo')"
                   class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 用户设置</a>
            </div>
        </div>

        <!-- 菜单 -->
        <ul class="sidebar-nav">
            <li class="sidebar-nav-heading">功能菜单</li>
            <c:forEach items="${menus}" var="menu1">
                <c:if test="${menu1.parentId==1}">
                    <li class="sidebar-nav-link">
                        <!-- 判断是否有下级 -->
                        <c:set var="isSub" value="0"></c:set>
                        <c:forEach items="${menus}" var="menu2">
                            <c:if test="${menu1.id==menu2.parentId}"><c:set var="isSub" value="1"></c:set></c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${isSub==1}">
                                <a href="javascript:;" class="sidebar-nav-sub-title menu-link-clear">
                                    <img src="${ctxStatic}${m.icon}<c:if test="${empty m.icon}">/assets/i/menu.png</c:if>"
                                         style="width:16px;height:16px;margin-right:8px;"/> ${menu1.name}
                                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
                                </a>
                                <ul class="sidebar-nav sidebar-nav-sub">
                                    <c:forEach items="${menus}" var="menu2">
                                        <c:if test="${menu1.id==menu2.parentId}">
                                            <li class="sidebar-nav-link">
                                                <a href="#${ctx}/${menu2.url}" class="menu-link menu-link-clear"
                                                   level="2">
                                                    <span class="am-icon-angle-right sidebar-nav-link-logo"></span> ${menu2.name}
                                                </a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <a href="#${ctx}/${menu1.url}" class="menu-link menu-link-clear" level="1">
                                    <img src="${ctxStatic}${m.icon}<c:if test="${empty m.icon}">/assets/i/menu.png</c:if>"
                                         style="width:16px;height:16px;margin-right:8px;"/> ${menu1.name}
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:if>
            </c:forEach>

        </ul>
    </div>

    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <iframe name="main-content" id="main-content" src="${ctx}/welcome" style="width:100%;" scrolling="no"
                frameborder="no"></iframe>
    </div>
</div>
</div>
<%@ include file="include/bottom.jsp" %>
<script src="${ctxStatic}/3rd-lib/tpl/tpl.js"></script>
<!-- 定义模板 -->
<script type="text/template" id="mailTpl" desc="邮件列表">
    <# for(var i=0; i
    <list.length; i++) {var item=list[i];#>
    <li class="tpl-dropdown-menu-messages">
        <a href="javascript:;"
           onclick="document.getElementById('main-content').contentWindow.openModel(false,'${ctx}/sys/msgReceive/update?id=<#=item.id #>');$('#mail').dropdown('close');"
           c class="tpl-dropdown-menu-messages-item am-cf">
            <div class="menu-messages-ico">
                <img src="${pageContext.request.contextPath}/<#=item.createUser.photo #><# if(item.createUser.photo==null || item.createUser.photo==''){#>static/assets/img/user.png<#}#>">
            </div>
            <div class="menu-messages-content">
                <div class="menu-messages-content-title">
                    <i class="am-icon-circle-o am-text-success"></i>
                    <span><#=item.createUser.name #></span>
                </div>
                <div class="am-text-truncate">
                    <#=item.msgSend.title #>
                </div>
                <div class="menu-messages-content-time">
                    <#=new Date(item.updateDate).format('yyyy-MM-dd h:m:s') #>
                </div>
            </div>
        </a>
    </li>
    <#}#>
        <li class="tpl-dropdown-menu-messages">
            <a href="#${ctx}/sys/msgReceive?msgSend.type=mail&status=-1"
               onclick="link('${ctx}/sys/msgReceive?msgSend.type=mail&status=-1');$('#mail').dropdown('close');"
               class="tpl-dropdown-menu-messages-item am-cf">
                <i class="am-icon-circle-o"></i> 进入列表…
            </a>
        </li>
</script>
<script type="text/template" id="noticeTpl" desc="通知列表">
    <# for(var i=0; i
    <list.length; i++) {var item=list[i];#>
    <li class="tpl-dropdown-menu-notifications">
        <a href="javascript:;"
           onclick="document.getElementById('main-content').contentWindow.openModel(false,'${ctx}/sys/msgReceive/update?id=<#=item.id #>');$('#notice').dropdown('close');"
           class="tpl-dropdown-menu-notifications-item am-cf">
            <div class="tpl-dropdown-menu-notifications-title">
                <i class="am-icon-circle-o am-text-warning"></i>
                <span><#=item.msgSend.title #></span>
            </div>
            <div class="tpl-dropdown-menu-notifications-time">
                <#=new Date(item.updateDate).format('h:m') #>
            </div>
        </a>
    </li>
    <#}#>
        <li class="tpl-dropdown-menu-notifications">
            <a href="#${ctx}/sys/msgReceive?msgSend.type=notice&status=-1"
               onclick="link('${ctx}/sys/msgReceive?msgSend.type=notice&status=-1');$('#notice').dropdown('close');"
               class="tpl-dropdown-menu-notifications-item am-cf">
                <i class="am-icon-bell"></i> 进入列表…
            </a>
        </li>
</script>
<script>
    $(document).ready(function () {
        //初始化地址
        var initPage = function () {
            var link = location.hash;
            if (link) {
                var $menu = $("a[href='" + link + "']");
                var level = $menu.attr("level");
                if (level == '1') {
                    $menu.addClass("active");
                } else if (level == '2') {
                    $menu.addClass("sub-active");
                    $menu.parent().parent().prev().addClass("active");
                }
                $("#main-content").attr("src", link.substr(1));
            }
        }
        initPage();
        //点击左侧菜单
        $(".sidebar-nav").on('click', '.menu-link', function () {
            //清除菜单选中状态
            $(".menu-link-clear").removeClass("active").removeClass("sub-active");
            var $menu = $(this);
            var level = $menu.attr("level");
            if (level == '1') {
                $menu.addClass("active");
            } else if (level == '2') {
                $menu.addClass("sub-active");
                $menu.parent().parent().prev().addClass("active");
            }
            $("#main-content").attr("src", $menu.attr("href").substr(1));
        });

        //所有的功能菜单
        var menus = [
            <c:forEach items="${menus}" var="menu" varStatus="status">
                <c:if test="${not empty menu.url}">{
                name: "${menu.name}",
                url: "${ctx}/${menu.url}"
            }<c:if test="${!status.last}">, </c:if></c:if>
            </c:forEach>
        ];

        //功能搜索事件绑定
        $(document).delegate(".am-select-ui-input", "blur focus keyup", function (event) {
            var eType = event.type;
            switch (eType) {
                case 'focusin'://获取焦点
                    var currentProposals = [];
                    var proposalList = $(this).parent().find(".am-select-ui");
                    var word = $(this).val();
                    proposalList.empty();
                    var items = menus;
                    for (var test in items) {
                        if (items[test].name.match(word)) {
                            currentProposals.push(items[test].name);
                            var element = $('<li></li>')
                                .html('<a href="#' + items[test].url + '" onclick="link(\'' + items[test].url + '\')">' + items[test].name + '</a>');
                            proposalList.append(element);
                        }
                    }
                    $(this).parent().find(".am-select-ui").show();
                    break;
                case 'focusout'://失去焦点
                    var $this = $(this);
                    setTimeout(function () {
                        $this.parent().find(".am-select-ui").hide(100);
                    }, 100);
                    break;
                case 'keyup'://键盘按下
                    var currentProposals = [];
                    var v = event.which;
                    if (v == 38 || v == 40 || v == 13) {
                        return;
                    }
                    var word = $(this).val();
                    var items = menus;
                    var proposalList = $(this).parent().find(".am-select-ui");
                    proposalList.empty();
                    for (var test in items) {
                        if (items[test].name.match(word)) {
                            currentProposals.push(items[test].name);
                            var element = $('<li></li>')
                                .html('<a href="#' + items[test].url + '" onclick="link(\'' + items[test].url + '\')">' + items[test].name + '</a>');
                            proposalList.append(element);
                        }
                    }
                    break;
            }
        });

        //邮件通知数据
        get("${ctx}/sys/msg/getMailPage?msgSend.type=mail&status=0&pageSize=2", function (data) {
            if (data.ret == 1) {
                if (data.data.list) {
                    $("#mailHtml").html(tpl('#mailTpl', data.data));
                }
                var $mailBadge = $("#mailBadge");
                if (data.data.total > 0) {
                    $mailBadge.html(data.data.total);
                    $mailBadge.show();
                } else {
                    $mailBadge.hide();
                }
            }
        });
        get("${ctx}/sys/msg/getNoticePage?msgSend.type=notice&status=0&pageSize=3", function (data) {
            if (data.ret == 1) {
                if (data.data.list) {
                    $("#noticeHtml").html(tpl('#noticeTpl', data.data));
                }
                var $noticeBadge = $("#noticeBadge");
                if (data.data.total > 0) {
                    $noticeBadge.html(data.data.total);
                    $noticeBadge.show();
                } else {
                    $noticeBadge.hide();
                }
            }
        });
    });

    //跳转
    function link(url) {
        $(".menu-link-clear").removeClass("active").removeClass("sub-active");
        $("#main-content").attr("src", url);
    }
</script>
</body>
</html>