package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.UUID;

import com.sbs.java.blog.config.Config;
import com.sbs.java.blog.dao.MemberDao;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.util.Util;

public class MemberService extends Service {
	private MailService mailService;
	private MemberDao memberDao;
	private AttrService attrService;

	public MemberService(Connection dbConn, MailService mailService, AttrService attrService) {
		memberDao = new MemberDao(dbConn);
		this.mailService = mailService;
		this.attrService = attrService;
	}

	public boolean isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}

	public int join(String loginId, String loginPw, String name, String nickname, String email) {
		int id = memberDao.join(loginId, loginPw, name, nickname, email);

		mailService.send(email, "가입을 환영합니다.",
				"<a href=\"https://ksh.blog.coding.family/\" target=\"_blank\">사이트로 이동</a>");

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

	public String genModifyPrivateAuthCode(int actorId) {
		String authCode = UUID.randomUUID().toString();
		attrService.setValue("member__" + actorId + "__extra__modifyPrivateAuthCode", authCode);

		return authCode;
	}

	public boolean isValidModifyPrivateAuthCode(int actorId, String authCode) {
		String authCodeOnDB = attrService.getValue("member__" + actorId + "__extra__modifyPrivateAuthCode");

		return authCodeOnDB.equals(authCode);
	}

	public void modify(int actorId, String loginPw) {
		memberDao.modify(actorId, loginPw);

		attrService.remove("member", actorId, "extra", "useTempPassword");
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public void notifyTempLoginPw(Member member) {
		String to = member.getEmail();
		String tempPasswordOrigin = Util.getTempPassword(6);
		String tempPassword = Util.sha256(tempPasswordOrigin);

		modify(member.getId(), tempPassword);
		attrService.setValue("member", member.getId(), "extra", "useTempPassword", "1");

		String title = String.format("[%s] 임시패스워드 발송", Config.getSiteName());
		String body = String.format("<div>임시 패스워드 : %s</div>\n", tempPasswordOrigin);
		mailService.send(to, title, body);
	}

	public boolean isNeedToChangePasswordForTemp(int actorId) {
		return attrService.getValue("member", actorId, "extra", "useTempPassword").equals("1");
	}

	public Member getMemberByIdForSession(int actorId) {
		Member member = getMemberById(actorId);

		boolean isNeedToChangePasswordForTemp = isNeedToChangePasswordForTemp(member.getId());
		member.getExtra().put("isNeedToChangePasswordForTemp", isNeedToChangePasswordForTemp);

		return member;
	}
}