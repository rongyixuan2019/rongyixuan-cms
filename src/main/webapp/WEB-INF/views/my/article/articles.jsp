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

<title>用户查看文章列表页面</title>
 
</head>
<body>
	<div>
		<table class="table table-hover  table-bordered">
			<thead class="thead-light">
				<tr>
					<th>序号</th>
					<th>文章标题</th>
					<th>作者</th>
					<th>是否热门</th>
					<th>所属栏目</th>
					<th>所属分类</th>
					<th>更新时间</th>
					<th>是否删除</th>
					<th>审核状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${info.list}" var="a" varStatus="i">
					<tr>
						<td>${(info.pageNum-1) * info.pageSize+i.index+1 }</td>
						<td>${a.title }</td>
						<td>${a.user.username }</td>
						<td>
						 <c:if test="${a.hot==0 }">
								<button type="button" class="btn btn-info"
									>否</button>
							</c:if> <c:if test="${a.hot==1 }">
								<button type="button" class="btn btn-success"
									>是</button>

							</c:if>
						
						</td>
						<td>${a.channel.name }</td>
						<td>${a.category.name }</td>
						<td><fmt:formatDate value="${a.updated }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${a.deleted==0?"正常":"管理员已删除" }</td>
								<td>${a.status==0?"待审":a.status==1?"已审":"驳回" }</td>
						<td><button type="button" class="btn btn-warning" onclick="detail(${a.id})">详情 </button> </td>
					</tr>


				</c:forEach>
			</tbody>

		</table>
          <!-- 引入分页信息 -->
		<jsp:include page="/WEB-INF/views/common/pages.jsp"/>

	</div>
	<script>
	
	//文章详情
	function detail(id){
		
		$("#center").load("/my/article?id="+id);
		
	}
	
	
	//分页
		function goPage(page) {
			var url = "/my/selectByUser?page=" + page 
			$("#center").load(url);
		}
	</script>
</body>
</html>