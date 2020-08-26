package com.sbs.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Member;

public class HomeController extends Controller {
	public HomeController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}

	@Override
	public String doAction() {
		switch (actionMethodName) {
		case "main":
			return actionMain();
		case "aboutMe":
			return actionAboutMe();
		}

		return "";
	}

	private String actionAboutMe() {
		return "home/aboutMe.jsp";
	}

	private String actionMain() {
		return "home/main.jsp";
	}

	@Override
	public String getControllerName() {
		return "home";
	}
}