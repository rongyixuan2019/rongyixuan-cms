<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />


<title>管理员后台主页面</title>

</head>
<body>
	

	<div class="container-fluid">
		<div class="row" style="margin-top: 2px; min-height: 50px;">
			<div class="col-md-12" style="background-color: #563d7c">
				<img alt="" src="/resource/images/logo.jpg" class="rounded-circle">
				<a class="navbar-brand mr-1" href="index.html">CMS系统后台</a>
				
				<c:choose>
					<%-- 登录显示用户菜单 --%>
					<c:when test="${sessionScope.admin != null}">
						<div class="btn-group dropleft"
							style="float: right; padding-top: 20px">
							<button type="button" class="btn btn-secondary dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">${sessionScope.admin.username}</button>
							<div class="dropdown-menu">
								<ul class="nav" style="left: -88px">
									
									<li class="nav-item"><a class="nav-link"
									href="/">前台列表</a></li>
									<li class="nav-item"><a class="nav-link"
									href="/passport/login">登录页面</a></li>
									<li class="nav-item"><a class="nav-link"
										href="/passport/reg">注销</a></li>

								</ul>
							</div>
						</div>
					</c:when>

				</c:choose>



			</div>
		</div>
		<hr>
		<div class="row" style="margin-top: 5px; min-height: 500px;">
			<div class="col-md-2"
				style="padding-top: 20px; background-color: #eceaea;">
				<ul class="navbar">
					<li class="navbar-brand"><a class="nav-link"
						href="/admin/index">&nbsp;后台首页</a></li>
					<li class="navbar-brand"><a class="nav-link" href="#"
						data="/admin/user/users">&nbsp;用户管理</a></li>
					<li class="navbar-brand"><a class="nav-link" href="#"
						data="/admin/article/articles">&nbsp;文章管理</a></li>
					<li class="navbar-brand"><a class="nav-link" href="#"
						data="/admin/links/selects" >&nbsp;友情链接</a></li>
					<li class="navbar-brand"><a class="nav-link" href="#">&nbsp;分类管理</a></li>
					<li class="navbar-brand"><a class="nav-link " href="#">&nbsp;系统设置</a></li>
				</ul>


			</div>

			<div class="col-md-10 split" id="center">
				<div align="center">
					<img alt="" src="/resource/pic/u.jpg"
						class="rounded-circle" style="height: 450px">
				</div>

			</div>


		</div>

	</div>
	<script type="text/javascript">
		//文档就绪函数
		$(function() {

			//为左侧菜单添加点击事件
			$(".nav-link").click(function() {
				var li = $("ul li a");
				//先移除所有的list-group-item-info样式
				li.removeClass("list-group-item-danger");
				//为左侧菜单添加动态点击样式 
				$(this).addClass("list-group-item-danger");
				//获取点击URL
				var url = $(this).attr("data");
				//在当前页面的中间区域执行url
				$("#center").load(url);
			})

		})
	</script>
</body>
</html>