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
	<script src="../static/js/jquery.cookie.js" ></script>
	<script src="../static/js/bootstrap.min.js" ></script>
<title>sign</title>
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
			<form id="singIn" action="#" class="form-horizontal" role="form" onsubmit="return saveAndPost();">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">帐号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="username" />
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							 <label><input type="checkbox" id="rememberMe"/>记住我</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <button type="submit" class="btn btn-default" id="sub">登录</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
//点击提交事件
function saveAndPost(){
	var username = $("#username").val();
	var password = $("#password").val();
	var rememberMe = $("#rememberMe").val();
	console.log(username+"|"+password+"|"+rememberMe);
	if(subValidate(username, password)){
		var token = submit(username, password, rememberMe);
	}
	console.log("token="+token);
	if(token != ''){
		gotoIndex(token);
	}
	return false;
}

//输入数据检验
function subValidate(username,password){
	if(!username){
		flag = false;
		alert("请正确输入用户名");
		return false;
	}
	if(!password){
		flag = false;
		alert("请正确输入密码");
		return false;
	}
	return true;
}

//成功后跳转页面
function gotoIndex(token){
	var action = "/ijjg/view/ucenterIndex";
	location.href=action;
}

//提交登录数据
function submit(username,password,rememberMe){
	console.log(username+"|"+password+"|"+rememberMe);
	var token = '';
	$.ajax({
		type:"POST",
		url:"/ijjg/user/login",
		async: false,
		data:"json="+JSON.stringify({"username":username,"password":password,"rememberMe":rememberMe}),
		success:function(data){
			if(data.code == "1"){
				$.cookie('ijjg_token',data.token,{expires:7,path:'/'});
				token = $.cookie('ijjg_token');
			}
		},
	});
	return token;
}


</script>
</html>