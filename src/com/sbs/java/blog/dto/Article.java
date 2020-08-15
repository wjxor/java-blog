package com.sbs.java.blog.dto;

import java.util.Map;

public class Article extends Dto {
	private String title;
	private String body;

	public Article(Map<String, Object> row) {
		super(row);
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
	}

	@Override
	public String toString() {
		return "Article [id=" + getId() + ", regDate=" + getRegDate() + ", title=" + title + ", body=" + body + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}