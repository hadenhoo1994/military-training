<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>新建<c:choose><c:when
            test="${msgSend.type=='mail'}">站内信</c:when><c:otherwise>通知</c:otherwise></c:choose></title>
    <%@ include file="../../include/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/3rd-lib/wangEditor/css/wangEditor.min.css">
    <style type="text/css">
        .tpl-content-wrapper {
            margin-left: 0
        }

        span.close {
            color: #dd514c;
            position: absolute;
            margin-top: -13px;
            margin-left: 4px;
            width: 14px;
            height: 14px;
        }
    </style>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">新建<c:choose><c:when
                                    test="${msgSend.type=='mail'}">站内信</c:when><c:otherwise>通知</c:otherwise></c:choose></div>
                        </div>
                        <div class="widget-body am-fr">
                            <form id="mform" class="am-form tpl-form-border-form" data-am-validator
                                  modelAttribute="msgSend" method="post">
                                <input type="hidden" name="id" value="${msgSend.id}"/>
                                <input type="hidden" name="type" value="${msgSend.type}"/>
                                <input type="hidden" id="users" name="users" value="${msgSend.users}"/>
                                <div class="am-form-group">
                                    <label class="am-u-sm-2 am-form-label">发送给：</label>
                                    <div class="am-u-sm-10" id="usersHtml">
                                        <button type="button" class="am-btn am-btn-primary am-btn-xs"
                                                onclick="openModel(false,'${ctx}/user/selectUser?users=${msgSend.users}')">
                                            <span class="am-icon-plus"></span></button>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-2 am-form-label">标题：</label>
                                    <div class="am-u-sm-10">
                                        <input type="text" name="title" placeholder="请输入标题" value="${msgSend.title}"
                                               required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-2 am-form-label">内容：</label>
                                    <div class="am-u-sm-10">
                                        <textarea id="content" name="content" rows="20" placeholder="请输入内容...">
                                            ${not empty msgSend.content?msgSend.content:''}
                                        </textarea>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-2 am-form-label">级别：</label>
                                    <div class="am-u-sm-10">
                                        <select name="level" data="${msgSend.level}">
                                            <option value="0">普通</option>
                                            <option value="1">高</option>
                                            <option value="-1">低</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-10 am-u-sm-push-2">
                                        <button type="button" class="am-btn am-btn-danger" onclick="closeModel(false)">
                                            取消
                                        </button>
                                        <button type="button" id="save" class="am-btn am-btn-primary">保存草稿</button>
                                        <button type="button" id="send" class="am-btn am-btn-primary">发送</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../include/bottom.jsp" %>
<script type="text/javascript" src="${ctxStatic}/3rd-lib/wangEditor/js/wangEditor.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/custom/js/jq.editor.js"></script>
<script src="${ctxStatic}/3rd-lib/tpl/tpl.js"></script>
<!-- 定义模板 -->
<script type="text/template" id="usersTpl" desc="发送给模板">
    <# for(var i=0; i
    <list.length; i++) {var item=list[i];#>
    <button type="button" class="am-btn am-btn-primary am-btn-xs">
        <#=item.name #><span class="close" onclick="removeUser('<#=item.id #>',this)"><span
                class="am-icon-remove"></span></span>
    </button>
    <#}#>
        <button type="button" class="am-btn am-btn-primary am-btn-xs"
                onclick="openModel(false,'${ctx}/user/selectUser?users=<#=ids #>')"><span class="am-icon-plus"></span>
        </button>
</script>
<script type="text/javascript">
    //当前地址
    var cctx = '${pageContext.request.contextPath}';
    $(document).ready(function () {
        //消息提醒
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
            closeModel(true);//关闭窗口
        }
        initSelectValue(true);//初始化下拉框的值
        //初始化编辑器
        $.fn.initEditor('${ctx}', '${ctxStatic}', 'content');

        //绑定事件
        $("#save").click(function () {
            //保存草稿
            var users = $("#users").val().split(",");
            if (users.length == 0 || users[0] == "") {
                showMsg('请选择发送给谁');
                return;
            }
            var content = $("#content").val();
            if (content == "" || content.replace(/(^\s*)|(\s*$)/g, "") == "") {
                showMsg('请输入内容');
                return;
            }
            var $form = $("#mform");
            $form.attr("action", "${ctx}/sys/msgSend/save");
            $form.submit();
        });
        $("#send").click(function () {
            //发送信息
            var users = $("#users").val().split(",");
            if (users.length == 0 || users[0] == "") {
                showMsg('请选择发送给谁');
                return;
            }
            var content = $("#content").val();
            if (content == "" || content.replace(/(^\s*)|(\s*$)/g, "") == "") {
                showMsg('请输入内容');
                return;
            }
            var $form = $("#mform");
            $form.attr("action", "${ctx}/sys/msgSend/send");
            $form.submit();
        });
    });
    //选择用户后回调函数
    function callSelectUser(ids) {
        $("#users").val(ids);
        get("${ctx}/user/getUsers?users=" + ids, function (data) {
            if (data.ret == 1 && data.data) {
                $("#usersHtml").html(tpl('#usersTpl', {ids: ids, list: data.data}));
            }
        });
    }
    callSelectUser('${msgSend.users}');
    //删除用户
    function removeUser(id, o) {
        $(o).parent().remove();
        var users = $("#users").val().split(",");
        var ids = "";
        for (var i = 0; i < users.length - 1; i++) {
            if (users[i] != id) {
                ids += users[i] + ",";
            }
        }
        callSelectUser(ids);
    }
</script>
</body>
</html>
