<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图片集详情${article.title }</title>


</head>
<body>
	<button type="button" class="btn btn-info" onclick="back()">返回列表</button>
	<!-- top -->
	<div>
		<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
	</div>
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
				<div>
				     	<div id="carouselExampleCaptions" class="carousel slide"
								data-ride="carousel">
								<ol class="carousel-indicators">
								 <c:forEach items="${list}" var="a" varStatus="i">
									<li data-target="#carouselExampleCaptions" data-slide-to="${i.index }"
										class="${i.index==0?"active":"" }"></li>
								 </c:forEach>
								</ol>
								<div class="carousel-inner">
								
								  <c:forEach items="${list}" var="a" varStatus="i">
								  <!-- 遍历图片 -->
									<div class="carousel-item ${i.index==0?"active":"" }">
										<img src="/pic/${a.url}" class="d-block w-100" alt="..."
										width="200px" height="450px">
										<div class="carousel-caption d-none d-md-block">
											<h5>${a.descr }</h5>

										</div>
										
									</div>
									</c:forEach>
								</div>
								<a class="carousel-control-prev" href="#carouselExampleCaptions"
									role="button" data-slide="prev"> <span
									class="carousel-control-prev-icon" aria-hidden="true"></span> <span
									class="sr-only">Previous</span>
								</a> <a class="carousel-control-next"
									href="#carouselExampleCaptions" role="button" data-slide="next">
									<span class="carousel-control-next-icon" aria-hidden="true"></span>
									<span class="sr-only">Next</span>
								</a>
							</div>
				
				
				
				</div>
			</dd>
		</dl>


	</div>


<div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

	</div>
	<script type="text/javascript">
	 
	 function back(){
		location.href="/"
	 }
	
	</script>
</body>
</html>