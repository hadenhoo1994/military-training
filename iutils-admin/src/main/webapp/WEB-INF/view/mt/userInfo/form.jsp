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
                                <input type="hidden" id="bid" value="${userInfo.battalionId}"/>
                                <input type="hidden" id="cid" value="${userInfo.companyId}"/>
                                <input type="hidden" id="pid" value="${userInfo.platoonId}"/>
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
                                                <option value="0" <c:if test="${userInfo.gender==0}">selected</c:if>>男</option>
                                                <option value="1" <c:if test="${userInfo.gender==1}">selected</c:if>>女</option>
                                            </select>
                                        </div>
                                    </div>
                                <div class="am-form-group" hidden>
                                    <label class="am-u-sm-3 am-form-label">营名称：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="battalionName" id="battalionName" placeholder="营名称" value="${userInfo.battalionName}" hidden="true" />
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">所属营：</label>
                                    <div class="am-u-sm-9">
                                        <select id="battalionId" name="battalionId">
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group" hidden>
                                    <label class="am-u-sm-3 am-form-label">连名称：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="companyName" id="companyName" placeholder="连名称" value="${userInfo.companyName}" hidden="true" />
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">所属连：</label>
                                    <div class="am-u-sm-9">
                                        <select id="companyId" name="companyId">
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group" hidden>
                                    <label class="am-u-sm-3 am-form-label">排名称：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="platoonName" id="platoonName" placeholder="排名称" value="${userInfo.platoonName}" hidden="true" />
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">所属排：</label>
                                    <div class="am-u-sm-9">
                                        <select id="platoonId" name="platoonId">
                                        </select>
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
                                                <option value="0" <c:if test="${userInfo.identity==0}">selected</c:if>>学生</option>
                                                <option value="1" <c:if test="${userInfo.identity==1}">selected</c:if>>教师</option>
                                                <option value="2" <c:if test="${userInfo.identity==2}">selected</c:if>>教官</option>
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
<script src="/static/3rd-lib/jquery/2.2.3/jquery.min.js"></script>
<script type="text/javascript" src="/static/assets/js/rea.js"></script>
<script>
    //查询营
    $(document).ready(function () {
        var battalionId = $("#bid").val();
        var select = $("#battalionId");
        var battalion = getBattalion();
        for (var i=0;i<=battalion.length;i++){
            if (battalion[i]==undefined){
                break;
            }
            if (battalionId == battalion[i].id){
                var str = ' <option value="'+battalion[i].id+'" selected>'+battalion[i].name+'</option>';
                $(".battalionName").val(battalion[i].name);
            }else{
                var str = ' <option value="'+battalion[i].id+'" >'+battalion[i].name+'</option>';
            }
            select.append(str)
        }
        //如果select没有选中 选择第一个为默认的选择项
        if($(select).find("option:selected").text() == undefined){
            $("#battalionId option:first").attr("selected", true);
            var name = $("#battalionId").find("option:selected").text();
            $("#battalionName").val(name);
        }else{
            var name = $("#battalionId").find("option:selected").text();
            $("#battalionName").val(name);
        }
        //将连查出来
        selectCompany()
    })
    $("#battalionId").change(function () {
        //当前选择的option
        var name = $("#battalionId").find("option:selected").text();
        $("#battalionName").val(name);
        //顺手查询连
        selectCompany()
    })
</script>

<script>
    //查询连
    function selectCompany() {
        var battalionId = $("#battalionId").find("option:selected").val();
        var companyId = $("#cid").val();
        var select = $("#companyId");
        select.empty();
        var company = getCompany(battalionId);
        for (var i=0;i<=company.length;i++){
            if (company[i]==undefined){
                break;
            }
            if (companyId == company[i].id){
                var str = ' <option value="'+company[i].id+'" selected>'+company[i].name+'</option>';
                $(".battalionName").val(company[i].name);
            }else{
                var str = ' <option value="'+company[i].id+'" >'+company[i].name+'</option>';
            }
            select.append(str)
        }
        //如果select没有选中 选择第一个为默认的选择项
        if($(select).find("option:selected").text() == undefined){
            $("#companyId option:first").attr("selected", true);
            var name = $("#companyId").find("option:selected").text();
            $("#companyName").val(name);
        }else{
            var name = $("#companyId").find("option:selected").text();
            $("#companyName").val(name);
        }
        selectPlatoon()
    }


    $("#companyId").change(function () {
        //当前选择的option
        var name = $("#companyId").find("option:selected").text();
        $("#companyName").val(name);
        selectPlatoon()
    })
</script>

<script>
    //查询排
    function selectPlatoon() {
        var companyId = $("#companyId").find("option:selected").val();
        var platoonId = $("#pid").val();
        var select = $("#platoonId");
        select.empty();
        var platoon = getPlatoon(companyId);
        for (var i=0;i<=platoon.length;i++){
            if (platoon[i]==undefined){
                break;
            }
            if (platoonId == platoon[i].id){
                var str = ' <option value="'+platoon[i].id+'" selected>'+platoon[i].name+'</option>';
                $(".battalionName").val(platoon[i].name);
            }else{
                var str = ' <option value="'+platoon[i].id+'" >'+platoon[i].name+'</option>';
            }
            select.append(str)
        }
        //如果select没有选中 选择第一个为默认的选择项
        if($(select).find("option:selected").text() == undefined){
            $("#platoonId option:first").attr("selected", true);
            var name = $("#platoonId").find("option:selected").text();
            $("#platoonName").val(name);
        }else{
            var name = $("#platoonId").find("option:selected").text();
            $("#platoonName").val(name);
        }
    }
    $("#platoonId").change(function () {
        //当前选择的option
        var name = $("#platoonId").find("option:selected").text();
        $("#platoonName").val(name);
    })
</script>


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
