<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%@ include file="/jsp/part/toastUiEditor.jspf"%>
<%
	Article article = (Article) request.getAttribute("article");
%>

<div class="con">
	<h1>게시물 상세페이지</h1>
</div>

<div class="con table-box">
	<table>
		<colgroup>
			<col width="200">
		</colgroup>

		<tbody>
			<tr>
				<th>번호</th>
				<td><%=article.getId()%></td>
			</tr>
			<tr>
				<th>날짜</th>
				<td><%=article.getRegDate()%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=article.getTitle()%></td>
			</tr>
			<tr>
				<th>조회</th>
				<td><%=article.getHit()%></td>
			</tr>
			<tr>
				<th>비고</th>
				<td>
					<div class="inline-block">
						<%
							if ((boolean) article.getExtra().get("deleteAvailable")) {
						%>
						<a onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;"
							href="./doDelete?id=<%=article.getId()%>">삭제</a>
						<%
							}
						%>
					</div>
					<div class="inline-block">
						<%
							if ((boolean) article.getExtra().get("modifyAvailable")) {
						%>
						<a href="./modify?id=<%=article.getId()%>">수정</a>
						<%
							}
						%>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><script type="text/x-template"><%=article.getBodyForXTemplate()%></script>
					<div class="toast-editor toast-editor-viewer"></div></td>
			</tr>
		</tbody>
	</table>




</div>



<%@ include file="/jsp/part/foot.jspf"%>