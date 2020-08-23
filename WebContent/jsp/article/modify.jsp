<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	Article article = (Article) request.getAttribute("article");
%>
<!-- 하이라이트 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/highlight.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/styles/default.min.css">

<!-- 하이라이트 라이브러리, 언어 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/css.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/javascript.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/xml.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php-template.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/sql.min.js"></script>

<!-- 코드 미러 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />

<!-- 토스트 UI 에디터, 자바스크립트 코어 -->
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

<!-- 토스트 UI 에디터, 신택스 하이라이트 플러그인 추가 -->
<script
	src="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight-all.min.js"></script>

<!-- 토스트 UI 에디터, CSS 코어 -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />

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
				<td colspan="2"><script type="text/x-template" id="origin1"
						style="display: none;"><%=article.getBodyForXTemplate()%></script>
					<div id="viewer1"></div> <script>
						var editor1__initialValue = getBodyFromXTemplate('#origin1');
						var editor1 = new toastui.Editor({
							el : document.querySelector('#viewer1'),
							initialValue : editor1__initialValue,
							viewer : true,
							plugins : [
									toastui.Editor.plugin.codeSyntaxHighlight,
									youtubePlugin, replPlugin, codepenPlugin ]
						});
					</script></td>
			</tr>
		</tbody>
	</table>




</div>



<%@ include file="/jsp/part/foot.jspf"%>