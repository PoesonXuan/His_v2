<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息管理</title>


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

</head>
<body>
 <form class="navbar-form">
        <h2 class="form-signin-heading">用户信息查询</h2>
        <label for="inputEmail" class="sr-only">请输入用户名</label>
        <input type="text" id="searchInput" class="form-control" placeholder="请输入用户名...">
        <button type="button" class="btn btn-info" onclick="btnClick('search')">搜索用户</button>
        <button type="button" class="btn btn-info" onclick="btnClick('addUser')">添加用户</button>
</form>
<h2 class="sub-header">用户信息列表</h2>
          <div class="table-responsive">
            <table id="user_table_list" class="table table-striped">
              <thead>
                <tr>
                  <th></th>
                  <th>登录名</th>
                  <th>用户名</th>
                  <th>年龄</th>
                  <th>性别</th>
                  <th>用户类别</th>
                  <th>联系方式</th>
                  <th>所属部门</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                
              </tbody>
            </table>
            
          </div>





<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">新增</h4>
                    <input id="user_ID" type="hidden" value=""/>
                    
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="txt_departmentname">登录名</label>
                        <input type="text" id="id_user_login_name" name="user_login_name" class="form-control" disabled="disabled"  placeholder="登录名">
                    </div>
                    <div class="form-group">
                        <label for="txt_parentdepartment">用户名</label>
                        <input type="text" id="id_user_name" name="user_name" class="form-control" id="txt_parentdepartment"  placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label for="txt_parentdepartment">年龄</label>
                        <input type="text" id="id_user_age" name="id_user_age" class="form-control" id="txt_parentdepartment" placeholder="年龄">
                    </div>
                    <div class="form-group">
                        <label for="txt_parentdepartment">性别</label>
                        <!-- <input type="text" id="id_user_sex" name="user_sex" class="form-control" id="txt_parentdepartment" placeholder="性别"> -->
                        
                         <div class="col-sm-12"> 
                        <!-- 样式1 --> 
                        	<select id="id_user_sex" class="form-control"> 
                        		<option value="1">男</option> 
                        		<option value="2">女</option> 
                        	</select> 
                        		
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="txt_parentdepartment">用户类别</label>
                        <!-- <input type="text" id="id_user_type"  name="user_type" class="form-control" id="txt_parentdepartment" placeholder="用户类别"> -->
                        
                        <div class="col-sm-12"> 
                        <!-- 样式1 --> 
                        	<select id="id_user_type" class="form-control"> 
                        		<option></option> 
                        		<option value="1">医务人员</option> 
                        		<option value="2">维护人员</option> 
                        		<option value="3">管理员</option> 
                        		<option value="99">超级管理员</option> 
                        	</select> 
                        		
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="txt_parentdepartment">联系方式</label>
                        <input type="text" id="id_user_tele" name="user_tele" class="form-control" id="txt_parentdepartment" placeholder="联系方式">
                    </div>
                   <!--  <div class="form-group">
                        <label for="txt_parentdepartment">所属部门</label>
                        <input type="text" id="id_user_department" name="user_department" class="form-control" id="txt_parentdepartment" placeholder="所属部门" disabled="disabled">
                    </div> -->
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                    <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="saveUpdate()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
                </div>
            </div>
        </div>
    </div>



<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="js/bootstrap/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/bootstrap/ie10-viewport-bug-workaround.js"></script>
<script type="text/javascript">
	$(function(){
	});
	
	
	function saveUpdate(){
		var userID = $("#user_ID").val();
		var loginName = $("#id_user_login_name").val();
		var userName = $("#id_user_name").val();
		var age = $("#id_user_age").val();
		var sex = $("#id_user_sex").val();
		var useType = $("#id_user_type").val();
		var tele = $("#id_user_tele").val();
		alert('userID='+userID+',loginName='+loginName+',userName='+userName+',age='+age+',sex='+sex+',useType='+useType+',tele='+tele);
		
		
		$.ajax({
            type: "POST",
            url: "controller",
            data: {
            	param:"updateUserById",
            	id:userID,
            	loginName:loginName,
            	userName:userName,
            	age:age,
            	sex:sex,
            	useType:useType,
            	tele:tele
            	},
            dataType: "json",
            success: function(res){
            	var result = res.result;
            	if('Y'==result){
            		 
            		alert('更新完成');
            		 
            		
            	}
            }
        });	
		
	}
	
	function btnClick(type,params){
		if(type){
			if(type == 'search'){
				search();
			}else if(type == 'addUser'){
				alert('addUser');
			}else if(type == 'editor'){
				editor(params);
				alert('editor+params='+params);//myModal
			}else if(type == 'delete'){
				alert('delete+params='+params);
			}
					
		}
	}
	
	
	function editor(id){		
		$.ajax({
            type: "POST",
            url: "controller",
            data: {param:"queryUserById",id:id},
            dataType: "json",
            success: function(res){
            	var result = res.result;
            	if('Y'==result){
            		var data = res.data;
            		alert('start');
            		console.log(data);
            		if(data && data.length >0){
            			var item = data[0];
            			$("#myModalLabel").text('更新');

            			$("#user_ID").val(id);
            			$("#id_user_login_name").val(item.loginName);
            			$("#id_user_name").val(item.name);
            			$("#id_user_age").val(item.age);
            			$("#id_user_sex").val(item.sex);
            			$("#id_user_type").val(item.lev);
            			$("#id_user_tele").val(item.tele);
            			/* $("#id_user_department").val(item.department); */
            			
            			$("#myModal").modal('show');
            		}
            	}
            }
        });	
		
	}
	
	function search(){
		var searchInput = $("#searchInput").val();
		
		if(searchInput && searchInput.length > 0){
			$.ajax({
	            type: "POST",
	            url: "controller",
	            data: {param:"searchUserLikeByName",searchInput:searchInput},
	            dataType: "json",
	            success: function(res){
	            	console.log(res);
	            	var result = res.result;
	            	if('Y'==result){
	            		var data = res.data;
	            		if(data){
	            			var tbody = '';
	            			for(var i=0;i<data.length;i++){
	            				tbody += '<tr>'; 
	            				var item = data[i];
	            				
	            				
	            				tbody = tbody + '<td><input type="radio" name="userID" value="'+item.ID+'"></td>';
	            				
	            				tbody = tbody + '<td>'+item.loginName+'</td>';
	            				tbody = tbody + '<td>'+item.name+'</td>';
	            				tbody = tbody + '<td>'+item.age+'</td>';
	            				tbody = tbody + '<td>'+item.sex+'</td>';
	            				tbody = tbody + '<td>'+item.lev+'</td>';
	            				tbody = tbody + '<td>'+item.tele+'</td>';
	            				tbody = tbody + '<td>'+item.department+'</td>';
	            				tbody = tbody + '<td>'
	            					+ '<p>'
	            					+ '<button type="button" class="btn btn-xs btn-info"  onclick="btnClick(\'editor\',\''+item.id+'\')">编辑</button>'
	            					/* +'<button type="button" class="btn btn-xs btn-danger"   onclick="btnClick(\'delete\',\''+item.id+'\')">删除</button>' */
	            					+'</p>'
	            					+'</td>';
	            				tbody += '</tr>'; 
	            			}
	            			
	            			$("#user_table_list tbody").empty("");
	            			
	            			$("#user_table_list tbody").append(tbody);
	            		}else{
	            			$("#user_table_list tbody").empty("");
	            		}
	            	}
	            }
	        });	
			
		}
		
		
	}

</script>        
</body>
</html>