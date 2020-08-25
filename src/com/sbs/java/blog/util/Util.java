package com.sbs.java.blog.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Util {
	public static boolean empty(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);

		return empty(paramValue);
	}

	public static boolean empty(Object obj) {
		if (obj == null) {
			return true;
		}

		if (obj instanceof String) {
			return ((String) obj).trim().length() == 0;
		}

		return true;
	}

	public static boolean isNum(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);

		return isNum(paramValue);
	}

	public static boolean isNum(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof Long) {
			return true;
		} else if (obj instanceof Integer) {
			return true;
		} else if (obj instanceof String) {
			try {
				Integer.parseInt((String) obj);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

		return false;
	}

	public static int getInt(HttpServletRequest req, String paramName) {
		return Integer.parseInt(req.getParameter(paramName));
	}

	public static void printEx(String errName, HttpServletResponse resp, Exception e) {
		try {
			resp.getWriter()
					.append("<h1 style='color:red; font-weight:bold; text-align:left;'>[에러 : " + errName + "]</h1>");

			resp.getWriter().append("<pre style='text-align:left; font-weight:bold; font-size:1.3rem;'>");
			e.printStackTrace(resp.getWriter());
			resp.getWriter().append("</pre>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static String getString(HttpServletRequest req, String paramName) {
		return req.getParameter(paramName);
	}

	public static String getUrlEncoded(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	public static String getString(HttpServletRequest req, String paramName, String elseValue) {
		if (req.getParameter(paramName) == null) {
			return elseValue;
		}

		if (req.getParameter(paramName).trim().length() == 0) {
			return elseValue;
		}

		return getString(req, paramName);
	}

	public static boolean isSuccess(Map<String, Object> rs) {
		return ((String) rs.get("resultCode")).startsWith("S-");
	}

	public static String getNewUrlRemoved(String url, String paramName) {
		String deleteStrStarts = paramName + "=";
		int delStartPos = url.indexOf(deleteStrStarts);

		if (delStartPos != -1) {
			int delEndPos = url.indexOf("&", delStartPos);

			if (delEndPos != -1) {
				delEndPos++;
				url = url.substring(0, delStartPos) + url.substring(delEndPos, url.length());
			} else {
				url = url.substring(0, delStartPos);
			}
		}

		if (url.charAt(url.length() - 1) == '?') {
			url = url.substring(0, url.length() - 1);
		}

		if (url.charAt(url.length() - 1) == '&') {
			url = url.substring(0, url.length() - 1);
		}

		return url;
	}

	public static String getNewUrl(String url, String paramName, String paramValue) {
		url = getNewUrlRemoved(url, paramName);

		if (url.contains("?")) {
			url += "&" + paramName + "=" + paramValue;
		} else {
			url += "?" + paramName + "=" + paramValue;
		}

		url = url.replace("?&", "?");

		return url;
	}

	public static String getNewUrlAndEncoded(String url, String paramName, String pramValue) {
		return getUrlEncoded(getNewUrl(url, paramName, pramValue));
	}
}