package com.sbs.java.blog.dao;

import java.sql.Connection;

import com.sbs.java.blog.dto.Attr;
import com.sbs.java.blog.util.DBUtil;
import com.sbs.java.blog.util.SecSql;

public class AttrDao {
	private Connection dbConn;

	public AttrDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public int setValue(String name, String value) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO attr (regDate, updateDate, `name`, `value`)");
		sql.append("VALUES (NOW(), NOW(), ?, ?)", name, value);
		sql.append("ON DUPLICATE KEY UPDATE");
		sql.append("updateDate = NOW()");
		sql.append(", `value` = ?", value);

		return DBUtil.update(dbConn, sql);
	}

	public Attr get(String name) {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM attr");
		sql.append("WHERE `name` = ?", name);

		return new Attr(DBUtil.selectRow(dbConn, sql));
	}

	public String getValue(String name) {
		SecSql sql = new SecSql();

		sql.append("SELECT `value`");
		sql.append("FROM attr");
		sql.append("WHERE `name` = ?", name);

		return DBUtil.selectRowStringValue(dbConn, sql);
	}

	public int remove(String name) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM attr");
		sql.append("WHERE `name` = ?", name);

		return DBUtil.delete(dbConn, sql);
	}
}