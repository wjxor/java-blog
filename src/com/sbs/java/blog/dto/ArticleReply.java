package com.sbs.java.blog.dto;

import java.util.Map;

import lombok.Data;

@Data
public class ArticleReply extends Dto {
	private int articleId;
	private int memberId;
	private String body;

	public ArticleReply(Map<String, Object> row) {
		super(row);

		this.articleId = (int) row.get("articleId");
		this.memberId = (int) row.get("memberId");
		this.body = (String) row.get("body");
	}

	public String getBodyForXTemplate() {
		return body.replaceAll("(?i)script", "<!--REPLACE:script-->");
	}

}