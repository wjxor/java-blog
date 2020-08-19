<%@ page import="java.util.List"%>
<%@ page import="com.sbs.java.blog.dto.CateItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<CateItem> cateItems = (List<CateItem>) request.getAttribute("cateItems");
%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 구글 폰트 불러오기 -->
<!-- rotobo(400/900), notosanskr(400/900) -->
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;900&family=Roboto:wght@400;900&display=swap"
	rel="stylesheet">

<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/css/home/main.css">

<!-- 제이쿼리 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/resource/js/common.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/js/home/main.js"></script>

<title>wjxor 블로그</title>
</head>

<body>
	<div class="mobile-top-bar visible-on-sm-down flex">
		<a href="#" class="btn-toggle-mobile-side-bar flex flex-ai-c"> <i
			class="fas fa-bars"></i> <i class="fas fa-times"></i>
		</a> <a href="${pageContext.request.contextPath}/s/home/main"
			class="logo absolute-center absolute-middle flex flex-ai-c"> <i
			class="fas fa-award"></i>
		</a>
	</div>
	<div class="mobile-side-bar flex flex-ai-c visible-on-sm-down">
		<nav class="menu-box-1 flex-grow-1">
			<ul>
				<li><a href="${pageContext.request.contextPath}/s/home/main"
					class="block">Home</a></li>
				<li><a href="#" class="block">Articles</a>
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/s/article/list"
							class="block">전체</a></li>
						<%
							for (CateItem cateItem : cateItems) {
						%>
						<li><a
							href="${pageContext.request.contextPath}/s/article/list?cateItemId=<%=cateItem.getId()%>"
							class="block"><%=cateItem.getName()%></a></li>
						<%
							}
						%>
					</ul></li>
				<li><a href="${pageContext.request.contextPath}/s/home/aboutMe"
					class="block">AboutMe</a></li>
				<li><a href="#" class="block">SNS</a>
					<ul>
						<li><a href="https://github.com/wjxor" target="_blank"
							class="block">GITHUB</a></li>
						<li><a href="https://www.instagram.com/shko9405" target="_blank"
							class="block">INSTA</a></li>
					</ul></li>
			</ul>
		</nav>
	</div>
	<div class="top-bar visible-on-md-up">
		<div class="con flex flex-jc-sb height-100p">
			<a href="${pageContext.request.contextPath}/s/home/main"
				class="logo flex flex-ai-c"> <i class="fas fa-award"></i>
			</a>

			<nav class="menu-box-1">
				<ul class="flex height-100p">
					<li><a href="${pageContext.request.contextPath}/s/home/main"
						class="flex height-100p flex-ai-c">Home</a></li>
					<li><a
						href="${pageContext.request.contextPath}/s/article/list"
						class="flex height-100p flex-ai-c">Articles</a>
						<ul>
							<%
								for (CateItem cateItem : cateItems) {
							%>
							<li><a
								href="${pageContext.request.contextPath}/s/article/list?cateItemId=<%=cateItem.getId()%>"
								class="block"><%=cateItem.getName()%></a></li>
							<%
								}
							%>
						</ul></li>
					<li><a
						href="${pageContext.request.contextPath}/s/home/aboutMe"
						class="flex height-100p flex-ai-c">AboutMe</a></li>
					<li><a href="#" class="flex height-100p flex-ai-c">SNS</a>
						<ul>
							<li><a href="https://github.com/wjxor" target="_blank"
								class="block">GITHUB</a></li>
							<li><a href="https://www.instagram.com/shko9405" target="_blank"
								class="block">INSTA</a></li>
						</ul></li>
				</ul>
			</nav>
		</div>
	</div>