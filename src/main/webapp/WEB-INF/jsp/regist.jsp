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
	<script src="../static/js/md5.js" ></script>
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
					<label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="username" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">手机号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="mobileNumber" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="password" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">再输入一遍密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="password_agin" />
					</div>
				</div>
				<div id="showsystem" style="display:none">
					<input id="code" name="system" value="" />
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <button type="submit" class="btn btn-default" id="sub">注册</button>
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
	var mobile = $("#mobileNumber").val();
	var password = $("#password").val();
	var passwordAgain = $("#password_agin").val();
	var r = 0;
	var code = 0;
	var token = '';
	if(subValidate(username,mobile,password,passwordAgain)){
		r = getRegisterCode(username,mobile);
	}
	if(r == 1){
		code = fillCode();
	}
	if(code != 0){
		password = md5(password);
		var resultCode = submit(username,mobile,password,code);
		if(resultCode == 1){
			gotoIndex();
		}
	}
	return false;
}


function fillCode(){
	var str=prompt("请输入验证","");  
    if(!str){
        alert("请正确输入有效验证码");  
    }
    return str;
}

//输入数据检验
function subValidate(username,mobile,password,passwordAgain){
	console.info(password+"|"+passwordAgain);
	if(!username){
		alert("请正确输入用户名");
		return false;
	}
	if(!mobile){
		alert("请正确输入手机号");
		return false;
	}
	if(!password){
		alert("请正确输入密码");
		return false;
	}
	if(password != passwordAgain){
		alert("两遍密码不一致")
		return false;
	}
	return true;
}

//成功后跳转页面
function gotoIndex(){
	var action = "/ijjg/view/ucenterIndex";
	location.href=action;
}

//注册提交
function submit(username,mobile,password,code){
	var resultCode = 0;
	$.ajax({
		type:"POST",
		url:"/ijjg/user/registeAct",
		async: false,
		data:"json="+JSON.stringify({"username":username,"usermobile":mobile,"password":password,"code":code}),
		success:function(data){
			console.info(data);
			resultCode = data.code;
			if(data.code != "1"){
				alert(data.message);
			}
			$.cookie('ijjg_token',data.token,{expires:7,path:'/'});
		},
	});
	return resultCode;
}

//获取注册码
function getRegisterCode(username,mobile){
	var r = '';
	$.ajax({
		type:"POST",
		url:"/ijjg/user/getRegisteCode",
		async: false,
		data:"json="+JSON.stringify({"username":username,"usermobile":mobile}),
		success:function(data){
			console.info(data);
			if(data.code != "1"){
				alert(data.message);
			}
			r = data.code;
		},
	});
	return r;
}


</script>
</html>