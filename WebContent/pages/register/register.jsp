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

<title>注册</title>
</head>
<body style="background-image:url('${pageContext.request.contextPath}/css/img/bg.jpg');no-repeat; background-size:100% 100%; background-attachment:fixed;">
	<input name="msg" type="hidden" id="msg" value="${msg}">
	<a href="${pageContext.request.contextPath}/pages/login/login.jsp" style="color: green;">返回登录界面>>></a>
    
    <div class="${pageContext.request.contextPath}/container">

      <form action="controller?param=login" method="post" class="form-signin">
        <h2 class="form-signin-heading">注册</h2>
        
        
        <label for="inputName" class="sr-only">名字</label>
        <input name="name" type="text" id="inputName" class="form-control" placeholder="请输入名字..." required autofocus>
        <br/>
        
        <label for="inputLoginName" class="sr-only">账号</label>
        <input name="loginName" type="text" id="inputLoginName" class="form-control" placeholder="请输入账号..." required autofocus>
        <br/>
        <label for="inputPassword" class="sr-only">密码</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="请输入密码..." required>
        <label for="reInputPassword" class="sr-only">确认密码</label>
        <input name="password" type="password" id="reInputPassword" class="form-control" placeholder="再次请输入密码..." required>
        
        <br/>
        
        <label for="inputAge" class="sr-only">年龄</label>
        <input name="age" min="10" max="100" type="number" id="inputAge" class="form-control" placeholder="请输入年龄..." required>
        
        <br />
        
        <label for="inputTel" class="sr-only">联系方式</label>
        <input name="tel" type="number" id="inputTel" class="form-control" placeholder="请输入联系方式..." required autofocus>
        
        <br />
        
        <label for="sex" class="sr-only">性别</label>
        <select id="sex" class="form-control">
				<option selected="selected">请选择性别（当前选项为空）</option>
				<option value="1">男</option>
				<option value="2">女</option>
		</select> 
        <br />
        
        <label for="userType" class="sr-only">用户类别</label>
        <select id="userType" class="form-control">
				<option  selected="selected">请选择用户类别（当前选项为空）</option>
				<option value="2">维护人员</option>
				<option value="1">医务人员</option>
		</select> 
		
        <br />
        
        <label  class="sr-only">所属部门</label>
        <select id="departmentID" class="form-control">
				<option value=""></option>
		</select> 
        <br/>
        <button class="btn btn-lg btn-primary btn-block" type="button" onclick="register()">注册</button>
      </form>
      

    </div> <!-- /container -->
 <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/js/bootstrap/ie10-viewport-bug-workaround.js"></script>
    
    <script type="text/javascript">
    	$(function(){
    		getDepartment();
    		
    		var msg = $('#msg').val();
    		
    		if(msg && msg != ''){
    			alert(msg);
    		} 
    		
    	});
    	
    	function getDepartment(){
    		$.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/controller",
	            data: {param:"getDepartments"},
	            dataType: "json",
	            success: function(res){
	            	var result = res.result;
	            	var options = '';
	            	if('Y'==result){
	            		
	            		var data = res.data;
	            		var options = '<option  selected="selected" >请选择所属部门（当前选项为空）</option>';
	            		for(var i=0;i<data.length;i++){
	            			var item = data[i];
	            			options = options + '<option value="'+ item.ID+'">' + item.NAME + '</option>';
	            		}
	            		
	            		$("#departmentID").append(options);
	            		
	            	}
	            }
	        });	
    		
    	}
    
    	
    	function register(){
    		var inputName = $('#inputName').val();
    		var loginName = $('#inputLoginName').val();
    		var inputPassword = $('#inputPassword').val();
    		var reInputPassword = $('#reInputPassword').val();
    		var inputAge = $('#inputAge').val();
    		var inputTel = $('#inputTel').val();
    		var sex = $('#sex').val();
    		var userType = $('#userType').val();
    		var departmentID = $('#departmentID').val();
    		
    		$.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/controller",
	            data: {
	            	param:"register",
	            	name:inputName,
	            	loginName:loginName,
	            	pwd:inputPassword,
	            	repwd:reInputPassword,
	            	age:inputAge,
	            	sex:sex,
	            	lev:userType,
	            	tele:inputTel,
	            	departmentId:departmentID
	            	},
	            dataType: "json",
	            success: function(res){
	            	var result = res.result;
	            	var options = '';
	            	var info = res.info;
            		if(info && info != ''){
            			alert(info);
            		}
	            }
	        });	
    		
    		
    	}
    </script>
    
</body>
</html>