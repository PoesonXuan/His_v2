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
			<h3 class="panel-title">值班列表</h3>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table id="duty_table_list" class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>名称</th>
							<th>日期</th>
							<th>创建人</th>
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
                    <h4 class="modal-title" id="myModalLabel">值班列表详情</h4>
                    <input id="dpID" type="hidden" value=""/>
                    
                </div>
                <div class="modal-body">

                    <div class="table-responsive">
						<table id="duty_detail_table_list" class="table table-striped">
							<thead>
								<tr>
									<th></th>
									<th>值班人</th>
									<th>值班日期</th>
									<!-- <th>操作</th> -->
								</tr>
							</thead>
							<tbody>
								
								
							</tbody>
						</table>
						
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
		
		getDutyList();
	});
	
	function getDutyList(){
		$.ajax({
            type: "POST",
            url: "controller",
            data: {param:"getDutyList"},
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
        				
        				tbody = tbody + '<td>'+item.name+'</td>';
        				tbody = tbody + '<td>'+item.createDate+'</td>';
        				tbody = tbody + '<td>'+item.creator.name+'</td>';
        				tbody = tbody + '<td>'
        					+ '<p>'
        					+ '<button type="button" class="btn btn-xs btn-info"  onclick="btnClick(\'details\',\''+item.id+'\')">详情</button>'
        					+ '</p>'
        					+'</td>';
        				tbody += '</tr>'; 
        			} 
            		
            		$("#duty_table_list tbody").empty("");
        			
        			$("#duty_table_list tbody").append(tbody);
        			
        			
            	}
            }
        });	
	};
	
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
        				
        				
        				tbody = tbody + '<td><input type="radio" name="ticketID" value="'+item.id+'"></td>';
        				
        				tbody = tbody + '<td>'+item.dutyDate+'</td>';
        				tbody = tbody + '<td>'+item.dutor.name+'</td>';
        				/* tbody = tbody + '<td>'
        					+ '<p>'
        					+ '<button type="button" class="btn btn-xs btn-info"  onclick="btnClick(\'detail\',\''+item.id+'\')">详情</button>'
        					+ '</p>'
        					+'</td>'; */
        				tbody += '</tr>'; 
        			}
            		
            		$("#ticket_table_list tbody").empty("");
        			
        			$("#ticket_table_list tbody").append(tbody);
        			
        			
            	}
            }
        });	
		
	}
	
	
	function detail(id){
		$.ajax({
            type: "POST",
            url: "controller",
            data: {
            	param:"dutyListDetail",dutyId:id
            },
            dataType: "json",
            success: function(res){
            	var result = res.result;
            	if('Y'==result){debugger;
            		var data = res.data;
            		console.log(data);
            		
            		resolveData2(data);
            		
            		
            	}
            	
            	var info = res.info;
        		if(info && info != ''){
        			alert(info);
        		}
            }
        });	
	};
	function resolveData2(data){
		if(data){
			var tbody = '';
			for(var i=0;i<data.length;i++){
				tbody += '<tr>'; 
				var item = data[i];
				tbody = tbody + '<td><input type="radio" name="ticketID" value="'+item.id+'"></td>';
				tbody = tbody + '<td>'+item.dutor.name+'</td>';
				tbody = tbody + '<td>'+item.dutyDate+'</td>';
				/* tbody = tbody + '<td>'
					+ '<p>'
					+ '<button type="button" class="btn btn-xs btn-info"  onclick="btnClick(\'update\',\''+item.seq+'\')">详情</button>'
					+ '</p>'
					+'</td>'; */
				tbody += '</tr>'; 
			}
			
			$("#duty_detail_table_list tbody").empty("");
			
			$("#duty_detail_table_list tbody").append(tbody); 
			
			$('#myModal').modal('show');
		}
	};
	
	
	function btnClick(type,id){debugger;
		if('details' == type ){
			detail(id);
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