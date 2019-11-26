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
							<input class="form-control"  type="text"
								placeholder="请输入订单号（支持模糊查询）" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">环节名称</label>
						<div class="col-sm-4"> 
							<select id="disabledSelect" class="form-control"> 
								<option value=""></option> 
								<option value="0">学生</option> 
								<option value="1">老师</option> 
								<option value="2">管理员</option> 
							</select> 
						</div> 
					</div>
					<br/>
					<br/>
					<div class="form-group">
						<label class="col-sm-2 control-label">订单号</label>
						<div class="col-sm-4 ">
							<input class="form-control"  type="text"
								placeholder="请输入订单号（支持模糊查询）" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">环节名称</label>
						<div class="col-sm-4"> 
							<select id="disabledSelect" class="form-control"> 
								<option value=""></option> 
								<option value="0">学生</option> 
								<option value="1">老师</option> 
								<option value="2">管理员</option> 
							</select> 
						</div> 
					</div>
					<br/>
					<br/>
					<div class="form-group">
						<div class="col-sm-12">
							<input class="btn btn-default pull-left col-sm-3" type="button" value="查询" />
							&nbsp;&nbsp;<input class="btn btn-default pull-left col-sm-3" type="button" value="重置" />
						</div>
					</div>
				</form>
				


			</div>
			<!-- /container -->
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">归档工单查询结果</h3>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>用户名</th>
							<th>登录名</th>
							<th>年龄</th>
							<th>性别</th>
							<th>用户类别</th>
							<th>创建日期</th>
							<th>修改日期</th>
							<th>最近修改人</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>

							<td><input type="radio" name="userID" value="1,001"></td>
							<td>Lorem</td>
							<td>ipsum</td>
							<td>dolor</td>
							<td>sit</td>
							<td>1,001</td>
							<td>Lorem</td>
							<td>ipsum</td>
							<td>dolor</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,002"></td>
							<td>amet</td>
							<td>consectetur</td>
							<td>adipiscing</td>
							<td>elit</td>
							<td>1,002</td>
							<td>amet</td>
							<td>consectetur</td>
							<td>adipiscing</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,003"></td>
							<td>Integer</td>
							<td>nec</td>
							<td>odio</td>
							<td>Praesent</td>
							<td>1,003</td>
							<td>Integer</td>
							<td>nec</td>
							<td>odio</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,003"></td>
							<td>libero</td>
							<td>Sed</td>
							<td>cursus</td>
							<td>ante</td>
							<td>1,003</td>
							<td>libero</td>
							<td>Sed</td>
							<td>cursus</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,004"></td>
							<td>dapibus</td>
							<td>diam</td>
							<td>Sed</td>
							<td>nisi</td>
							<td>1,004</td>
							<td>dapibus</td>
							<td>diam</td>
							<td>Sed</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,005"></td>
							<td>Nulla</td>
							<td>quis</td>
							<td>sem</td>
							<td>at</td>
							<td>1,005</td>
							<td>Nulla</td>
							<td>quis</td>
							<td>sem</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,006"></td>
							<td>nibh</td>
							<td>elementum</td>
							<td>imperdiet</td>
							<td>Duis</td>
							<td>1,006</td>
							<td>nibh</td>
							<td>elementum</td>
							<td>imperdiet</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,007"></td>
							<td>sagittis</td>
							<td>ipsum</td>
							<td>Praesent</td>
							<td>mauris</td>
							<td>1,007</td>
							<td>sagittis</td>
							<td>ipsum</td>
							<td>Praesent</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,008"></td>
							<td>Fusce</td>
							<td>nec</td>
							<td>tellus</td>
							<td>sed</td>
							<td>1,008</td>
							<td>Fusce</td>
							<td>nec</td>
							<td>tellus</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,009"></td>
							<td>augue</td>
							<td>semper</td>
							<td>porta</td>
							<td>Mauris</td>
							<td>1,009</td>
							<td>augue</td>
							<td>semper</td>
							<td>porta</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,010"></td>
							<td>massa</td>
							<td>Vestibulum</td>
							<td>lacinia</td>
							<td>arcu</td>
							<td>1,010</td>
							<td>massa</td>
							<td>Vestibulum</td>
							<td>lacinia</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,011"></td>
							<td>eget</td>
							<td>nulla</td>
							<td>Class</td>
							<td>aptent</td>
							<td>1,011</td>
							<td>eget</td>
							<td>nulla</td>
							<td>Class</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,012"></td>
							<td>taciti</td>
							<td>sociosqu</td>
							<td>ad</td>
							<td>litora</td>
							<td>1,012</td>
							<td>taciti</td>
							<td>sociosqu</td>
							<td>ad</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,013"></td>
							<td>torquent</td>
							<td>per</td>
							<td>conubia</td>
							<td>nostra</td>
							<td>1,013</td>
							<td>torquent</td>
							<td>per</td>
							<td>conubia</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,014"></td>
							<td>per</td>
							<td>inceptos</td>
							<td>himenaeos</td>
							<td>Curabitur</td>
							<td>1,014</td>
							<td>per</td>
							<td>inceptos</td>
							<td>himenaeos</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>

								</p>
							</td>
						</tr>
						<tr>
							<td><input type="radio" name="userID" value="1,015"></td>
							<td>sodales</td>
							<td>ligula</td>
							<td>in</td>
							<td>libero</td>
							<td>1,015</td>
							<td>sodales</td>
							<td>ligula</td>
							<td>in</td>
							<td>
								<p>
									<button type="button" class="btn btn-xs btn-info"
										onclick="btnClick('detail','1')">详情</button>
								</p>
							</td>
						</tr>
					</tbody>
				</table>
				<p>
					<button type="button" class="btn btn-xs btn-default"><<</button>
					<button type="button" class="btn btn-xs btn-default"><</button>
					<button type="button" class="btn btn-xs btn-default">1</button>
					<button type="button" class="btn btn-xs btn-info">2</button>
					<button type="button" class="btn btn-xs btn-default">3</button>
					<button type="button" class="btn btn-xs btn-default">4</button>
					<button type="button" class="btn btn-xs btn-default">5</button>
					<button type="button" class="btn btn-xs btn-default">6</button>
					<button type="button" class="btn btn-xs btn-default">7</button>
					<button type="button" class="btn btn-xs btn-default">></button>
					<button type="button" class="btn btn-xs btn-default">>></button>
					<input type="text" value="共100页,当前第2页" disabled="disabled" />
				</p>
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
		
		
	});
	
	

</script>

</body>
</html>