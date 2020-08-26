package com.sbs.java.blog.service;

import java.sql.Connection;

import com.sbs.java.blog.dao.AttrDao;
import com.sbs.java.blog.dto.Attr;

public class AttrService extends Service {

	private AttrDao attrDao;

	public AttrService(Connection dbConn) {
		attrDao = new AttrDao(dbConn);
	}

	public Attr get(String name) {
		return attrDao.get(name);
	}

	public int setValue(String name, String value) {
		return attrDao.setValue(name, value);
	}

	public String getValue(String name) {
		return attrDao.getValue(name);
	}

	public int remove(String name) {
		return attrDao.remove(name);
	}
}