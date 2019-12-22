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
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />

<title>文章详情页面</title>
<script type="text/javascript">
 function collect() {
		var text = '${article.title}';//收藏的标题
		var url = window.location.href;//收藏的地址

		$.post("/collect", {
			text : text,
			url : url
		}, function(flag) {
			if (flag.code == 0) {
				alert(flag.msg);
				$("#mc").html(
						"<span style='font-size: 20px;color: red'>★(已收藏)</span>")
			} else {
				alert(flag.msg);
				location="/passport/login";

			}
		})

	}
 </script>
</head>
<body>
<input type="button" value="返回" onclick="selAll()">
<h5>访问量：${article.hits }</h5>
<div style="text-align: center">
		<dl>
			<dt>
			 <h2>${article.title }</h2>
			</dt>
			
			<hr>
			<dd id="mc">

				<c:if test="${isCollect==1}">
					<span style="font-size: 20px; color: red">★ (已收藏)</span>
				</c:if>
				
				
				<c:if test="${isCollect!=1}">
					<span style="font-size: 20px; color: blue;"> <a
						href="javascript:collect()">☆ (未收藏)</a>
					</span>
				</c:if>


			</dd>
			
			
			<hr>
			<dd>
			<!-- <button type="button" class="btn btn-info" onclick="close()">关闭</button> -->
			</dd>
			<dd><fmt:formatDate value="${aritcle.updated }" pattern="yyyy-MM-dd HH:mm:ss"/> </dd>
			<dd>${article.content }</dd>

		</dl>

	</div>
	
	<script type="text/javascript">
	 
	 function selAll(){
		 alert("111");
		location="/";
	 }
	
	</script>
</body>
</html>