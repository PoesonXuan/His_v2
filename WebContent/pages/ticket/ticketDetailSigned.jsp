<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>


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
			<h3 class="panel-title">订单详情</h3>
		</div>
		<div class="panel-body">
			<div class="container">
				<!-- <form action="#"> -->
					<div class="form-group">
						<label class="col-sm-2 control-label">订单号</label>
						<div class="col-sm-4 ">
							<input id="codeID" class="form-control" type="text"
								placeholder="订单号" disabled="disabled" value="${ticket.code }" />
							<input id="ticketID" class="form-control" type="text"
								placeholder="订单ID" value="${ticket.status }" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">订单名称</label>
						<div class="col-sm-4 ">
							<input id="startDateID" class="form-control" type="text"
								placeholder="订单名称"  value="${ticket.name }" />
						</div>
					</div>
					<br /> <br />
					<div class="form-group">
						<label class="col-sm-2 control-label">工单审批</label>
						<div class="col-sm-4">
							<select id="disabledSelect" class="form-control">
								
							</select>
						</div>
					</div>

					<br /> <br />
					

					<div class="form-group">
						<label class="col-sm-2 control-label">订单描述</label>
						<div class="col-sm-10 ">
							<textarea id="desc" class="form-control" rows="3" name="textarea"
								placeholder="说点什么吧">${ticket.description}

							</textarea>
							<!-- <textarea id="rea"  ></textarea> -->
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">签收详情</label>
						<div class="col-sm-10 ">
							<textarea id="opinion" class="form-control" rows="3" name="textarea"
								placeholder="说点什么吧">
								${ticket.dealer_desc}
							</textarea>
							<!-- <textarea id="rea"  ></textarea> -->
						</div>
					</div>

					<br /> <br /> <br /> <br />
					<div class="form-group">
						<label class="col-sm-2 control-label">所属部门</label>
						<div class="col-sm-10 ">
							<input id="relateUserID" class="form-control" type="text"
								placeholder="部门名"   value="${ticket.department.name }"/>
						</div>

						<br /> <br />
						

					</div>

					<br /> <br /> <br /> <br />
					<div class="form-group" >
						<!-- <div class="col-sm-12" style="margin-top: 1em;">
							<input class="btn btn-default pull-left col-sm-3" type="button"
								value="提交" onclick="submit()" /> &nbsp;&nbsp;
						</div> -->
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
			$("#ticketID").hide();
			var status = $("#ticketID").val();
			 
			var options = '';
			
			if(status && status =='3'){
				options = '<option value="3">签收</option>';
			}
			
			$('#disabledSelect').html(options);
			
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
			var ticketID = $('#ticketID').val();
			var opinion = $('#opinion').val();
			var result = $('#disabledSelect').val();
			

			if(!result || result == ''){
				alert('审批结果不能为空');
				return ;
			}
			if(!opinion || opinion == ''){
				alert('审批意见不能为空');
				return ;
			}
			
			$.ajax({
	            type: "POST",
	            url: "controller",
	            data: {
	            	param:"verifyTicket",
	            	id:ticketID,
	            	result:result,
	            	opinion:opinion
	            },
	            dataType: "json",
	            success: function(res){
	            	var result = res.result;
	            	var info = '';
	            	if('Y'==result){
	            		info = res.info;
	            		if(info && info != ''){
		            		alert(info);
		            	}
	            		window.location.href = "controller?param=pages&index=302";
	            	}else{
	            		info = res.info;
	            		if(info && info != ''){
		            		alert(info);
		            	}
	            	}
	            	
	            }
	        });	
			
			
		};
		
		function validate(){
			
		};
	</script>

</body>
</html>