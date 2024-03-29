<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <script src="webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="webjars/bootstrap/3.3.5/css/bootstrap.min.css" />
    <!--    <script type="text/javascript" src="/js/validate.js"></script>-->
    <script type="text/javascript" src="/js/kaptcha.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>
    <meta charset="UTF-8"/>
    <title>登录页面</title>
</head>
<body>

<p id="prompt"></p>

<p>账号：<input type="text"  id="name" name="name"/></p>
<p>密码：<input type="password"  id="password" name="password"/></p>
<p></p>
<!-- begin:验证码 -->
<input type="text" class="form-controlA" id="kaptcha" name="kaptcha"
       placeholder="请输入验证码" style="color: #000000;" />
<span class="help-block"></span>
<img src="kaptcha.jpg" width="200" id="kaptchaImage" title="看不清，点击换一张" /> <small>看不清，点击换一张</small>
<!-- end:验证码 -->
<p></p>
<p>
    <button type="button" class="btn btn-primary" id="user_insert_btn">登录</button>
</p>

<!-- begin:注册功能 -->
<p></p><p><a th:href="@{/index} ">点击前往注册吧！</a></p>

</body>

</html>
