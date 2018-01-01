<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>角色编辑</title>
    <%@ include file="../../include/head.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/3rd-lib/jquery-ztree/css/zTreeStyle.css">
    <style>
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
                            <div class="widget-title am-fl">角色编辑</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="role"
                                  action="${ctx}/role/update" method="post">
                                <input type="hidden" name="id" value="${role.id}"/>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left"><span class="error">*</span>归属机构：</label>
                                    <div class="am-u-sm-12">
                                        <div class="am-input-group" style="width: 200px;">
                                            <input type="text" id="parentName" class="am-form-field" minlength="1"
                                                   placeholder="归属机构（必选）" value="${role.organization.name}" required
                                                   readonly/>
                                            <input type="hidden" id="organizationId" name="organizationId"
                                                   value="${role.organizationId}"/>
                                            <span class="am-input-group-btn">
									<button class="am-btn am-btn-default" id="menuBtn" type="button">选择</button>
								    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left"><span class="error">*</span>角色名称：</label>
                                    <div class="am-u-sm-12">
                                        <input type="text" name="name" value="${role.name}" minlength="1"
                                               placeholder="角色名称（必填）" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left"><span class="error">*</span>角色标识：</label>
                                    <div class="am-u-sm-12">
                                        <input type="text" name="role" value="${role.role}" minlength="1"
                                               placeholder="角色标识（必填）" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">是否可用：</label>
                                    <div class="am-u-sm-12">
                                        <select name="available" data="${role.available}">
                                            <option value="true">可用</option>
                                            <option value="false">禁用</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group" style="display: none;">
                                    <label class="am-u-sm-12 am-form-label am-text-left">数据范围：</label>
                                    <div class="am-u-sm-12">
                                        <select name="type" data="${role.dataScope}">
                                            <c:forEach items="${dataScope}" var="m">
                                                <option value="${m}">${m.info}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">角色授权：</label>
                                    <div class="am-u-sm-12">
                                        <input type="hidden" id="resourceIds" name="resourceIds"
                                               value='<c:forEach items="${role.resourceIds}" var="item" varStatus="status">${item}<c:if test="${!status.last}">,</c:if></c:forEach>'/>
                                        <ul id="treeOrg" class="ztree"
                                            style="margin-top:0; width:200px;height: auto;"></ul>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">备注：</label>
                                    <div class="am-u-sm-12">
                                        <textarea name="remarks">${role.remarks}</textarea>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-12">
                                        <shiro:hasPermission name="sys:role:edit">
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
<div id="menuContent" class="menuContent" style="display:none;position: absolute;z-index: 10000;">
    <ul id="tree" class="ztree" style="margin-top:0;"></ul>
</div>
<%@ include file="../../include/bottom.jsp" %>
<script src="${ctxStatic}/3rd-lib/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${ctxStatic}/custom/js/ztree.select.js"></script>
<script src="${ctxStatic}/custom/js/ztree.org.js"></script>
<script>
    $(function () {
        //消息提醒
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
            closeModel(true);//关闭窗口
        }
        initSelectValue(true);//初始化下拉框的值
        //资源树选择
        var zNodes = [<c:forEach items="${organizationList}" var="o" varStatus="status">{
            id:${o.id},
            pId:${o.parentId},
            name: '${o.name}',
            open:${o.rootNode}
        }<c:if test="${!status.last}">, </c:if></c:forEach>];
        $.fn.ztreeSelect($("#tree"), zNodes);

        //权限树选择
        var orgZNodes = [<c:forEach items="${resourceList}" var="r" varStatus="status"><c:if test="${not r.rootNode}">{
            id:${r.id},
            pId:${r.parentId},
            name: "${r.name}",
            checked:${fnc:in(role.resourceIds, r.id)},
            open: true
        }<c:if test="${!status.last}">, </c:if></c:if></c:forEach>];
        $.fn.ztreeOrg($("#treeOrg"), orgZNodes);
    });
    //ztree资源点击回调
    function ztreeOnClickCall(treeNode) {
        $("#organizationId").val(treeNode.id);
    }
    //ztree机构点击回调
    function ztreeOrgOnClickCall(id) {
        $("#resourceIds").val(id);
    }
</script>
</body>
</html>