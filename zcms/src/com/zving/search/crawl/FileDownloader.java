package com.zving.search.crawl;

import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.ServletUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.framework.utility.ZipUtil;
import com.zving.search.DocumentList;
import com.zving.search.SearchUtil;
import com.zving.search.WebDocument;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.httpclient.CircularRedirectException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;

public class FileDownloader {
	private String allowExtension;
	private String denyExtension;
	private HttpClient client;
	private int threadCount = 50;

	protected int aliveThreadCount = 0;

	protected int busyThreadCount = 0;
	private boolean proxyFlag;
	private String proxyHost;
	private int proxyPort;
	private String proxyUserName;
	private String proxyPassword;
	private int timeout;
	private int currentLevel;
	private int downloadCount;
	private UrlExtracter extracter;
	public static final Pattern charsetPattern = Pattern
			.compile(
					"<meta\\s*?http\\-equiv.*?content-type.*?content\\s*?=.*?charset\\s*?=\\s*?([^\\\"\\'\\s]+?)(\\\"|\\'|\\s|>|(/>))",
					34);

	public int getTimeout() {
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void downloadList(DocumentList list, int level) {
		this.currentLevel = level;
		LogUtil.getLogger().info("===Level Download Start====\t Total " + list.size() + " URL");
		try {
			MultiThreadedHttpConnectionManager cm = new MultiThreadedHttpConnectionManager();
			HttpConnectionManagerParams hcmp = new HttpConnectionManagerParams();
			hcmp.setDefaultMaxConnectionsPerHost(this.threadCount);
			hcmp.setConnectionTimeout(this.timeout);
			hcmp.setSoTimeout(this.timeout);
			cm.setParams(hcmp);
			this.client = new HttpClient(cm);
			if (this.proxyFlag) {
				NTCredentials creds = new NTCredentials(this.proxyUserName, this.proxyPassword,
						this.proxyHost, "*");
				this.client.getState().setProxyCredentials(AuthScope.ANY, creds);
				this.client.getHostConfiguration().setProxy(this.proxyHost, this.proxyPort);
			}
			list.moveFirst();
			this.aliveThreadCount = this.threadCount;
			this.extracter = new UrlExtracter();
			this.extracter.init(list, level, this);
			for (int i = 0; i < this.threadCount; ++i) {
				FileDownloadThread fdt = new FileDownloadThread();
				fdt.setDownloader(this);
				fdt.setList(list);
				fdt.setThreadIndex(i);
				fdt.setHttpClient(this.client);
				fdt.start();
			}
			try {
				while (this.aliveThreadCount != 0) {
					Thread.sleep(1000L);
				}
				checkEmpty(list);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int downloadOne(WebDocument doc) {
		return downloadOne(null, doc, false, -1);
	}

	public static int downloadOne(HttpClient httpClient, WebDocument doc) {
		return downloadOne(httpClient, doc, false, -1);
	}

	public static int downloadOne(HttpClient httpClient, WebDocument doc, int threadIndex) {
		return downloadOne(httpClient, doc, false, threadIndex);
	}

	public static int downloadOne(HttpClient httpClient, WebDocument doc, boolean second,
			int threadIndex) {
		if (httpClient == null) {
			SimpleHttpConnectionManager cm = new SimpleHttpConnectionManager();
			HttpConnectionManagerParams hcmp = new HttpConnectionManagerParams();
			hcmp.setDefaultMaxConnectionsPerHost(1);
			hcmp.setConnectionTimeout(30000);
			hcmp.setSoTimeout(30000);
			cm.setParams(hcmp);
			httpClient = new HttpClient(cm);
		}
		String url = doc.getUrl();
		int statusCode = 200;
		HttpMethodBase gm = null;
		String charset = null;

		if (url.equals("http://imgs.xinhuanet.com/icon/photos/20071010tp.swf"))
			System.out.println(1);
		try {
			String host = ServletUtil.getHost(url);
			if (doc.getForm() != null) {
				Mapx form = doc.getForm();
				PostMethod pm = new PostMethod(url);
				Object[] ks = form.keyArray();
				Object[] vs = form.valueArray();
				for (int i = 0; i < form.size(); ++i) {
					pm.addParameter(ks[i].toString(), vs[i].toString());
				}
				gm = pm;
			} else {
				gm = new GetMethod(url);
			}

			gm.setRequestHeader("Host", host);
			gm
					.setRequestHeader(
							"User-Agent",
							"Mozilla/5.0 (Windows; U; Windows NT 5.2; zh-CN; rv:1.8.1.11) Gecko/20071127 Firefox/3.0.1 QQDownload");
			gm.setRequestHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

			httpClient.executeMethod(gm);

			statusCode = gm.getStatusCode();
			InputStream is = gm.getResponseBodyAsStream();
			byte[] body = getBytesFromStream(is);

			is.close();
			String contentType = null;
			if (gm.getResponseHeader("Content-Type") != null)
				contentType = gm.getResponseHeader("Content-Type").getValue().toLowerCase();
			else {
				contentType = "text/html";
			}
			if (gm.getResponseHeader("Content-Encoding") != null) {
				String contentEncoding = gm.getResponseHeader("Content-Encoding").getValue()
						.toLowerCase();
				if (contentEncoding.equals("gzip"))
					body = ZipUtil.ungzip(body);
				else if (contentEncoding.equals("zip")) {
					body = ZipUtil.unzip(body);
				}
			}
			Header h = gm.getResponseHeader("Last-Modified");
			if (h == null) {
				h = gm.getResponseHeader("Date");
			}
			Date lastModified = null;
			if (h != null) {
				lastModified = SearchUtil.parseLastModifedDate(h.getValue());
				if (lastModified == null)
					LogUtil.getLogger().info(
							"Error on Get lastModified：" + h.getValue() + "\t" + url + "\t RefURL："
									+ doc.getRefUrl());
			} else {
				lastModified = new Date();
			}
			doc.setLastDownloadTime(System.currentTimeMillis());
			doc.setContent(body);
			doc.setLastmodifiedDate(lastModified);
			doc.setErrorMessage(null);
			if ((contentType.indexOf("text") < 0) && (contentType.indexOf("html") < 0)) {
				doc.setTextContent(false);
			}
			doc.setTextContent(true);
			charset = gm.getResponseCharSet();
			try {
				Charset.forName(charset);
			} catch (Exception e) {
				charset = "utf-8";
			}
			if (((charset = getCharSet(body, charset)) == null)
					&& ((charset = getCharSet(body, "utf-8")) == null)
					&& ((charset = getCharSet(body, "unicode")) == null)) {
				charset = getCharSet(body, "ISO-8859-1");
			}

			try {
				Charset.forName(charset);
			} catch (Exception e) {
				charset = "utf-8";
			}
			if ((charset.equalsIgnoreCase("utf-8")) && (!(StringUtil.isUTF8(body)))) {
				charset = "gbk";
			}
			if (charset.equalsIgnoreCase("gb2312")) {
				charset = "gbk";
			}
			label1094: doc.setCharset(charset);
		} catch (UnsupportedEncodingException e) {
			doc.setErrorMessage("无法处理的文件编码:" + charset);
			LogUtil.getLogger().info(
					"Error Charset,URL not been Downloaded：" + charset + "\t" + url + "\t RefURL："
							+ doc.getRefUrl());
		} catch (IllegalArgumentException e) {
			if (second) {
				e.printStackTrace();
				doc.setErrorMessage("URL语法不正确");
				LogUtil.getLogger().info(
						"URL Syntax Error,URL not been Downloaded：\t" + url + "\t RefURL："
								+ doc.getRefUrl());
			} else {
				statusCode = downloadOne(httpClient, doc, true, threadIndex);
			}
		} catch (CircularRedirectException e) {
			doc.setErrorMessage("URL循环重定向");
			LogUtil.getLogger().info(
					gm.getStatusCode() + ":Circular Redirect,URL not been Downloaded：\t" + url
							+ "\t RefURL：" + doc.getRefUrl());
		} catch (HttpException e) {
			if (second) {
				e.printStackTrace();
				doc.setErrorMessage("HttpException,第二次重试失败.");
				LogUtil.getLogger().info(
						"HttpException,Failed With 2 Times Retry：\t" + url + "\t RefURL："
								+ doc.getRefUrl());
			} else {
				statusCode = downloadOne(httpClient, doc, true, threadIndex);
			}
		} catch (IOException e) {
			if (second) {
				doc.setErrorMessage("IOException,两次重试皆失败");
				LogUtil.getLogger().info(
						"IOException,Failed With 2 Times Retry：\t" + url + "\t RefURL："
								+ doc.getRefUrl());
			} else {
				statusCode = downloadOne(httpClient, doc, true, threadIndex);
			}
		} finally {
			if (gm != null) {
				gm.releaseConnection();
			}
		}
		return statusCode;
	}

	public static String getCharSet(byte[] body, String charset) {
		String tmp = null;
		try {
			tmp = new String(body, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Matcher m = charsetPattern.matcher(tmp);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}

	private static byte[] getBytesFromStream(InputStream is) throws IOException {
		byte[] buffer = new byte[1024];
		int read = -1;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] data = (byte[]) null;
		try {
			while ((read = is.read(buffer)) != -1) {
				if (read > 0) {
					byte[] chunk = (byte[]) null;
					if (read == 1024) {
						chunk = buffer;
					} else {
						chunk = new byte[read];
						System.arraycopy(buffer, 0, chunk, 0, read);
					}
					bos.write(chunk);
				}
			}
			data = bos.toByteArray();
		} finally {
			if (bos != null) {
				bos.close();
				bos = null;
			}
		}
		return data;
	}

	public boolean isMatchedUrl(String url, String refUrl) {
		return this.extracter.isMatchedUrl(url, refUrl);
	}

	public void checkEmpty(DocumentList list) {
		LogUtil.getLogger().info("===Check Content Empty URL====\tTotal " + list.size() + " URL");
		int t = this.threadCount / 3;
		if (t == 0) {
			t = 1;
		}
		this.aliveThreadCount = t;
		for (int i = 0; i < t; ++i) {
			FileDownloadThread fdt = new FileDownloadThread();
			fdt.setDownloader(this);
			fdt.setList(list);
			fdt.setThreadIndex(i);
			fdt.setHttpClient(this.client);
			fdt.setCheckEmpty(true);
			fdt.start();
		}
		try {
			while (this.aliveThreadCount != 0) {
				Thread.sleep(1000L);
			}
			return;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getDenyExtension() {
		return this.denyExtension;
	}

	public void setDenyExtension(String denyExtension) {
		this.denyExtension = denyExtension;
	}

	public String getAllowExtension() {
		return this.allowExtension;
	}

	public void setAllowExtension(String allowExtension) {
		this.allowExtension = allowExtension;
	}

	public int getThreadCount() {
		return this.threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public String getProxyHost() {
		return this.proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public String getProxyPassword() {
		return this.proxyPassword;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}

	public int getProxyPort() {
		return this.proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getProxyUserName() {
		return this.proxyUserName;
	}

	public void setProxyUserName(String proxyUserName) {
		this.proxyUserName = proxyUserName;
	}

	public int getCurrentLevel() {
		return this.currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getDownloadCount() {
		return this.downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public boolean isProxyFlag() {
		return this.proxyFlag;
	}

	public void setProxyFlag(boolean proxyFlag) {
		this.proxyFlag = proxyFlag;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.search.crawl.FileDownloader JD-Core Version: 0.5.3
 */