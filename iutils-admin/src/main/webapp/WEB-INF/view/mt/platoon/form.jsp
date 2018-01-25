<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>军排表</title>
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
                            <div class="widget-title am-fl">军排表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="platoon" action="${ctx}/mt/platoon/<c:choose><c:when test="${empty platoon.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>" method="post">
                                <input type="hidden" name="id" value="${platoon.id}"/>
                                <input type="hidden" id="bid" value="${platoon.battalionId}"/>
                                <input type="hidden" id="cid" value="${platoon.companyId}"/>
                                <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">排名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="name" placeholder="排名称" value="${platoon.name}" required/>
                                        </div>
                                    </div>

                                    <div class="am-form-group" hidden>
                                      <label class="am-u-sm-3 am-form-label">营名称：</label>
                                         <div class="am-u-sm-9">
                                            <input type="text" name="battalionName" id="battalionName" placeholder="营名称" value="${platoon.battalionName}" hidden="true" />
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
                                            <input type="text" name="companyName" id="companyName" placeholder="营名称" value="${platoon.companyName}" hidden="true" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">所属连：</label>
                                        <div class="am-u-sm-9">
                                            <select id="companyId" name="companyId">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">学生数：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="studentNum" placeholder="学生数" value="${platoon.studentNum}" />
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">备注：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="remarks" placeholder="备注" value="${platoon.remarks}" />
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
    }
    $("#companyId").change(function () {
        //当前选择的option
        var name = $("#companyId").find("option:selected").text();
        $("#companyName").val(name);
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
