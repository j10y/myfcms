package org.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.html.Entities;
import org.apache.nutch.searcher.Hit;
import org.apache.nutch.searcher.HitDetails;
import org.apache.nutch.searcher.Hits;
import org.apache.nutch.searcher.NutchBean;
import org.apache.nutch.searcher.Query;
import org.apache.nutch.searcher.Summary;
import org.apache.nutch.util.NutchConfiguration;

public class TestNutch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration nutchConf = NutchConfiguration.create();
		
		try {
			NutchBean bean = new NutchBean(nutchConf);
			
			// get query from request
			String queryString = "湖北省";
			if (queryString == null)
				queryString = "";
			String htmlQueryString = Entities.encode(queryString);
			
			int start = 0; // first hit to display
			
			int hitsPerPage = 10; // number of hits to display
			
			int hitsPerSite = 0; // max hits per site
			
			boolean reverse = true;
			
			// get the lang from request
			String queryLang = "zh";
			if (queryLang == null) {
				queryLang = "";
			}
			Query query = Query.parse(queryString, queryLang, nutchConf);
			Hits hits = bean.search(query);
			Hit[] show = hits.getHits(0, 10);
			HitDetails[] details = bean.getDetails(show);
			Summary[] summaries = bean.getSummary(details, query);
			System.out.println("total hits: " + hits.getTotal());
			
			
//			for (int i = 0; i < 10; i++) { // display the hits
//				Hit hit = show[i];
//				HitDetails detail = details[i];
//				String title = detail.getValue("title");
//				String url = detail.getValue("url");
//				String id = "idx=" + hit.getIndexNo() + "&id=" + hit.getUniqueKey();
//				String summary = summaries[i].toHtml(true);
//				String caching = detail.getValue("cache");
//				
//				System.out.println(title);
//				System.out.println(url);
//				System.out.println(summary);
//			}
//			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
