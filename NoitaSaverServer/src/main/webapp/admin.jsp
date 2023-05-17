
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
      <div class="layui-logo">layui 后台布局</div>
      <!-- 头部区域（可配合layui已有的水平导航） -->
      <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item"><a href="">控制台</a></li>
        <li class="layui-nav-item"><a href="">商品管理</a></li>
        <li class="layui-nav-item"><a href="">用户</a></li>
        <li class="layui-nav-item">
          <a href="javascript:;">其它系统</a>
          <dl class="layui-nav-child">
            <dd><a href="">邮件管理</a></dd>
            <dd><a href="">消息管理</a></dd>
            <dd><a href="">授权管理</a></dd>
          </dl>
        </li>
      </ul>
      <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
          <a href="javascript:;">
            <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
            ${sessionScope.user.username}
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
          <li class="layui-nav-item layui-nav-itemed">
            <a class="" href="javascript:;">所有商品</a>
            <dl class="layui-nav-child">
              <dd id="m1" class="subMenu"><a href="javascript:;" data-url="sao.html">用户管理</a></dd>
              <dd id="m2" class="subMenu"><a href="javascript:;" data-url="#">用户</a></dd>
              <dd id="m3" class="subMenu"><a href="javascript:;" data-url="#">用户2</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item">
            <a href="javascript:;">解决方案</a>
            <dl class="layui-nav-child">
              <dd><a href="javascript:;">列表一</a></dd>
              <dd><a href="javascript:;">列表二</a></dd>
              <dd><a href="">超链接</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item"><a href="">云市场</a></li>
          <li class="layui-nav-item"><a href="">发布商品</a></li>
        </ul>
      </div>
    </div>

    <div class="layui-tab layui-tab-brief" lay-filter="demo" lay-allowclose="true">
      <ul class="layui-tab-title site-demo-title">
        <li id="homeTab" class="layui-this" lay-id="1">首页</li>
      </ul>

      <div class="layui-body layui-tab-content site-demo site-demo-body" style="overflow: hidden;">
        <div class="layui-tab-item layui-show" style="overflow-y:auto;">
          <iframe src="admin/main.html" class="layadmin-iframe" frameborder="no" border="0" allowtransparency="yes"></iframe>
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