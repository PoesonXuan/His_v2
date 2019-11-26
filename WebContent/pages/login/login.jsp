<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="XuanZP">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="${pageContext.request.contextPath}/css/bootstrap/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/bootstrap/signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${pageContext.request.contextPath}/js/bootstrap/ie-emulation-modes-warning.js"></script>

<title>登录</title>
</head>
<body style="background-image:url('${pageContext.request.contextPath}/css/img/bg.jpg');no-repeat; background-size:100% 100%; background-attachment:fixed;">
	<input name="msg" type="hidden" id="msg" value="${msg}">
    
    <div class="${pageContext.request.contextPath}/container">

      <form action="${pageContext.request.contextPath}/controller?param=login" method="post" class="form-signin">
        <h2 class="form-signin-heading">登录</h2>
        <label for="inputEmail" class="sr-only">账号</label>
        <input name="loginName" type="text" id="inputEmail" class="form-control" placeholder="请输入账号..." required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="请输入密码..." required>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        <button class="btn btn-lg btn-primary btn-block" type="button" onclick="toRegister()">注册</button>
      </form>
      

    </div> <!-- /container -->
 <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/bootstrap/ie10-viewport-bug-workaround.js"></script>
    
    <script type="text/javascript">
    
    	function toRegister(){
    		window.location.href = '${pageContext.request.contextPath}/pages/register/register.jsp';
    	}
    
    	$(function(){
    		
    		var msg = $('#msg').val();
    		
    		if(msg && msg != ''){
    			alert(msg);
    		}
    		
    	});
    
    </script>
    
</body>
</html>