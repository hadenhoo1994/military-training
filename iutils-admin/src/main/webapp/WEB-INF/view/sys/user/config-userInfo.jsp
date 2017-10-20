<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>用户信息</title>
    <%@ include file="../../include/head.jsp" %>
    <style>
        .tpl-content-wrapper {
            margin-left: 0
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
                            <div class="widget-title am-fl">用户设置</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" modelAttribute="user"
                                  action="${ctx}/user/saveUserInfo" method="post">
                                <input type="hidden" name="id" value="${user.id}"/>
                                <input type="hidden" id="photo" name="photo" value="${user.photo}"/>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">用户头像：</label>
                                    <div class="am-u-sm-9 am-margin-top-xs">
                                        <div class="am-form-group am-form-file">
                                            <div class="tpl-form-file-img tpl-user-panel-profile-picture">
                                                <img id="showPic"
                                                     src="${pageContext.request.contextPath}/${user.photo}<c:if test="${empty user.photo}">static/assets/img/user.png</c:if>"
                                                     alt="用户图像">
                                            </div>
                                            <button type="button" id="uploadPic"
                                                    class="am-btn am-btn-danger am-btn-sm ">
                                                <i class="am-icon-cloud-upload"></i> 上传
                                            </button>
                                            <input type="file" name="file" id="file">
                                        </div>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">姓名：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" name="name"
                                               value="${user.name}" placeholder="请输入姓名">
                                        <small></small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">邮箱：</label>
                                    <div class="am-u-sm-9">
                                        <input type="email" class="tpl-form-input am-margin-top-xs" name="email"
                                               placeholder="请输入邮箱" value="${user.email}">
                                        <small></small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">手机：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" name="mobile"
                                               placeholder="请输入手机" value="${user.mobile}">
                                        <small></small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">电话：</label>
                                    <div class="am-u-sm-9">
                                        <input type="tel" class="tpl-form-input am-margin-top-xs" name="phone"
                                               placeholder="请输入电话" value="${user.phone}">
                                        <small></small>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">个人简介：</label>
                                    <div class="am-u-sm-9 am-margin-top-xs">
                                        <textarea class="" rows="5" name="remarks"
                                                  placeholder="请输入个人简介">${user.remarks}</textarea>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="submit" class="am-btn am-btn-primary tpl-btn-bg-color-success ">
                                            保存修改
                                        </button>
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
<script type="text/javascript" src="${ctxStatic}/custom/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
        }
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        var ctx = "${pageContext.request.contextPath}";
        $("#uploadPic").click(function () {
            $("#file").click();
        });
        $("#file").change(function () {
            $.ajaxFileUpload({
                url: '${ctx}/upload/local',
                type: 'post',
                secureuri: false,
                fileElementId: 'file',
                dataType: 'text',
                success: function (data, status) {
                    data = JSON.parse(delHtmlTag(data));
                    if (data.ret == 1) {
                        $("#showPic").attr("src", ctx + '/' + data.data);
                        $("#photo").val(data.data);
                    } else {
                        alert(data.msg);
                    }
                },
                error: function (data, status, e) {
                    alert("上传失败");
                }
            });
        });
    });
</script>
</body>
</html>