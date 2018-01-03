<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>用户表</title>
    <%@ include file="../../include/head.jsp"%>
    <style>
        .tpl-content-wrapper{margin-left:0}
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
                            <div class="widget-title am-fl">用户表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="userInfo" action="${ctx}/mt/userInfo/<c:choose><c:when test="${empty userInfo.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>" method="post">
                            <input type="hidden" name="id" value="${userInfo.id}"/>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">姓名：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="name" placeholder="姓名" value="${userInfo.name}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">性别：</label>
                                        <div class="am-u-sm-9">
                                            <select name="gender">
                                                <option value="0">男</option>
                                                <option value="1">女</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">班级id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="classId" placeholder="班级id" value="${userInfo.classId}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">班级名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="className" placeholder="班级名称" value="${userInfo.className}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">专业id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="professionId" placeholder="专业id" value="${userInfo.professionId}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">专业名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="professionName" placeholder="专业名称" value="${userInfo.professionName}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">系别id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="departmentId" placeholder="系别id" value="${userInfo.departmentId}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">系别名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="departmentName" placeholder="系别名称" value="${userInfo.departmentName}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">排id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="platoonId" placeholder="排id" value="${userInfo.platoonId}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">排名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="platoonName" placeholder="排名称" value="${userInfo.platoonName}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">连id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="companyId" placeholder="连id" value="${userInfo.companyId}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">连名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="companyName" placeholder="连名称" value="${userInfo.companyName}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">营id：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="battalionId" placeholder="营id" value="${userInfo.battalionId}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">营名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="battalionName" placeholder="营名称" value="${userInfo.battalionName}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">手机号码：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="mobileNumber" placeholder="手机号码" value="${userInfo.mobileNumber}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">qq号码：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="qq" placeholder="qq号码" value="${userInfo.qq}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">微信号：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="wx" placeholder="微信号" value="${userInfo.wx}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">住址：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="address" placeholder="住址" value="${userInfo.address}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">用户身份标识：</label>
                                        <div class="am-u-sm-9">
                                            <select name="identity">
                                                <option value="0">学生</option>
                                                <option value="1">教师</option>
                                                <option value="2">教官</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">备注：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="remarks" placeholder="备注" value="${userInfo.remarks}" />
                                        </div>
                                    </div>
                            <div class="am-form-group">
                                <div class="am-u-sm-9 am-u-sm-push-3">
                                    <button type="submit" class="am-btn am-btn-primary">保存</button>
                                    <button type="button" class="am-btn am-btn-danger" onclick="closeModel(false)">关闭</button>
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
<%@ include file="../../include/bottom.jsp"%>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if(msg!=''){
            showMsg(msg);
            closeModel(true);//关闭窗口
        }
    });
</script>
</body>
</html>
