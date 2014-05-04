package org.apache.nutch.analysis.zh;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Set;

import org.ansj.splitWord.analysis.IndexAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.nutch.analysis.zh.util.AnsjTokenizer;

public class AnsjIndexAnalysis extends Analyzer {
	public Set<String> filter;

	public boolean pstemming = false;;

	/**
	 * 如果需要停用词就传入停用词的hashmap value0
	 * 
	 * @param filter
	 * @param pstemming
	 *            ,是否分析词干
	 */
	public AnsjIndexAnalysis(Set<String> filter, boolean pstemming) {
		this.filter = filter;
		this.pstemming = pstemming;
	}

	public AnsjIndexAnalysis(boolean pstemming) {
		this.pstemming = pstemming;
	}

	public AnsjIndexAnalysis() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		// TODO Auto-generated method stub
		return new AnsjTokenizer(new IndexAnalysis(new BufferedReader(reader)), reader, filter, pstemming);
	}

}
