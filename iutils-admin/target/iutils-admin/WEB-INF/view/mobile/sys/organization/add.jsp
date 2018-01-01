<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>组织机构编辑</title>
    <%@ include file="../../include/head.jsp" %>
    <style type="text/css">
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
                            <div class="widget-title am-fl">机构新增</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="organization"
                                  action="${ctx}/organization/update" method="post">
                                <input type="hidden" name="parentId" value="${organization.id}"/>
                                <input type="hidden" name="parentIds"
                                       value="${organization.parentIds}${organization.id}/"/>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">上级机构：</label>
                                    <div class="am-u-sm-12">
                                        <input type="text" id="parentName" value="${organization.name}" readonly/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left"><span class="error">*</span>机构名称：</label>
                                    <div class="am-u-sm-12">
                                        <input type="text" minlength="2" name="name" placeholder="机构名称（至少2个字符）"
                                               required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">是否可用：</label>
                                    <div class="am-u-sm-12">
                                        <select name="available" data="true">
                                            <option value="true">可用</option>
                                            <option value="false">禁用</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-12">
                                        <shiro:hasPermission name="sys:organization:edit">
                                            <button type="submit" class="am-btn am-btn-primary">保存</button>
                                        </shiro:hasPermission>
                                        <button type="button" class="am-btn am-btn-danger" onclick="closeModel(false)">
                                            关闭
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
<script type="text/javascript">
    $(function () {
        //消息提醒
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
            closeModel(true);//关闭窗口
        }
        initSelectValue(true);//初始化下拉框的值
    });
</script>
</body>
</html>
