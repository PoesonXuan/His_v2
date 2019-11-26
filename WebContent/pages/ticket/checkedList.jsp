<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单列表</title>


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
			<h3 class="panel-title">查询条件</h3>
		</div>
		<div class="panel-body">
			<div class="container">
				<form action="#">
					<div class="form-group">
						<label class="col-sm-2 control-label">订单号</label>
						<div class="col-sm-4 ">
							<input id="ticketCode" class="form-control"  type="text"
								placeholder="请输入订单号" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">订单名称</label>
						<div class="col-sm-4 ">
							<input  id="ticketName" class="form-control"  type="text"
								placeholder="请输入订单号（支持模糊查询）" />
						</div>
					</div>
					
					<br/>
					<br/>
					<div class="form-group">
						<div class="col-sm-12">
							<input class="btn btn-default pull-left col-sm-3" type="button" onclick="getCheckList()" value="查询" />
							&nbsp;&nbsp;<input class="btn btn-default pull-left col-sm-3" type="reset" value="重置" />
						</div>
					</div>
				</form>
				


			</div>
			<!-- /container -->
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">待审核工单查询结果</h3>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table id="ticket_table_list" class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>订单编码</th>
							<th>订单名称</th>
							<th>发单人</th>
							<th>审核结果</th>
							<th>所属部门</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						
						
					</tbody>
				</table>
				
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
		//alert("ticketList");
		reset();

		getCheckList();
		
	});
	
	function reset(){
		$('#ticketCode').val('');
		$('#ticketName').val('');
	}
	
	function getCheckList(){

		var ticketCode = $('#ticketCode').val();
		var ticketName = $('#ticketName').val();

		$.ajax({
            type: "POST",
            url: "controller",
            data: {param:"getCheckedList",ticketCode:ticketCode,ticketName:ticketName},
            dataType: "json",
            success: function(res){
            	var result = res.result;
            	if('Y'==result){
            		var data = res.data;
            		console.log(data);
            		
            		var tbody = '';
            		
            		for(var i=0;i<data.length;i++){
        				tbody += '<tr>'; 
        				var item = data[i];
        				
        				
        				tbody = tbody + '<td><input type="radio" name="ticketID" value="'+item.ID+'"></td>';
        				
        				tbody = tbody + '<td>'+item.code+'</td>';
        				tbody = tbody + '<td>'+item.name+'</td>';
        				tbody = tbody + '<td>'+item.startor+'</td>';
        				tbody = tbody + '<td>'+item.result+'</td>';
        				tbody = tbody + '<td>'+item.department+'</td>';
        				tbody = tbody + '<td>'
        					+ '<p>'
        					+ '<button type="button" class="btn btn-xs btn-info"  onclick="btnClick(\'detail\',\''+item.id+'\')">详情</button>'
        					+ '</p>'
        					+'</td>';
        				tbody += '</tr>'; 
        			}
            		
            		$("#ticket_table_list tbody").empty("");
        			
        			$("#ticket_table_list tbody").append(tbody);
            	}
            }
        });	
		
	}
	
	function btnClick(type,id){
		if('detail' == type ){
			window.location.href = 'controller?param=ticketDetail&type=checked&id='+id;
		}
	}

</script>

</body>
</html>