package org.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.searcher.Hit;
import org.apache.nutch.searcher.HitDetails;
import org.apache.nutch.searcher.Hits;
import org.apache.nutch.searcher.NutchBean;
import org.apache.nutch.searcher.Query;
import org.apache.nutch.searcher.Summary;
import org.apache.nutch.util.NutchConfiguration;

public class SearchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration conf = NutchConfiguration.create();
		NutchBean bean;
		try {
			
			bean = new NutchBean(conf);
			Query query = Query.parse("No matter where you go in life or how old you get","en", conf);
//			Query query = Query.parse("焦裕禄精神学习","zh", conf);			
			System.out.println(query);
//			query =  Query.parse(query.toString().replace("\"", ""),"zh", conf);
			System.out.println(query);
			query.getParams().setMaxHitsPerDup(1);
			final Hits hits = bean.search(query);
			System.out.println("Total hits: " + hits.getTotal());
			final int length = (int) Math.min(hits.getLength(), 10);
			final Hit[] show = hits.getHits(0, length);
			final HitDetails[] details = bean.getDetails(show);
			final Summary[] summaries = bean.getSummary(details, query);
		
		
			for (int i = 0; i < hits.getLength(); i++) {
				System.out.println(" " + i + " " + details[i].getColValue("title", query) + "\n" + summaries[i].toHtml(false));
			}
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);

	}

}
