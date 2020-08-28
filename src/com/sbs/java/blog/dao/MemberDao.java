package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.Map;

import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.util.DBUtil;
import com.sbs.java.blog.util.SecSql;

public class MemberDao extends Dao {
	private Connection dbConn;

	public MemberDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public int join(String loginId, String loginPw, String name, String nickname, String email) {
		SecSql sql = SecSql.from("INSERT INTO member");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", name = ?", name);
		sql.append(", nickname = ?", nickname);
		sql.append(", email = ?", email);
		return DBUtil.insert(dbConn, sql);
	}

	public boolean isJoinableLoginId(String loginId) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		return DBUtil.selectRowIntValue(dbConn, sql) == 0;
	}

	public boolean isJoinableNickname(String nickname) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM `member`");
		sql.append("WHERE nickname = ?", nickname);

		return DBUtil.selectRowIntValue(dbConn, sql) == 0;
	}

	public boolean isJoinableEmail(String email) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM `member`");
		sql.append("WHERE email = ?", email);

		return DBUtil.selectRowIntValue(dbConn, sql) == 0;
	}

	public int getMemberIdByLoginIdAndLoginPw(String loginId, String loginPw) {
		SecSql sql = SecSql.from("SELECT id");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		sql.append("AND loginPw = ?", loginPw);

		return DBUtil.selectRowIntValue(dbConn, sql);
	}

	public Member getMemberById(int id) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE id = ?", id);

		Map<String, Object> row = DBUtil.selectRow(dbConn, sql);

		if (row.isEmpty()) {
			return null;
		}

		return new Member(row);
	}

	public void modify(int actorId, String loginPw) {
		SecSql sql = SecSql.from("UPDATE member");
		sql.append("SET updateDate = NOW()");
		sql.append(", loginPw = ?", loginPw);
		sql.append("WHERE id = ?", actorId);
		DBUtil.update(dbConn, sql);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE name = ?", name);
		sql.append("AND email = ?", email);

		Map<String, Object> row = DBUtil.selectRow(dbConn, sql);

		if (row.isEmpty()) {
			return null;
		}

		return new Member(row);
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		Map<String, Object> row = DBUtil.selectRow(dbConn, sql);

		if (row.isEmpty()) {
			return null;
		}

		return new Member(row);
	}
}