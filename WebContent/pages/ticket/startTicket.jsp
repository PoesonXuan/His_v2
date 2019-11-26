<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发起订单</title>


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



	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">发起新的订单</h3>
		</div>
		<div class="panel-body">
			<div class="container">
				<!-- <form action="#"> -->
					<div class="form-group">
						<label class="col-sm-2 control-label">订单号</label>
						<div class="col-sm-4 ">
							<input id="codeID" class="form-control" type="text"
								placeholder="订单号" disabled="disabled" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">订单名称</label>
						<div class="col-sm-4 ">
							<input id="startDateID" class="form-control" type="text"
								placeholder="订单名称" />
						</div>
					</div>
					<br /> <br />
					<div class="form-group">
						<label class="col-sm-2 control-label">订单状态</label>
						<div class="col-sm-4">
							<select id="disabledSelect" class="form-control">
								<option value=""></option>
								<option value="0">发起</option>
							</select>
						</div>
					</div>

					<br /> <br />
					

					<div class="form-group">
						<label class="col-sm-2 control-label">订单描述</label>
						<div class="col-sm-10 ">
							<textarea id="desc" class="form-control" rows="3" name="textarea"
								placeholder="说点什么吧">
							</textarea>
							<!-- <textarea id="rea"  ></textarea> -->
						</div>
					</div>

					<br /> <br /> <br /> <br />
					<div class="form-group">
						<label class="col-sm-2 control-label">所属部门</label>
						<div class="col-sm-10 ">
							<input id="relateUserID" class="form-control" type="text"
								placeholder="部门名" />
							<input id="userHiddenID"  type="hidden" value="" />
						</div>

						<br /> <br />
						<div class="pull-right col-sm-10"
							style="border: 2px solid #408080; border-radius: 5px;">
							<table id="department_table_list" class="table table-striped ">
								<thead>
									<tr>
									  <th>部门名称</th>
					                  <th>部门编码</th>
					                  <th>操作</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
							<br /> <br />
						</div>

					</div>

					<br /> <br /> <br /> <br />
					<div class="form-group" >
						<div class="col-sm-12" style="margin-top: 1em;">
							<input class="btn btn-default pull-left col-sm-3" type="button"
								value="提交" onclick="submit()" /> &nbsp;&nbsp;<input
								class="btn btn-default pull-left col-sm-3" type="button"
								value="重置" />
						</div>
					</div>
				<!-- </form> -->



			</div>
			<!-- /container -->
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
		$(function() {
			//alert("ticketList");

			$("#relateUserID").bind("input propertychange", function(event) {
				console.log($("#relateUserID").val());
				var searchInput = $("#relateUserID").val();
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
		            				
		            				
		            				
		            				tbody = tbody + '<td>'+item.NAME+'</td>';
		            				tbody = tbody + '<td>'+item.EN_NAME+'</td>';
		            				tbody = tbody + '<td>'
		            					+ '<p>'
		            					+ '<button type="button" class="btn btn-xs btn-info"  onclick="btnClick(\'select\',\''+item.ID+'\')">选取</button>'
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
				
				
			
			});
			getCode();
		});

		function getCode() {
			$.ajax({
	            type: "POST",
	            url: "controller",
	            data: {param:"getTicketCode"},
	            dataType: "json",
	            success: function(res){
	            	var result = res.result;
	            	if('Y'==result){
	            		var info = res.info;
						$('#codeID').val(info);
	            	}
	            }
	        });	
		}
		function getDate() {
			$.ajax({
				type : "POST",
				url : "controller",
				data : {
					code : "req",
					reqCode : "1002"
				},
				dataType : "json",
				success : function(res) {
					var result = res.result;
					if (result && (result == '101' || result == 101)) {
						$("#startDateID").val(res.data);
					}
				},
				error : function(res) {
					alert("error");

				}
			});
		}
		
		function btnClick(type,id){
			if('select'== type){
				select(id);
			}
		}
		
		function select(id){
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
	            			$("#relateUserID").val(item.NAME);
	            			$("#userHiddenID").val(id);
	            			alert('选择成功');
	            		}
	            	}
	            }
	        });	
			
		}
		
		function submit(){
			var code = $('#codeID').val();
			var name = $('#startDateID').val();
			var status = $('#disabledSelect').val();
			var desc = $('#desc').val();
			var departmentName = $('#relateUserID').val();
			var departmentID = $('#userHiddenID').val();

			if(!code || code == ''){
				alert('订单号不能为空');
				return ;
			}
			if(!name || name == ''){
				alert('订单名称不能为空');
				return ;
			}
			if(!status || status == ''){
				alert('订单状态不能为空');
				return ;
			}
			if(!desc || desc == ''){
				alert('订单描述不能为空');
				return ;
			}
			if(!departmentID || departmentID == ''){
				alert('部门信息不能为空');
				return ;
			}
			
			$.ajax({
	            type: "POST",
	            url: "controller",
	            data: {
	            	param:"startTicket",
	            	code:code,
	            	name:name,
	            	status:status,
	            	desc:desc,
	            	departmentName:departmentName,
	            	departmentID:departmentID
	            	},
	            dataType: "json",
	            success: function(res){
	            	var result = res.result;
	            	var info = '';
	            	if('Y'==result){
	            		info = res.info;
	            	}else{
	            		info = res.info;
	            	}
	            	if(info && info != ''){
	            		alert(info);
	            	}
	            }
	        });	
			
			
		};
		
		function validate(){
			
		};
	</script>

</body>
</html>