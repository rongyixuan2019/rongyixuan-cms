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

<title>个人中心主页面</title>
 
</head>
<body>
<!-- 头 -->
	<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
	<br />
	<!-- 横幅 -->
	<div class="container">
		<div class="row">
			<div class="col-md-12 my_banner"></div>
		</div>
	</div>
	<br />
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<!-- 导航条 -->
				<jsp:include page="/WEB-INF/views/my/left.jsp"></jsp:include>
				<br />
			</div>
			<div class="col-md-9" id="center">
				 <!--引入富文本编辑器  -->
				 <div style="display: none">
				 <jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
				 </div>
			
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
 <script type="text/javascript">
 $(function(){
	 
	 //页面加载时让为左侧菜单默认点击 我的文章
	  
	 $("#myArticle").click();
   $("#center").load("/my/selectByUser")
    
	 $(".channel").click(function(){

		 var li =$("ul li");
		//先移除所有的list-group-item-info样式
		li.removeClass("list-group-item-info");
		//为左侧菜单添加动态点击样式 
		$(this).parent().addClass("list-group-item-info");
		 var url =$(this).attr("data");
		 alert(url);
		 $("#center").load(url);
	 })
 })
 
 </script>

</body>
</html>