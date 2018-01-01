<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>组织机构编辑</title>
    <%@ include file="../../include/head.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/3rd-lib/jquery-ztree/css/zTreeStyle.css">
    <style type="text/css">
        ul.ztree {
            margin-top: 10px;
            border: 1px solid #ddd;
            background: #fff;
            width: 198px;
            height: 200px;
            overflow-y: auto;
            overflow-x: auto;
        }

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
                            <div class="widget-title am-fl">机构编辑</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="organization"
                                  action="${ctx}/organization/update" method="post">
                                <input type="hidden" name="id" value="${organization.id}"/>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left"><span class="error">*</span>上级机构：</label>
                                    <div class="am-u-sm-12">
                                        <div class="am-input-group" style="width: 200px;">
                                            <input type="text" id="parentName" class="am-form-field"
                                                   value="${organization.organization.name}" required readonly/>
                                            <input type="hidden" id="parentId" name="parentId"
                                                   value="${organization.parentId}"/>
                                            <input type="hidden" id="parentIds" name="parentIds"
                                                   value="${organization.parentIds}"/>
                                            <span class="am-input-group-btn">
									<button class="am-btn am-btn-default" id="menuBtn" type="button">选择</button>
								    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left"><span class="error">*</span>机构名称：</label>
                                    <div class="am-u-sm-12">
                                        <input type="text" minlength="2" name="name" placeholder="机构名称（至少2个字符）"
                                               value="${organization.name}" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">是否可用：</label>
                                    <div class="am-u-sm-12">
                                        <select name="available" data="${organization.available}">
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
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index: 10000;">
    <ul id="tree" class="ztree" style="margin-top:0;"></ul>
</div>
<%@ include file="../../include/bottom.jsp" %>
<script src="${ctxStatic}/3rd-lib/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctxStatic}/custom/js/ztree.select.js"></script>
<script type="text/javascript">
    $(function () {
        //消息提醒
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
            closeModel(true);//关闭窗口
        }
        initSelectValue(true);//初始化下拉框的值

        var zNodes = [
            <c:forEach items="${organizationList}" var="o" varStatus="status">
            {
                id:${o.id},
                pId:${o.parentId},
                pIds: '${o.parentIds}',
                name: '${o.name}',
                open:${o.rootNode}
            }<c:if test="${!status.last}">, </c:if>
            </c:forEach>
        ];
        $.fn.ztreeSelect($("#tree"), zNodes);
    });
    //ztree点击回调
    function ztreeOnClickCall(treeNode) {
        $("#parentId").val(treeNode.id);
        $("#parentIds").val(treeNode.pIds + treeNode.id + "/");
    }
</script>
</body>
</html>
