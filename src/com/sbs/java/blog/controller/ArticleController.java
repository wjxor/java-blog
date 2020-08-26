package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.Util;

public class ArticleController extends Controller {
	public ArticleController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}

	public void beforeAction() {
		super.beforeAction();
		// 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
		// 필요없다면 지워도 된다.
	}

	public String doAction() {
		switch (actionMethodName) {
		case "list":
			return actionList();
		case "modify":
			return actionModify();
		case "doModifyReply":
			return actionDoModifyReply();
		case "modifyReply":
			return actionModifyReply();
		case "detail":
			return actionDetail();
		case "doWrite":
			return actionDoWrite();
		case "doWriteReply":
			return actionDoWriteReply();
		case "doDeleteReply":
			return actionDoDeleteReply();
		case "doDelete":
			return actionDoDelete();
		case "doModify":
			return actionDoModify();
		case "write":
			return actionWrite();
		}

		return "";
	}

	private String actionDoModifyReply() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");
		String body = Util.getString(req, "body");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		Map<String, Object> getReplyCheckRsModifyAvailableRs = articleService.getReplyCheckRsModifyAvailable(id,
				loginedMemberId);

		if (Util.isSuccess(getReplyCheckRsModifyAvailableRs) == false) {
			return "html:<script> alert('" + getReplyCheckRsModifyAvailableRs.get("msg")
					+ "'); history.back(); </script>";
		}

		articleService.modifyArticleReply(id, body);

		String redirectUri = Util.getString(req, "redirectUri", "list");

		redirectUri = Util.getNewUri(redirectUri, "lastWorkArticleReplyId", id + "");

		return "html:<script> alert('" + id + "번 댓글이 수정되었습니다.'); location.replace('" + redirectUri + "'); </script>";
	}

	private String actionDoWriteReply() {
		if (Util.empty(req, "articleId")) {
			return "html:articleId를 입력해주세요.";
		}

		if (Util.isNum(req, "articleId") == false) {
			return "html:articleId를 정수로 입력해주세요.";
		}

		int articleId = Util.getInt(req, "articleId");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		String body = Util.getString(req, "body");
		String redirectUri = Util.getString(req, "redirectUri");

		int id = articleService.writeArticleReply(articleId, loginedMemberId, body);

		redirectUri = Util.getNewUri(redirectUri, "lastWorkArticleReplyId", id + "");

		return "html:<script> alert('" + id + "번 댓글이 작성되었습니다.'); location.replace('" + redirectUri + "'); </script>";
	}

	private String actionWrite() {
		return "article/write.jsp";
	}

	private String actionDoModify() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		Map<String, Object> getCheckRsModifyAvailableRs = articleService.getCheckRsModifyAvailable(id, loginedMemberId);

		if (Util.isSuccess(getCheckRsModifyAvailableRs) == false) {
			return "html:<script> alert('" + getCheckRsModifyAvailableRs.get("msg") + "'); history.back(); </script>";
		}

		int cateItemId = Util.getInt(req, "cateItemId");
		String title = Util.getString(req, "title");
		String body = Util.getString(req, "body");

		articleService.modifyArticle(id, cateItemId, title, body);

		return "html:<script> alert('" + id + "번 게시물이 수정되었습니다.'); location.replace('detail?id=" + id + "'); </script>";
	}

	private String actionDoDeleteReply() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		Map<String, Object> getReplyCheckRsDeleteAvailableRs = articleService.getReplyCheckRsDeleteAvailable(id,
				loginedMemberId);

		if (Util.isSuccess(getReplyCheckRsDeleteAvailableRs) == false) {
			return "html:<script> alert('" + getReplyCheckRsDeleteAvailableRs.get("msg")
					+ "'); history.back(); </script>";
		}

		articleService.deleteArticleReply(id);

		String redirectUri = Util.getString(req, "redirectUri", "list");

		return "html:<script> alert('" + id + "번 댓글이 삭제되었습니다.'); location.replace('" + redirectUri + "'); </script>";
	}

	private String actionDoDelete() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		Map<String, Object> getCheckRsDeleteAvailableRs = articleService.getCheckRsDeleteAvailable(id, loginedMemberId);

		if (Util.isSuccess(getCheckRsDeleteAvailableRs) == false) {
			return "html:<script> alert('" + getCheckRsDeleteAvailableRs.get("msg") + "'); history.back(); </script>";
		}

		articleService.deleteArticle(id);

		return "html:<script> alert('" + id + "번 게시물이 삭제되었습니다.'); location.replace('list'); </script>";
	}

	private String actionDoWrite() {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int cateItemId = Util.getInt(req, "cateItemId");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		int id = articleService.write(cateItemId, title, body, loginedMemberId);

		return "html:<script> alert('" + id + "번 게시물이 생성되었습니다.'); location.replace('list'); </script>";
	}

	private String actionDetail() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		articleService.increaseHit(id);

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		Article article = articleService.getForPrintArticle(id, loginedMemberId);

		List<ArticleReply> articleReplies = articleService.getForPrintArticleReplies(id, loginedMemberId);

		req.setAttribute("article", article);
		req.setAttribute("articleReplies", articleReplies);

		return "article/detail.jsp";
	}

	private String actionModify() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		Article article = articleService.getForPrintArticle(id, loginedMemberId);

		req.setAttribute("article", article);

		return "article/modify.jsp";
	}

	private String actionModifyReply() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		ArticleReply articleReply = articleService.getArticleReply(id);
		req.setAttribute("articleReply", articleReply);

		Article article = articleService.getForPrintArticle(articleReply.getArticleId(), loginedMemberId);
		req.setAttribute("article", article);

		return "article/modifyReply.jsp";
	}

	private String actionList() {
		int page = 1;

		if (!Util.empty(req, "page") && Util.isNum(req, "page")) {
			page = Util.getInt(req, "page");
		}

		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) {
			cateItemId = Util.getInt(req, "cateItemId");
		}

		String cateItemName = "전체";

		if (cateItemId != 0) {
			CateItem cateItem = articleService.getCateItem(cateItemId);
			cateItemName = cateItem.getName();
		}
		req.setAttribute("cateItemName", cateItemName);

		String searchKeywordType = "";

		if (!Util.empty(req, "searchKeywordType")) {
			searchKeywordType = Util.getString(req, "searchKeywordType");
		}

		String searchKeyword = "";

		if (!Util.empty(req, "searchKeyword")) {
			searchKeyword = Util.getString(req, "searchKeyword");
		}

		int itemsInAPage = 10;
		int totalCount = articleService.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeyword);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);

		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("cPage", page);

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		List<Article> articles = articleService.getForPrintListArticles(loginedMemberId, page, itemsInAPage, cateItemId,
				searchKeywordType, searchKeyword);
		req.setAttribute("articles", articles);
		return "article/list.jsp";
	}

	@Override
	public String getControllerName() {
		return "article";
	}
}