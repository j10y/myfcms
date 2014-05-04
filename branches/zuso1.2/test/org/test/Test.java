package org.test;

import java.io.StringReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.nutch.analysis.AnalyzerFactory;
import org.apache.nutch.util.NutchConfiguration;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* 标准分词器：单子分词 */
		try {
			String text2 = "党政 领导 干部 选拔任用 工作条例";
			Configuration conf = NutchConfiguration.createCrawlConfiguration();
			Analyzer analyzer = AnalyzerFactory.get(conf).get("zh");
			testAnalyzer(analyzer, text2); // 使用IKAnalyzer，词库分词
	
//			System.out.println(new ToAnalysis().parse("党政领导干部选拔任用工作条例")); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static void testAnalyzer(Analyzer analyzer, String text) throws Exception {
		System.out.println("当前使用的分词器：" + analyzer.getClass());

		TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
		tokenStream.addAttribute(TypeAttribute.class);

		while (tokenStream.incrementToken()) {
			TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);
			System.out.print(termAttribute.term()+",");
		}
	}

}
