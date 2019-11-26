/**
 * 
 */
var home = function(){
	
	var mainContent = function(){
		document.getElementById("content").src  = "pages/welcome/welcome.jsp";
	}
	
	var leftMenu = function(type){
		
		document.getElementById("content").src  = "controller?param=pages&index="+type;
		if(type == 101 || type == '101') {// 部门管理
			
			//document.getElementById("content").src  = "pages/department/department.jsp";
		}else if(type == 102 || type == '102') {// 部门管理
			//document.getElementById("content").src  = "pages/users/userInfo.jsp";
		}
	}
	
	var mainPage = function(type){
		window.location.href = '';
		
		if(type == 99 || type == '99') {// 超级管理员
			window.location.href = 'pages/main/mainRoot.jsp';
		}else if(type == 0 || type == '0') {// 非医务人员
			window.location.href = 'pages/main/mainOthers.jsp';
		}else if(type == 1 || type == '1') {// 医务人员
			window.location.href = 'pages/main/mainYiWu.jsp';
		}else if(type == 2 || type == '2') {// 维护人员
			window.location.href = 'pages/main/mainWeiHu.jsp';
		}else if(type == 3 || type == '3') {// 管理人员
			window.location.href = 'pages/main/mainAdmin.jsp';
		}else {
			window.location.href = 'pages/main/mainOthers.jsp';
		}
		
	}
	return {
		mainContent:mainContent,
		mainPage:mainPage,
		leftMenu:leftMenu
	};
	
}();