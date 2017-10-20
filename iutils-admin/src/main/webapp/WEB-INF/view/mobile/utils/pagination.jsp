<!-- 分页工具 -->
<%@ page contentType="text/html;charset=UTF-8" %>
<style>
    .am-pagination .am-disabled {
        display: none;
    }
</style>
<div class="am-pagination-tip">显示 ${page.pageNo+1}/${page.pageNumber} 页，共 ${page.total} 条</div>
<c:if test="${page.total>page.pageSize}">
    <ul class="am-pagination tpl-pagination am-pagination-right" style="margin:0;">
        <li class="<c:if test="${page.hasFirstPage}">am-disabled</c:if>"><a href="javascript:page(${page.first});"><i
                class="am-icon-fast-backward"></i></a></li>
        <li class="<c:if test="${page.hasFirstPage}">am-disabled</c:if>"><a href="javascript:page(${page.prev});"><i
                class="am-icon-backward"></i></a></li>
        <li class="<c:if test="${page.hasLastPage}">am-disabled</c:if>"><a href="javascript:page(${page.next});"><i
                class="am-icon-forward"></i></a></li>
        <li class="<c:if test="${page.hasLastPage}">am-disabled</c:if>"><a href="javascript:page(${page.last});"><i
                class="am-icon-fast-forward"></i></a></li>
    </ul>
    <script type="text/javascript">
        //分页查询
        function page(n) {
            $("#pageNo").val(n);
            $("#searchForm").submit();
            return false;
        }
    </script>
</c:if>