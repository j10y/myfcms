package org.apache.nutch.analysis.zh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.ansj.dic.DicReader;
import org.apache.hadoop.conf.Configurable;
import org.apache.lucene.analysis.TokenStream;
import org.apache.nutch.analysis.NutchAnalyzer;
import org.apache.nutch.plugin.Pluggable;

public class ChineseAnalyzer extends NutchAnalyzer implements Configurable, Pluggable {

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		Set<String> filter = new HashSet<String>();
		BufferedReader stopReader = DicReader.getReader("stopLibrary.dic");

		try {
			String str = stopReader.readLine();
			while (str != null && !str.isEmpty()) {
				filter.add(str);
				str = stopReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (fieldName.equals("DEFAULT")) {
			return new AnsjAnalysis(filter, false).tokenStream(fieldName, reader);
		} else {
			return new AnsjIndexAnalysis(filter, false).tokenStream(fieldName, reader);
		}
	}
}