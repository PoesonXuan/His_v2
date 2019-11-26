<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/page/welcome.js"></script>
<link href="${pageContext.request.contextPath}/css/page/welcome.css" rel="stylesheet">
</head>
<body>
	<!--背景图片-->
	<div id="web_bg"></div>
	<!--其他代码 ... -->
</body>
<script type="text/javascript">

$(function(){
	$("#web_bg").attr("style","background:url('../../css/img/welcome.jpg') no-repeat;width:100%;height:100%;"); 
	
});
</script>
</html>