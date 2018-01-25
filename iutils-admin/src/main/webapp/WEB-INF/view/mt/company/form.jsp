<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>连表</title>
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
                            <div class="widget-title am-fl">连表</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="company" action="${ctx}/mt/company/<c:choose><c:when test="${empty company.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>" method="post">
                                <input type="hidden" name="id" value="${company.id}"/>
                                <input type="hidden" id="bid" value="${company.battalionId}"/>
                                <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">连名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="name" placeholder="连名称" value="${company.name}" required/>
                                        </div>
                                    </div>
                                    <div class="am-form-group" hidden>
                                        <label class="am-u-sm-3 am-form-label">营名称：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="battalionName" id="battalionName" placeholder="营名称" value="${company.battalionName}" hidden="true" />
                                        </div>
                                    </div>
                                <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">所属营：</label>
                                        <div class="am-u-sm-9">
                                            <select id="battalionId" name="battalionId">
                                                <option >请选择连所属营</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">备注：</label>
                                        <div class="am-u-sm-9">
                                            <input type="text" name="remarks" placeholder="备注" value="${company.remarks}" />
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
    })
    $("#battalionId").change(function () {
        //当前选择的option
        var name = $("#battalionId").find("option:selected").text();
         $("#battalionName").val(name);
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
