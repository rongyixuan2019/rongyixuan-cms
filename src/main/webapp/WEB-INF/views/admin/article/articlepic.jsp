<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图片集详情${article.title }</title>


</head>
<body>
	<div class="container">
		<dl>
			<!-- 标题 -->
			<dt>
				<h2 align="center">
					<strong>${title }</strong>
				</h2>
			</dt>
			<hr>
			<dd>
				<button type="button" class="btn btn-success" onclick="update(${article.id},1)">同意</button>
				<button type="button" class="btn btn-danger" onclick="update(${article.id},-1)">驳回</button>
				<button type="button" class="btn btn-info" onclick="back()">返回列表</button>
			</dd>
			<dd>
				<div>
					<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<c:forEach items="${list}" var="a" varStatus="i">
								<li data-target="#carouselExampleCaptions" data-slide-to="${i.index }" class="${i.index==0?"active":"" }"></li>
							</c:forEach>
						</ol>
						<div class="carousel-inner">

							<c:forEach items="${list}" var="a" varStatus="i">

								<div class="carousel-item ${i.index==0?"active":"" }">
									<img src="/pic/${a.url}" class="d-block w-100" alt="..." width="200px" height="450px">
									<div class="carousel-caption d-none d-md-block">
										<h5>${a.descr }</h5>
									</div>
								</div>
							</c:forEach>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</dd>
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