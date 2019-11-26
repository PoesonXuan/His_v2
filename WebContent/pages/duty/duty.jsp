<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>值日管理</title>


<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="${pageContext.request.contextPath}/css/bootstrap/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/bootstrap/dashboard.css" rel="stylesheet">

<!-- Bootstrap date CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap/date/bootstrap-datepicker3.css"
	rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/bootstrap/date/htmleaf-demo.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${pageContext.request.contextPath}/js/bootstrap/ie-emulation-modes-warning.js"></script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">查询条件</h3>
		</div>
		<div class="panel-body">
			<div class="container">
				<form action="#">
				
					<div  class="form-group">
						<div class="col-md-5">
							<h3>请选择开始日期：</h3>
							<div class="input-group date datepicker">
							    <input id="startDate" type="text" class="form-control">
							    <div class="input-group-addon">
							        <span class="glyphicon glyphicon-th "></span>
							    </div>
							</div>
						</div>
						<div class="col-md-2"></div>
						<div class="col-md-5">
							<h3>请选择开始日期：</h3>
							<div class="input-group date datepicker">
							    <input id="endDate"  type="text" class="form-control">
							    <div class="input-group-addon">
							        <span class="glyphicon glyphicon-th "></span>
							    </div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-12">
							<input class="btn btn-default pull-left col-sm-3" type="button" value="一键生成值日表" onclick="btnClick('create')" />
							<input class="btn btn-default pull-left col-sm-3" type="button" value="一键发布值日表" onclick="btnClick('submit')" />
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
				<table id="duty_table_list" class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>值班人</th>
							<th>值班日期</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						
						
					</tbody>
				</table>
				
			</div>
		</div>
	</div>

<!-- 修改窗口 start -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">排班详情</h4>
                    <input id="dpID" type="hidden" value=""/>
                    
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="txt_duty_date">排班日期</label>
                        <input type="text" id="id_duty_date" name="txt_duty_date" class="form-control" id="txt_departmentname" placeholder="部门名称">
                    </div>
                    <div class="form-group">
                        <label for="txt_dutor_name">值班人</label>
                        <input type="text" id="id_dutor_name" name="txt_dutor_name" class="form-control" id="txt_parentdepartment" placeholder="上级部门">
                    </div>
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                   <!--  <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="department()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button> -->
                </div>
            </div>
        </div>
    </div>
<!-- 修改窗口 end -->


	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="${pageContext.request.contextPath}/js/bootstrap/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="${pageContext.request.contextPath}/js/bootstrap/ie10-viewport-bug-workaround.js"></script>
	
	<!-- bootstrap date -->
	<script src="${pageContext.request.contextPath}/js/bootstrap/date/bootstrap-datepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/date/bootstrap-datepicker.zh-CN.min.js"></script>
	
	<script type="text/javascript">
	$(function(){
		//alert("ticketList");
		getCheckList();
		
		initDate();
		
	});
	
	function initDate(){
		$('.datepicker').datepicker({
			language: 'zh-CN'
		});
	}
	
	function getCheckList(){
		
		$.ajax({
            type: "POST",
            url: "controller",
            data: {param:"getSubmitList"},
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
			window.location.href = 'controller?param=ticketDetail&type=submit&id='+id;
		}else if('create' == type){
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			createDuty();
		}else if('update' == type){
			
			$.ajax({
	            type: "POST",
	            url: "controller",
	            data: {param:"getDutyById",id:id},
	            dataType: "json",
	            success: function(res){
	            	var result = res.result;
	            	if('Y'==result){
	            		var data = res.data;
	            		console.log(data);

	            		$('#id_duty_date').val(data.dutyDate);
	            		$('#id_dutor_name').val(data.dutor.name);
	            		
	            		$("#myModal").modal('show');
	            	}
	            	
	            	var info = res.info;
	        		if(info && info != ''){
	        			alert(info);
	        		}
	            	
	            }
	        });	
			
			
		}else if('submit' == type){
			
			$.ajax({
	            type: "POST",
	            url: "controller",
	            data: {param:"dutySubmit"},
	            dataType: "json",
	            success: function(res){
	            	var result = res.result;
	            	if('Y'==result){
	            		
	            	}
	            	
	            	var info = res.info;
	        		if(info && info != ''){
	        			alert(info);
	        		}
	            	
	            }
	        });	
		}
	}
	
	
	function createDuty(){
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		$.ajax({
            type: "POST",
            url: "controller",
            data: {
            	param:"createDuty",
            	startDate:startDate,
            	endDate:endDate
            },
            dataType: "json",
            success: function(res){
            	var result = res.result;
            	if('Y'==result){
            		var data = res.data;
            		console.log(data);
            		
            		resolveData(data);
            		
            		
            	}
            	
            	var info = res.info;
        		if(info && info != ''){
        			alert(info);
        		}
            }
        });	
		
	};

	function resolveData(data){
		if(data){
			var tbody = '';
			for(var i=0;i<data.length;i++){
				tbody += '<tr>'; 
				var item = data[i];
				tbody = tbody + '<td><input type="radio" name="ticketID" value="'+item.seq+'"></td>';
				tbody = tbody + '<td>'+item.dutor.name+'</td>';
				tbody = tbody + '<td>'+item.dutyDate+'</td>';
				tbody = tbody + '<td>'
					+ '<p>'
					+ '<button type="button" class="btn btn-xs btn-info"  onclick="btnClick(\'update\',\''+item.seq+'\')">详情</button>'
					+ '</p>'
					+'</td>';
				tbody += '</tr>'; 
			}
			
			$("#duty_table_list tbody").empty("");
			
			$("#duty_table_list tbody").append(tbody);
		}
	}
</script>

</body>
</html>