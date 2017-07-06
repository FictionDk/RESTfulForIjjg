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
			<div class="alert alert-success alert-dismissable" id="notice" style="display:none">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				<a href="#" class="alert-link" id="msg"></a>
				</div>
			<div class="list-group">
				<a href="#" class="list-group-item active">个人中心</a>
				<div class="list-group-item" id="usename">
					用户名:
				</div>
				<div class="list-group-item" id="mobile">
					手机号:
				</div>
				<div class="list-group-item" id="time">
					最后登录时间:
				</div>
				<a class="list-group-item active"> <span id="count" class="badge">0</span>未读私信</a>
			</div>
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
				var htmlUser = data.object.user.userName;
				var htmlLogin = data.object.user.lastLoginTime?data.object.user.lastLoginTime:data.object.user.createTime;
				var htmlMobile = data.object.user.userMobile;
				var htmlCount = data.object.messageCount;
				$("#usename").append(htmlUser);
				$("#time").append(htmlLogin);
				$("#mobile").append(htmlMobile);
				$("#count").html(htmlCount);
				if(htmlCount > 0){
					showCountView(data.object.messageList);
				}
			}else{
				alert(data.message);
				turnToLoginView()
			}
		},
	});
}

function showCountView(msgList){
	console.info(msgList[0]);
	if(msgList[0]['alreadyRead'] == 0){
		$("#notice").show();
		$("#msg").html(msgList[0]['message']+"<br>"+msgList[0]['msgTime']);
	}
}

function turnToLoginView(){
	var action = "/ijjg/view/login";
	location.href=action;
}

</script>
</html>