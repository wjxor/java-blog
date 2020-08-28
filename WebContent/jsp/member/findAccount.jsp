<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="pageTitle" value="아이디/비번 찾기"></c:set>
<%@ include file="/jsp/part/head.jspf"%>

<style>
/* cus */
.find-loginId-form-box {
	margin-top: 30px;
}

.find-loginPw-form-box {
	margin-top: 30px;
}
</style>

<script>
	var FindLoginIdForm__submitDone = false;
	function FindLoginIdForm__submit(form) {
		if (FindLoginIdForm__submitDone) {
			alert('처리중 입니다.');
			return;
		}
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			return;
		}
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		form.submit();
		FindLoginIdForm__submitDone = true;
	}
</script>

<h2 class="con">로그인 아이디 찾기</h2>
<div class="find-loginId-form-box con">
	<form action="doFindLoginId" method="POST" class="form1"
		onsubmit="FindLoginIdForm__submit(this); return false;">
		<div class="form-row">
			<div class="label">이름</div>
			<div class="input">
				<input name="name" autofocus type="text" placeholder="이름을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">이메일</div>
			<div class="input">
				<input name="email" type="email" placeholder="이메일을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">찾기</div>
			<div class="input">
				<input type="submit" value="찾기" />
			</div>
		</div>
	</form>
</div>

<script>
	var FindLoginPwForm__submitDone = false;
	function FindLoginPwForm__submit(form) {
		if (FindLoginPwForm__submitDone) {
			alert('처리중 입니다.');
			return;
		}
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('로그인 아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		form.submit();
		FindLoginPwForm__submitDone = true;
	}
</script>

<h2 class="con">로그인 비번 찾기</h2>
<div class="find-loginPw-form-box con">
	<form action="doFindLoginPw" method="POST" class="form1"
		onsubmit="FindLoginPwForm__submit(this); return false;">
		<div class="form-row">
			<div class="label">로그인 아이디</div>
			<div class="input">
				<input name="loginId" autofocus type="text"
					placeholder="로그인 아이디를 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">이메일</div>
			<div class="input">
				<input name="email" type="email" placeholder="이메일을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">찾기</div>
			<div class="input">
				<input type="submit" value="찾기" />
			</div>
		</div>
	</form>
</div>

<%@ include file="/jsp/part/foot.jspf"%>