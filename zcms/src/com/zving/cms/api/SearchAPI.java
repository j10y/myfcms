package com.zving.cms.api;

import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.ServletUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZCFullTextSchema;
import com.zving.schema.ZCFullTextSet;
import com.zving.search.ArticleIndexer;
import com.zving.search.ArticleSearcher;
import com.zving.search.SearchParameters;
import com.zving.search.SearchResult;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;

public class SearchAPI {
	private static Mapx siteMap = new Mapx();

	public static String getIndexIDBySiteID(String site) {
		if ((StringUtil.isEmpty(site)) || (!(StringUtil.isDigit(site)))) {
			return null;
		}
		String id = siteMap.getString(site);
		if (id == null) {
			ZCFullTextSchema ft = new ZCFullTextSchema();
			ft.setSiteID(site);
			ft.setProp1("AutoIndex");
			ZCFullTextSet set = ft.query();
			if (set.size() > 0) {
				id = String.valueOf(set.get(0).getID());
				siteMap.put(site, id);
			} else {
				return null;
			}
		}
		return id;
	}

	public static void update(long id) {
		ZCFullTextSchema ft = new ZCFullTextSchema();
		ft.setID(id);
		if (ft.fill()) {
			if (ft.getType().equals("Article"))
				updateArticle(id);
		} else
			LogUtil.getLogger().warn("没有ID=" + id + "的全文检索!");
	}

	public static void updateArticle(long id) {
		ArticleIndexer ai = new ArticleIndexer(id);
		ai.start();
	}

	public static SearchResult searchArticle(long id, String keyword, int pageSize, int pageIndex) {
		SearchParameters sps = new SearchParameters();
		sps.addFulltextField("_KeyWord", keyword);
		sps.setPageIndex(pageIndex);
		sps.setPageSize(pageSize);
		sps.setIndexID(id);
		return ArticleIndexer.search(sps);
	}

	public static SearchResult searchArticle(String keyword, int pageSize, int pageIndex, Mapx map) {
		map.put("keyword", keyword);
		map.put("page", pageIndex);
		map.put("size", pageSize);
		return ArticleSearcher.search(map);
	}

	public static SearchResult searchArticle(long id, String keyword, int pageSize, int pageIndex,
			String catalog) {
		Mapx map = new Mapx();
		map.put("catalog", catalog);
		map.put("keyword", keyword);
		map.put("page", pageIndex);
		map.put("size", pageSize);
		return ArticleSearcher.search(map);
	}

	public static SearchResult search(long id, String keyword, int pageSize, int pageIndex, Mapx map) {
		map.put("keyword", keyword);
		map.put("page", pageIndex);
		map.put("size", pageSize);
		return ArticleSearcher.search(map);
	}

	public static String getPageURL(HttpServletRequest request, int pageNo) {
		Mapx map = ServletUtil.getParameterMap(request, false);
		map.put("page", pageNo);
		return ServletUtil.getQueryStringFromMap(map, false);
	}

	public static String getURL(HttpServletRequest request, String text, String type, String value) {
		Mapx map = ServletUtil.getParameterMap(request, false);
		if ((value != null) && (value.equals(map.get(type)))) {
			return " ·" + text;
		}
		map.put(type, value);
		return " ·<a href='" + ServletUtil.getQueryStringFromMap(map, false) + "'>" + text + "</a>";
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.api.SearchAPI JD-Core Version: 0.5.3
 */