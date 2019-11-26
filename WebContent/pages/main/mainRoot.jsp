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
<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="css/bootstrap/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/bootstrap/dashboard.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="js/bootstrap/ie-emulation-modes-warning.js"></script>

<title>Home</title>
</head>
<body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#" onclick="home.mouseClick(0)">医院信息管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul id="nav" class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/controller?param=loginOut">退出</a></li>
            <li><a href="#">欢迎 ${currentUser.name}</a></li>
            <!-- <li><a href="#">关于</a></li> -->
          </ul>
          <!-- <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form> -->
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul id="treeNav" class="nav nav-sidebar">
            <li><a href="#" onclick="home.mainContent()">首页</a></li>
            <li><a href="#" onclick="home.leftMenu(101)">部门管理</a></li>
            <li><a href="#" onclick="home.leftMenu(102)">用户管理</a></li>
            <li><a href="#" onclick="home.leftMenu(1001)">排班列表</a></li>
          </ul>
        
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <iframe id="content" src="pages/welcome/welcome.jsp" style="width: 100%;height: 500px;border: 0;"></iframe>
        </div>
      </div>
    </div>
 <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="js/bootstrap/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/bootstrap/ie10-viewport-bug-workaround.js"></script>
   
	<script src="${pageContext.request.contextPath}/js/home.js"></script>
</body>
</html>