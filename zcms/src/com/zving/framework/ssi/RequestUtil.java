package com.zving.framework.ssi;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.Cookie;

public final class RequestUtil {
	private static SimpleDateFormat format = new SimpleDateFormat(" EEEE, dd-MMM-yy kk:mm:ss zz");

	static {
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public static String encodeCookie(Cookie cookie) {
		StringBuffer buf = new StringBuffer(cookie.getName());
		buf.append("=");
		buf.append(cookie.getValue());

		if (cookie.getComment() != null) {
			buf.append("; Comment=\"");
			buf.append(cookie.getComment());
			buf.append("\"");
		}

		if (cookie.getDomain() != null) {
			buf.append("; Domain=\"");
			buf.append(cookie.getDomain());
			buf.append("\"");
		}

		if (cookie.getMaxAge() >= 0) {
			buf.append("; Max-Age=\"");
			buf.append(cookie.getMaxAge());
			buf.append("\"");
		}

		if (cookie.getPath() != null) {
			buf.append("; Path=\"");
			buf.append(cookie.getPath());
			buf.append("\"");
		}

		if (cookie.getSecure()) {
			buf.append("; Secure");
		}

		if (cookie.getVersion() > 0) {
			buf.append("; Version=\"");
			buf.append(cookie.getVersion());
			buf.append("\"");
		}

		return buf.toString();
	}

	public static String filter(String message) {
		if (message == null) {
			return null;
		}
		char[] content = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		for (int i = 0; i < content.length; ++i) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(content[i]);
			}
		}
		return result.toString();
	}

	public static String normalize(String path) {
		return normalize(path, true);
	}

	public static String normalize(String path, boolean replaceBackSlash) {
		if (path == null) {
			return null;
		}

		String normalized = path;

		if ((replaceBackSlash) && (normalized.indexOf(92) >= 0)) {
			normalized = normalized.replace('\\', '/');
		}
		if (normalized.equals("/.")) {
			return "/";
		}

		if (!(normalized.startsWith("/")))
			normalized = "/" + normalized;
		int index;
		while (true) {
			index = normalized.indexOf("//");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + normalized.substring(index + 1);
		}

		while (true) {
			index = normalized.indexOf("/./");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + normalized.substring(index + 2);
		}

		while (true) {
			index = normalized.indexOf("/../");
			if (index < 0)
				break;
			if (index == 0)
				return null;
			int index2 = normalized.lastIndexOf(47, index - 1);
			normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
		}

		return normalized;
	}

	public static String parseCharacterEncoding(String contentType) {
		if (contentType == null)
			return null;
		int start = contentType.indexOf("charset=");
		if (start < 0)
			return null;
		String encoding = contentType.substring(start + 8);
		int end = encoding.indexOf(59);
		if (end >= 0)
			encoding = encoding.substring(0, end);
		encoding = encoding.trim();
		if ((encoding.length() > 2) && (encoding.startsWith("\"")) && (encoding.endsWith("\"")))
			encoding = encoding.substring(1, encoding.length() - 1);
		return encoding.trim();
	}

	public static Cookie[] parseCookieHeader(String header) {
		if ((header == null) || (header.length() < 1)) {
			return new Cookie[0];
		}
		ArrayList cookies = new ArrayList();
		while (header.length() > 0) {
			int semicolon = header.indexOf(59);
			if (semicolon < 0)
				semicolon = header.length();
			if (semicolon == 0)
				break;
			String token = header.substring(0, semicolon);
			if (semicolon < header.length())
				header = header.substring(semicolon + 1);
			else
				header = "";
			try {
				int equals = token.indexOf(61);
				if (equals > 0) {
					String name = token.substring(0, equals).trim();
					String value = token.substring(equals + 1).trim();
					cookies.add(new Cookie(name, value));
				}
			} catch (Throwable localThrowable) {
			}
		}
		return ((Cookie[]) cookies.toArray(new Cookie[cookies.size()]));
	}

	public static void parseParameters(Map map, String data, String encoding)
			throws UnsupportedEncodingException {
		if ((data == null) || (data.length() <= 0)) {
			return;
		}

		byte[] bytes = (byte[]) null;
		try {
			if (encoding == null) {
				bytes = data.getBytes();
				parseParameters(map, bytes, encoding);
			}
			bytes = data.getBytes(encoding);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}

	}

	public static String URLDecode(String str) {
		return URLDecode(str, null);
	}

	public static String URLDecode(String str, String enc) {
		return URLDecode(str, enc, false);
	}

	public static String URLDecode(String str, String enc, boolean isQuery) {
		if (str == null) {
			return null;
		}

		byte[] bytes = (byte[]) null;
		try {
			if (enc == null) {
				bytes = str.getBytes();

			}
			bytes = str.getBytes(enc);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		return URLDecode(bytes, enc, isQuery);
	}

	public static String URLDecode(byte[] bytes) {
		return URLDecode(bytes, null);
	}

	public static String URLDecode(byte[] bytes, String enc) {
		return URLDecode(bytes, null, false);
	}

	public static String URLDecode(byte[] bytes, String enc, boolean isQuery) {
		if (bytes == null) {
			return null;
		}
		int len = bytes.length;
		int ix = 0;
		int ox = 0;
		while (ix < len) {
			byte b = bytes[(ix++)];
			if ((b == 43) && (isQuery))
				b = 32;
			else if (b == 37) {
				b = (byte) ((convertHexDigit(bytes[(ix++)]) << 4) + convertHexDigit(bytes[(ix++)]));
			}
			bytes[(ox++)] = b;
		}
		if (enc != null) {
			try {
				return new String(bytes, 0, ox, enc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new String(bytes, 0, ox);
	}

	private static byte convertHexDigit(byte b) {
		if ((b >= 48) && (b <= 57))
			return (byte) (b - 48);
		if ((b >= 97) && (b <= 102))
			return (byte) (b - 97 + 10);
		if ((b >= 65) && (b <= 70))
			return (byte) (b - 65 + 10);
		return 0;
	}

	private static void putMapEntry(Map map, String name, String value) {
		String[] newValues = (String[]) null;
		String[] oldValues = (String[]) map.get(name);
		if (oldValues == null) {
			newValues = new String[1];
			newValues[0] = value;
		} else {
			newValues = new String[oldValues.length + 1];
			System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
			newValues[oldValues.length] = value;
		}
		map.put(name, newValues);
	}

	public static void parseParameters(Map map, byte[] data, String encoding)
			throws UnsupportedEncodingException {
		if ((data != null) && (data.length > 0)) {
			int ix = 0;
			int ox = 0;
			String key = null;
			String value = null;
			while (ix < data.length) {
				byte c = data[(ix++)];
				switch ((char) c) {
				case '&':
					value = new String(data, 0, ox, encoding);
					if (key != null) {
						putMapEntry(map, key, value);
						key = null;
					}
					ox = 0;
					break;
				case '=':
					if (key == null) {
						key = new String(data, 0, ox, encoding);
						ox = 0;
					} else {
						data[(ox++)] = c;
					}
					break;
				case '+':
					data[(ox++)] = 32;
					break;
				case '%':
					data[(ox++)] = (byte) ((convertHexDigit(data[(ix++)]) << 4) + convertHexDigit(data[(ix++)]));
					break;
				default:
					data[(ox++)] = c;
				}
			}

			if (key != null) {
				value = new String(data, 0, ox, encoding);
				putMapEntry(map, key, value);
			}
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.ssi.RequestUtil JD-Core Version: 0.5.3
 */