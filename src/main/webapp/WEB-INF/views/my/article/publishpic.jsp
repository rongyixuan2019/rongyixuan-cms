<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/cms.css" />

<title>图片集页面</title>
 
</head>
<body>
<br>
	<div class="container">
	<form id="form1" >
	
	 <button type="button" onclick="addCard()" class="btn btn-info">添加card</button>
	 <button type="button" onclick="publishPic()" class="btn btn-info">发布图片</button>
		<div class="form-group">
			<label for="title">标题</label> <input type="text" class="form-control"
				name="title" id="title">
		</div>
		<hr>

		<div id="mdiv" style="border: 1px solid red">
			<div id="card1" style="float: left">
				<div class="card" style="width: 15rem;">
					<div class="card-header">
						<label for="title">标题图片</label> <input type="file"
							class="form-control" name="files" id="file">
					</div>
					<div class="card-body">
						图片描述:
						<textarea rows="5" cols="" name="descr" style="width: 13rem;"></textarea>
					</div>
				</div>
			</div>
		</div>
	</form>


</div>

<script type="text/javascript">
function addCard(){
	$("#mdiv").append($("#card1").html());
}

//发布图片集
function publishPic(){
	 var formData = new FormData($( "#form1" )[0]);
	 $.ajax({
		 type:"post",
		 url:"/my/publishpic",
		 data : formData,
		// 告诉jQuery不要去处理发送的数据
		 processData : false,
		 // 告诉jQuery不要去设置Content-Type请求头
		 contentType : false,
		 success:function(flag){
			 if(flag){
				 alert("发布成功");
				 location.href="/my"
			 }else{
				 alert("发布失败,试试重新登录后再发布")
			 }
		 }
		 
	 })
	
	
}
</script>
</body>
</html>