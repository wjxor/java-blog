package com.sbs.java.blog.config;

public class Config {
	// [설정 방법] 아래 값들은 App.start()에서 다음 우선순위로 주입된다.
	// 1순위: 환경변수 BLOG_GMAIL_ID, BLOG_GMAIL_PW, BLOG_MAIL_FROM, BLOG_MAIL_FROM_NAME
	// 2순위: WebContent/WEB-INF/web.xml 의 context-param (web.xml.sample 을 web.xml 로 복사 후 값 입력)
	// gmailId/gmailPw 를 설정하지 않으면 메일 기능(가입 환영메일, 임시 비밀번호 발송)만 동작하지 않고 나머지는 정상 동작한다.
	public static String mailFrom = "no-reply@no-reply.com";
	public static String mailFromName = "관리자";
	public static String gmailId;
	public static String gmailPw;

	public static String getSiteName() {
		return "ksh.blog.coding.family";
	}
}