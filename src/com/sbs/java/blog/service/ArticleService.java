package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.CateItem;

public class ArticleService extends Service {

	private ArticleDao articleDao;

	public ArticleService(Connection dbConn, HttpServletRequest req, HttpServletResponse resp) {
		super(req, resp);
		articleDao = new ArticleDao(dbConn, req, resp);
	}

	public List<Article> getForPrintListArticles(int page, int itemsInAPage, int cateItemId, String searchKeywordType,
			String searchKeyword) {
		return articleDao.getForPrintListArticles(page, itemsInAPage, cateItemId, searchKeywordType, searchKeyword);
	}

	public int getForPrintListArticlesCount(int cateItemId, String searchKeywordType, String searchKeyword) {
		return articleDao.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeyword);
	}

	public Article getForPrintArticle(int id) {
		return articleDao.getForPrintArticle(id);
	}

	public List<CateItem> getForPrintCateItems() {
		return articleDao.getForPrintCateItems();
	}

	public CateItem getCateItem(int cateItemId) {
		return articleDao.getCateItem(cateItemId);
	}

}