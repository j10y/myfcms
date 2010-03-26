package com.hxzy.base.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.lucene.demo.html.HTMLParser;

public class HTMLDocParser {

	private String htmlPath;

	private HTMLParser htmlParser;

	public HTMLDocParser(String htmlPath) {
		this.htmlPath = htmlPath;
		initHtmlParser();
	}

	private void initHtmlParser() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(htmlPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (null != inputStream) {
			try {
				htmlParser = new HTMLParser(new InputStreamReader(inputStream,
						"gb2312"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	public String getTitle() {
		if (null != htmlParser) {
			try {
				return htmlParser.getTitle();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public Reader getContent() {
		if (null != htmlParser) {
			try {
				return htmlParser.getReader();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getPath() {
		return this.htmlPath;
	}

}
