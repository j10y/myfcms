package org.test;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;
import org.apache.nutch.html.Entities;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "An IndexWriter creates and maintains an index.";
		/* 标准分词器：单子分词 */
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_20);
		try {
			testAnalyzer(analyzer, text);
			String text2 = "焦裕禄精神学习";
			testAnalyzer(new IKAnalyzer(), text2); // 使用IKAnalyzer，词库分词
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static void testAnalyzer(Analyzer analyzer, String text) throws Exception {
		System.out.println("当前使用的分词器：" + analyzer.getClass());

		TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
		tokenStream.addAttribute(TermAttribute.class);

		while (tokenStream.incrementToken()) {
			TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);
			System.out.println(termAttribute.term());
		}
	}

}
