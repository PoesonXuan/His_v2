<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>department</title>
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
        <h2 class="form-signin-heading">部门信息查询</h2>
        <label for="inputEmail" class="sr-only">请输入部门名称</label>
        <input id="searchInput" type="text" class="form-control" placeholder="请输入部门名...">
        <button type="button" class="btn btn-info" onclick="btnClick('search')">搜索部门</button>
        <button type="button" class="btn btn-info" onclick="btnClick('add')">添加部门</button>
</form>
<h2 class="sub-header">部门信息列表</h2>
          <div class="table-responsive">
            <table id="department_table_list" class="table table-striped">
              <thead>
                <tr>
                  <th></th>
                  <th>部门名称</th>
                  <th>部门编码</th>
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
                    <input id="dpID" type="hidden" value=""/>
                    
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="txt_departmentname">部门名称</label>
                        <input type="text" id="id_department_name" name="txt_department_name" class="form-control" id="txt_departmentname" placeholder="部门名称">
                    </div>
                    <div class="form-group">
                        <label for="txt_parentdepartment">英文编码</label>
                        <input type="text" id="id_department_en_name" name="txt_department_en_name" class="form-control" id="txt_parentdepartment" placeholder="上级部门">
                    </div>
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                    <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="department()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
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
	
	function btnClick(type,params){
		if(type){
			if(type == 'search'){
				search();
			}else if(type == 'add'){
				$("#myModalLabel").text('新增');
				$("#dpID").val('');
				$("#id_department_name").val('');
    			$("#id_department_en_name").val('');
				$("#myModal").modal('show');
			}else if(type == 'editor'){
				editor(params);
			}else if(type == 'delete'){
				deletes(params);
			}
					
		}
	}
	
	function deletes(id){
		$.ajax({
            type: "POST",
            url: "controller",
            data: {param:"deleteDepartmentId",id:id},
            dataType: "json",
            success: function(res){
            	var result = res.result;
            	if('Y'==result){
					alert('删除成功');
            	}else{
            		var info = res.info;
            		if(info && info.length > 0){
    					alert(info);
            		}
            	}
            }
        });	
	}
	
	function editor(id){
		$.ajax({
            type: "POST",
            url: "controller",
            data: {param:"queryDepartmentId",id:id},
            dataType: "json",
            success: function(res){
            	var result = res.result;
            	if('Y'==result){
            		var data = res.data;
            		if(data && data.length >0){
            			var item = data[0];
            			$("#myModalLabel").text('更新');
            			$("#dpID").val(id);
            			$("#id_department_name").val(item.NAME);
            			$("#id_department_en_name").val(item.EN_NAME);
            			$("#myModal").modal('show');
            		}
            	}
            }
        });	
		
	}
	
	function search(){
		var searchInput = $("#searchInput").val();
		$.ajax({
            type: "POST",
            url: "controller",
            data: {param:"searchDepartmentLikeByName",searchInput:searchInput},
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
            				
            				
            				tbody = tbody + '<td><input type="radio" name="departmentID" value="'+item.ID+'"></td>';
            				
            				tbody = tbody + '<td>'+item.NAME+'</td>';
            				tbody = tbody + '<td>'+item.EN_NAME+'</td>';
            				tbody = tbody + '<td>'
            					+ '<p>'
            					+ '<button type="button" class="btn btn-xs btn-info"  onclick="btnClick(\'editor\',\''+item.ID+'\')">编辑</button>'
            					+'<button type="button" class="btn btn-xs btn-danger"   onclick="btnClick(\'delete\',\''+item.ID+'\')">删除</button>'
            					+'</p>'
            					+'</td>';
            				tbody += '</tr>'; 
            			}
            			
            			$("#department_table_list tbody").empty("");
            			
            			$("#department_table_list tbody").append(tbody);
            		}else{
            			$("#department_table_list tbody").empty("");
            		}
            	}
            }
        });	
	}
	
	function department(){

		var v_name = $("#id_department_name");
		var v_en_name = $("#id_department_en_name");
		if(!v_name){
			alert('部门名称不能为空！');
			return;
		}
		if(!v_en_name){
			alert('部门英文编码不能为空！');
			return;
		}
		var name = v_name.val();
		var en_name = v_en_name.val();
		if(name == ''){
			alert('部门名称不能为空！');
			return;
		}
		if(en_name == ''){
			alert('部门英文编码不能为空！');
			return;
		}
		
		var dpID = $("#dpID").val();
		if(dpID && dpID.length >0){
			$.ajax({
	            type: "POST",
	            url: "controller",
	            data: {param:"updateDepartment",name:name,en_name:en_name,dpID:dpID},
	            dataType: "json",
	            success: function(res){
	            	var result = res.result;
	            	if('Y'==result){
	            		alert('更新成功，请刷新页面');
	            	}
	            }
	        });
		}else{
			$.ajax({
	            type: "POST",
	            url: "controller",
	            data: {param:"addDepartment",name:name,en_name:en_name},
	            dataType: "json",
	            success: function(res){
	            	var result = res.result;
	            	if('Y'==result){
	            		alert('保存成功');
	            	}
	            }
	        });
			
		}
		
		
	}
	

</script>        
</body>
</html>