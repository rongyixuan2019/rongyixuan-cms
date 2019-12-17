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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" /> 
<title>管理员查询文章页面</title>
 
</head>
<body>

<script type="text/javascript">
	function query() {
		//在中间区域加载用户页面
		$("#center").load("/admin/article/articles?title=" + $("[name='title']").val());
	}
</script>
<div style="text-align: center">
		<dl>
			<dt>
			  <h2>${article.title }</h2>
			</dt>
			<hr>
			<dd>
			<button type="button" class="btn btn-success" onclick="update(${article.id},1)">同意</button>
			<button type="button" class="btn btn-danger" onclick="update(${article.id},-1)">驳回</button>
			<button type="button" class="btn btn-info" onclick="back()">返回列表</button>
			
			</dd>
			<dd><fmt:formatDate value="${aritcle.updated }" pattern="yyyy-MM-dd HH:mm:ss"/> </dd>
			<dd>${article.content }</dd>

		</dl>

	</div>
	
	<script type="text/javascript">
	 function update(id,status){
		 $.post("/admin/article/update",{id:id,status:status},function(flag){
			 if(flag){
				 alert("操作成功") ;
				  $("#center").load("/admin/article/articles")
			 }
		 })
		 
	 }
	 function back(){
		 $("#center").load("/admin/article/articles")
	 }
	
	</script>
</body>
</html>