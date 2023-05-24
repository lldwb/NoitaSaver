
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>后台首页</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/global.css">
  <link rel="stylesheet" href="css/admin.css">
  <script src="layui/layui.js"></script>
  <script src="js/admin.js"></script>
</head>

<body class="layui-layout-body">
  <div class="layui-layout layui-layout-admin">
    <div class="layui-header">
      <div class="layui-logo">NoitaSaver用户管理系统</div>
      <!-- 头部区域（可配合layui已有的水平导航） -->
      <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
          <a href="javascript:;">
            <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
            ${sessionStorage.getItem('user')}
          </a>
          <dl class="layui-nav-child">
            <dd><a href="">基本资料</a></dd>
            <dd><a href="">安全设置</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="">退了</a></li>
      </ul>
    </div>

    <div class="layui-side layui-bg-black">
      <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
          <li class="layui-nav-item"><a data-url="sao.html">用户管理</a></li>
        </ul>
      </div>
    </div>

    <div class="layui-tab layui-tab-brief" lay-filter="demo" lay-allowclose="true">
      <ul class="layui-tab-title site-demo-title">
        <li id="homeTab" class="layui-this" lay-id="1">首页</li>
      </ul>

      <div class="layui-body layui-tab-content site-demo site-demo-body" style="overflow: hidden;">
        <div class="layui-tab-item layui-show" style="overflow-y:auto;">
          <iframe src="sao.html" class="layadmin-iframe" frameborder="no" border="0" allowtransparency="yes"></iframe>
        </div>
      </div>
    </div>

    <div class="layui-footer">
      <!-- 底部固定区域 -->
      © layui.com - 底部固定区域
    </div>
  </div>

</body>

</html>