<%@ page contentType="text/html;charset=UTF-8" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<link rel="icon" type="image/png" href="${ctxStatic}/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="${ctxStatic}/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="${iutilsName}"/>
<link rel="stylesheet" href="${ctxStatic}/3rd-lib/amazeui/css/amazeui.min.css"/>
<link rel="stylesheet" href="${ctxStatic}/3rd-lib/amazeui-color/color.min.css">
<link rel="stylesheet" href="${ctxStatic}/assets/css/app.css">
<script src="${ctxStatic}/3rd-lib/jquery/2.2.3/jquery.min.js"></script>
<style>
    .color-form-group {
        margin: 0;
    }
</style>
<c:set var="loginUser" value="${fnc:getLoginUser()}"></c:set>
