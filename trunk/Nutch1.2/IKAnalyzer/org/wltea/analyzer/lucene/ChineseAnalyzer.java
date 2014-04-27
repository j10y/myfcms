package org.wltea.analyzer.lucene;

import java.io.Reader;

import org.apache.hadoop.conf.Configurable;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.nutch.analysis.NutchAnalyzer;
import org.apache.nutch.plugin.Pluggable;

public class ChineseAnalyzer extends NutchAnalyzer implements Configurable,
		Pluggable {

	private final static Analyzer analyzer = new IKAnalyzer();

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		return analyzer.tokenStream(fieldName, reader);
	}

}
