<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%@ include file="/jsp/part/toastUiEditor.jspf"%>

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
				<td>${article.id}</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${article.regDate}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${article.title}</td>
			</tr>
			<tr>
				<th>조회</th>
				<td>${article.hit}</td>
			</tr>
			<tr>
				<th>비고</th>
				<td><c:if test="${article.extra.deleteAvailable}">
						<a onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;"
							href="./doDelete?id=${article.id}">삭제</a>
					</c:if> <c:if test="${article.extra.modifyAvailable}">
						<a href="./modify?id=${article.id}">수정</a>
					</c:if></td>
			</tr>
			<tr>
				<td colspan="2"><script type="text/x-template">${article.bodyForXTemplate}</script>
					<div class="toast-editor toast-editor-viewer"></div></td>
			</tr>
		</tbody>
	</table>




</div>



<%@ include file="/jsp/part/foot.jspf"%>