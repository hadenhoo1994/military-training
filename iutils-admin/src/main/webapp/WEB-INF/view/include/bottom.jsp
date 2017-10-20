<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${ctxStatic}/3rd-lib/layer/layer.js"></script>
<!-- 弹出插件 -->
<script src="${ctxStatic}/3rd-lib/amazeui/js/amazeui.min.js"></script>
<script src="${ctxStatic}/3rd-lib/amazeui-color/color.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/custom/js/comm.js"></script>
<script src="${ctxStatic}/assets/js/app.js"></script>
<script>
    //百度统计

</script>
<script>
    //解决页面高度自适应
    $(document).ready(function () {
        if (parent && parent.document) {
            var mainHeight = $(parent.window).height();
            var $mainContent = $("#main-content", parent.document);
            $mainContent.load(function () {
                var thisHeight = $(document).height() + 30;
                if (mainHeight > thisHeight) {
                    thisHeight = mainHeight;
                }
                $mainContent.height(thisHeight);
            });
        }
    });
</script>
