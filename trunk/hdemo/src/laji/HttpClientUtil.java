package laji;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;

/**
 * HTTP���󹤾���
 * 
 * @author huwei(jshuwei.org.cn)
 * @since 1.5
 */
public class HttpClientUtil {
	/**
	 * ģ��get����
	 * 
	 * @param url
	 *            ����URL��ַ
	 * @param params
	 *            �������
	 * @param charset
	 *            �ַ�����
	 * @return get�������Ӧ�ַ���
	 */
	public static String doGet(String url, String params, String charset) {
		HttpClient hc = new HttpClient();
		hc.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		GetMethod gm = new GetMethod(url);
		gm.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		gm.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		String content = "";
		try {
			gm.setQueryString(URIUtil.encodeQuery(params, charset));
			if (hc.executeMethod(gm) == HttpStatus.SC_OK)
				content = new String(gm.getResponseBody(), charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * ģ��post����
	 * 
	 * @param url
	 *            ����URL��ַ
	 * @param params
	 *            �������
	 * @param charset
	 *            �ַ�����
	 * @param isWrap
	 *            �Ƿ���
	 * @return post�������Ӧ�ַ���
	 */
	public static String doPost(String url, Map<String, String> params, String charset,
			boolean isWrap) {
		HttpClient hc = new HttpClient();
		HttpMethod hm = new PostMethod();
		StringBuffer sb = new StringBuffer();
		if (params != null && params.size() != 0) {
			HttpMethodParams hmp = new HttpMethodParams();
			for (String key : params.keySet())
				hmp.setParameter(key, params.get(key));
			hm.setParams(hmp);
		}
		try {
			hc.executeMethod(hm);
			if (hm.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(hm
						.getResponseBodyAsStream(), charset));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
					if (isWrap)
						sb.append(System.getProperty("line.separator"));
				}
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hm.releaseConnection();
		}
		return sb.toString();
	}
}