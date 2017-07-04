<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
	<meta charset="UTF-8" />
	<!-- meta使用viewport以确保页面可自由缩放 -->
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="../static/css/bootstrap.min.css" />
	<script src="../static/js/jquery-1.12.4.min.js" ></script>
	<script src="../static/js/bootstrap.min.js" ></script>
	<script src="../static/js/jquery.cookie.js" ></script>
<title>ucenterIndex</title>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="page-header">
				<h1>
					IJJG <small>这是测试</small>
				</h1>
			</div>
		</div>
		<div class="col-md-12 column">
			<dl id="userInfo">
			</dl>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
getUserInfo();
function getUserInfo(){
	var token = $.cookie('ijjg_token');
	console.info("token="+token);
	if(token == null || token == ''){
		alert("登录失效,请重新登录");
		turnToLoginView
		return null;
	}
	$.ajax({
		type:"POST",
		url:"/ijjg/user/userInfoGet",
		async: false,
		data:"json="+JSON.stringify({"token":token}),
		success:function(data){
			if(data.code == "1"){
				console.info(data.object)
				var htmlUser = "<dt>用户名<dt><dd>"+data.object.username+"</dd>";
				var htmlLogin = "<dt>最后登录时间<dt><dd>"+data.object.lastlogintime+"</dd>";
				var htmlMobile = "<dt>手机号<dt><dd>"+data.object.usermobile+"</dd>";
				$("#userInfo").append(htmlUser);
				$("#userInfo").append(htmlLogin);
				$("#userInfo").append(htmlMobile);
			}else{
				alert(data.message);
				turnToLoginView()
			}
		},
	});
}

function turnToLoginView(){
	var action = "/ijjg/view/login";
	location.href=action;
}

</script>
</html>