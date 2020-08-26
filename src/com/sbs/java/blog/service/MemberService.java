package com.sbs.java.blog.service;

import java.sql.Connection;

import com.sbs.java.blog.dao.MemberDao;
import com.sbs.java.blog.dto.Member;

public class MemberService extends Service {
	private MailService mailService;
	private MemberDao memberDao;

	public MemberService(Connection dbConn, MailService mailService) {
		memberDao = new MemberDao(dbConn);
		this.mailService = mailService;
	}

	public boolean isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}

	public int join(String loginId, String loginPw, String name, String nickname, String email) {
		int id = memberDao.join(loginId, loginPw, name, nickname, email);

		mailService.send(email, "가입을 환영합니다.", "<a href=\"https://hs.my.iu.gy/\" target=\"_blank\">사이트로 이동</a>");

		return id;
	}

	public boolean isJoinableNickname(String nickname) {
		return memberDao.isJoinableNickname(nickname);
	}

	public boolean isJoinableEmail(String email) {
		return memberDao.isJoinableEmail(email);
	}

	public int getMemberIdByLoginIdAndLoginPw(String loginId, String loginPw) {
		return memberDao.getMemberIdByLoginIdAndLoginPw(loginId, loginPw);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}
}