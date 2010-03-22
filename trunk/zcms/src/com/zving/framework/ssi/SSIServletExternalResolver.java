 package com.zving.framework.ssi;
 
 import com.zving.framework.Config;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.ServletUtil;
 import java.io.File;
 import java.io.IOException;
 import java.io.UnsupportedEncodingException;
 import java.net.URL;
 import java.net.URLConnection;
 import java.net.URLDecoder;
 import java.util.Collection;
 import java.util.Date;
 import java.util.Enumeration;
 import javax.servlet.RequestDispatcher;
 import javax.servlet.ServletContext;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 public class SSIServletExternalResolver
   implements SSIExternalResolver
 {
   protected final String[] VARIABLE_NAMES = { "AUTH_TYPE", "CONTENT_LENGTH", "CONTENT_TYPE", "DOCUMENT_NAME", "DOCUMENT_URI", "GATEWAY_INTERFACE", "HTTP_ACCEPT", "HTTP_ACCEPT_ENCODING", "HTTP_ACCEPT_LANGUAGE", "HTTP_CONNECTION", "HTTP_HOST", "HTTP_REFERER", "HTTP_USER_AGENT", "PATH_INFO", "PATH_TRANSLATED", "QUERY_STRING", "QUERY_STRING_UNESCAPED", "REMOTE_ADDR", "REMOTE_HOST", "REMOTE_PORT", "REMOTE_USER", "REQUEST_METHOD", "REQUEST_URI", "SCRIPT_FILENAME", "SCRIPT_NAME", "SERVER_ADDR", "SERVER_NAME", "SERVER_PORT", "SERVER_PROTOCOL", "SERVER_SOFTWARE", "UNIQUE_ID" };
   protected ServletContext context;
   protected HttpServletRequest req;
   protected HttpServletResponse res;
   protected boolean isVirtualWebappRelative;
   protected int debug;
   protected String inputEncoding;
   private Mapx timeMap = new Mapx(); private Mapx fileMap = new Mapx();
 
   public SSIServletExternalResolver(ServletContext context, HttpServletRequest req, HttpServletResponse res, boolean isVirtualWebappRelative, int debug, String inputEncoding) { this.context = context; this.req = req; this.res = res; this.isVirtualWebappRelative = isVirtualWebappRelative; this.debug = debug; this.inputEncoding = inputEncoding;
   }
 
   public void log(String message, Throwable throwable)
   {
     if (throwable != null)
       this.context.log(message, throwable);
     else
       this.context.log(message);
   }
 
   public void addVariableNames(Collection variableNames)
   {
     for (int i = 0; i < this.VARIABLE_NAMES.length; ++i) {
       String variableName = this.VARIABLE_NAMES[i];
       String variableValue = getVariableValue(variableName);
       if (variableValue != null) {
         variableNames.add(variableName);
       }
     }
     Enumeration e = this.req.getAttributeNames();
     while (e.hasMoreElements()) {
       String name = (String)e.nextElement();
       if (!(isNameReserved(name)))
         variableNames.add(name);
     }
   }
 
   protected Object getReqAttributeIgnoreCase(String targetName)
   {
     Object object = null;
     if (!(isNameReserved(targetName))) {
       object = this.req.getAttribute(targetName);
       if (object == null) {
         Enumeration e = this.req.getAttributeNames();
         while (e.hasMoreElements()) {
           String name = (String)e.nextElement();
           if ((targetName.equalsIgnoreCase(name)) && (!(isNameReserved(name)))) {
             object = this.req.getAttribute(name);
             if (object != null) {
               break;
             }
           }
         }
       }
     }
     return object;
   }
 
   protected boolean isNameReserved(String name) {
     return ((name.startsWith("java.")) || (name.startsWith("javax.")) || (name.startsWith("sun.")));
   }
 
   public void setVariableValue(String name, String value) {
     if (!(isNameReserved(name)))
       this.req.setAttribute(name, value);
   }
 
   public String getVariableValue(String name)
   {
     String retVal = null;
     Object object = getReqAttributeIgnoreCase(name);
     if (object != null)
       retVal = object.toString();
     else {
       retVal = getCGIVariable(name);
     }
     return retVal;
   }
 
   protected String getCGIVariable(String name) {
     String retVal = null;
     String[] nameParts = name.toUpperCase().split("_");
     int requiredParts = 2;
     if (nameParts.length == 1) {
       if (nameParts[0].equals("PATH")) {
         requiredParts = 1;
         retVal = null;
       }
     } else if (nameParts[0].equals("AUTH")) {
       if (nameParts[1].equals("TYPE"))
         retVal = this.req.getAuthType();
     }
     else if (nameParts[0].equals("CONTENT")) {
       if (nameParts[1].equals("LENGTH")) {
         int contentLength = this.req.getContentLength();
         if (contentLength >= 0)
           retVal = Integer.toString(contentLength);
       }
       else if (nameParts[1].equals("TYPE")) {
         retVal = this.req.getContentType();
       }
     } else if (nameParts[0].equals("DOCUMENT")) {
       if (nameParts[1].equals("NAME")) {
         String requestURI = this.req.getRequestURI();
         retVal = requestURI.substring(requestURI.lastIndexOf(47) + 1);
       } else if (nameParts[1].equals("URI")) {
         retVal = this.req.getRequestURI();
       }
     } else if (name.equalsIgnoreCase("GATEWAY_INTERFACE")) {
       retVal = "CGI/1.1";
     } else if (nameParts[0].equals("HTTP")) {
       if (nameParts[1].equals("ACCEPT")) {
         String accept = null;
         if (nameParts.length == 2) {
           accept = "Accept";
         } else if (nameParts[2].equals("ENCODING")) {
           requiredParts = 3;
           accept = "Accept-Encoding";
         } else if (nameParts[2].equals("LANGUAGE")) {
           requiredParts = 3;
           accept = "Accept-Language";
         }
         if (accept != null) {
           Enumeration acceptHeaders = this.req.getHeaders(accept);
           if ((acceptHeaders != null) && 
             (acceptHeaders.hasMoreElements())) {
             StringBuffer rv = new StringBuffer((String)acceptHeaders.nextElement());
             while (acceptHeaders.hasMoreElements()) {
               rv.append(", ");
               rv.append((String)acceptHeaders.nextElement());
             }
             retVal = rv.toString();
           }
         }
       } else if (nameParts[1].equals("CONNECTION")) {
         retVal = this.req.getHeader("Connection");
       } else if (nameParts[1].equals("HOST")) {
         retVal = this.req.getHeader("Host");
       } else if (nameParts[1].equals("REFERER")) {
         retVal = this.req.getHeader("Referer");
       } else if ((nameParts[1].equals("USER")) && 
         (nameParts.length == 3) && 
         (nameParts[2].equals("AGENT"))) {
         requiredParts = 3;
         retVal = this.req.getHeader("User-Agent");
       }
     }
     else if (nameParts[0].equals("PATH")) {
       if (nameParts[1].equals("INFO"))
         retVal = this.req.getPathInfo();
       else if (nameParts[1].equals("TRANSLATED"))
         retVal = this.req.getPathTranslated();
     }
     else if (nameParts[0].equals("QUERY")) {
       if (nameParts[1].equals("STRING")) {
         String queryString = this.req.getQueryString();
         if (nameParts.length == 2)
         {
           retVal = nullToEmptyString(queryString);
         } else if (nameParts[2].equals("UNESCAPED")) {
           requiredParts = 3;
           if (queryString != null)
           {
             String queryStringEncoding = "ISO-8859-1";
 
             String uriEncoding = null;
             boolean useBodyEncodingForURI = false;
 
             String requestEncoding = this.req.getCharacterEncoding();
 
             if (uriEncoding != null)
               queryStringEncoding = uriEncoding;
             else if ((useBodyEncodingForURI) && 
               (requestEncoding != null)) {
               queryStringEncoding = requestEncoding;
             }
 
             try
             {
               retVal = URLDecoder.decode(queryString, queryStringEncoding);
             } catch (UnsupportedEncodingException e) {
               retVal = queryString;
             }
           }
         }
       }
     } else if (nameParts[0].equals("REMOTE")) {
       if (nameParts[1].equals("ADDR"))
         retVal = this.req.getRemoteAddr();
       else if (nameParts[1].equals("HOST"))
         retVal = this.req.getRemoteHost();
       else if (nameParts[1].equals("IDENT"))
         retVal = null;
       else if (nameParts[1].equals("PORT"))
         retVal = Integer.toString(this.req.getRemotePort());
       else if (nameParts[1].equals("USER"))
         retVal = this.req.getRemoteUser();
     }
     else if (nameParts[0].equals("REQUEST")) {
       if (nameParts[1].equals("METHOD")) {
         retVal = this.req.getMethod();
       } else if (nameParts[1].equals("URI"))
       {
         retVal = (String)this.req.getAttribute("javax.servlet.forward.request_uri");
         if (retVal == null)
           retVal = this.req.getRequestURI();
       }
     } else if (nameParts[0].equals("SCRIPT")) {
       String scriptName = this.req.getServletPath();
       if (nameParts[1].equals("FILENAME"))
         retVal = this.context.getRealPath(scriptName);
       else if (nameParts[1].equals("NAME"))
         retVal = scriptName;
     }
     else if (nameParts[0].equals("SERVER")) {
       if (nameParts[1].equals("ADDR")) {
         retVal = this.req.getLocalAddr();
       }
       if (nameParts[1].equals("NAME")) {
         retVal = this.req.getServerName();
       } else if (nameParts[1].equals("PORT")) {
         retVal = Integer.toString(this.req.getServerPort());
       } else if (nameParts[1].equals("PROTOCOL")) {
         retVal = this.req.getProtocol();
       } else if (nameParts[1].equals("SOFTWARE")) {
         StringBuffer rv = new StringBuffer(this.context.getServerInfo());
         rv.append(" ");
         rv.append(System.getProperty("java.vm.name"));
         rv.append("/");
         rv.append(System.getProperty("java.vm.version"));
         rv.append(" ");
         rv.append(System.getProperty("os.name"));
         retVal = rv.toString();
       }
     } else if (name.equalsIgnoreCase("UNIQUE_ID")) {
       retVal = this.req.getRequestedSessionId();
     }
     if (requiredParts != nameParts.length)
       return null;
     return retVal;
   }
 
   public Date getCurrentDate() {
     return new Date();
   }
 
   protected String nullToEmptyString(String string) {
     String retVal = string;
     if (retVal == null) {
       retVal = "";
     }
     return retVal;
   }
 
   protected String getPathWithoutFileName(String servletPath) {
     String retVal = null;
     int lastSlash = servletPath.lastIndexOf(47);
     if (lastSlash >= 0)
     {
       retVal = servletPath.substring(0, lastSlash + 1);
     }
     return retVal;
   }
 
   protected String getPathWithoutContext(String servletPath) {
     String retVal = null;
     int secondSlash = servletPath.indexOf(47, 1);
     if (secondSlash >= 0)
     {
       retVal = servletPath.substring(secondSlash);
     }
     return retVal;
   }
 
   protected String getAbsolutePath(String path) throws IOException {
     String pathWithoutContext = SSIServletRequestUtil.getRelativePath(this.req);
     String prefix = getPathWithoutFileName(pathWithoutContext);
     if (prefix == null) {
       throw new IOException("Couldn't remove filename from path: " + pathWithoutContext);
     }
     String fullPath = prefix + path;
     String retVal = RequestUtil.normalize(fullPath);
     if (retVal == null) {
       throw new IOException("Normalization yielded null on path: " + fullPath);
     }
     return retVal;
   }
 
   protected ServletContextAndPath getServletContextAndPathFromNonVirtualPath(String nonVirtualPath) throws IOException
   {
     if ((nonVirtualPath.startsWith("/")) || (nonVirtualPath.startsWith("\\"))) {
       throw new IOException("A non-virtual path can't be absolute: " + nonVirtualPath);
     }
     if (nonVirtualPath.indexOf("../") >= 0) {
       throw new IOException("A non-virtual path can't contain '../' : " + nonVirtualPath);
     }
     String path = getAbsolutePath(nonVirtualPath);
     ServletContextAndPath csAndP = new ServletContextAndPath(this.context, path);
     return csAndP;
   }
 
   protected ServletContextAndPath getServletContextAndPathFromVirtualPath(String virtualPath) throws IOException
   {
     if ((!(virtualPath.startsWith("/"))) && (!(virtualPath.startsWith("\\")))) {
       return new ServletContextAndPath(this.context, getAbsolutePath(virtualPath));
     }
     String normalized = RequestUtil.normalize(virtualPath);
     if (this.isVirtualWebappRelative) {
       return new ServletContextAndPath(this.context, normalized);
     }
     ServletContext normContext = this.context.getContext(normalized);
     if (normContext == null) {
       throw new IOException("Couldn't get context for path: " + normalized);
     }
 
     if (!(isRootContext(normContext))) {
       String noContext = getPathWithoutContext(normalized);
       if (noContext == null) {
         throw new IOException("Couldn't remove context from path: " + normalized);
       }
       return new ServletContextAndPath(normContext, noContext);
     }
     return new ServletContextAndPath(normContext, normalized);
   }
 
   protected boolean isRootContext(ServletContext servletContext)
   {
     return (servletContext == servletContext.getContext("/"));
   }
 
   protected ServletContextAndPath getServletContextAndPath(String originalPath, boolean virtual) throws IOException {
     ServletContextAndPath csAndP = null;
     if (this.debug > 0) {
       log("SSIServletExternalResolver.getServletContextAndPath( " + originalPath + ", " + virtual + ")", null);
     }
     if (virtual)
       csAndP = getServletContextAndPathFromVirtualPath(originalPath);
     else {
       csAndP = getServletContextAndPathFromNonVirtualPath(originalPath);
     }
     return csAndP;
   }
 
   protected URLConnection getURLConnection(String originalPath, boolean virtual) throws IOException {
     ServletContextAndPath csAndP = getServletContextAndPath(originalPath, virtual);
     ServletContext context = csAndP.getServletContext();
     String path = csAndP.getPath();
     URL url = context.getResource(path);
     if (url == null) {
       throw new IOException("Context did not contain resource: " + path);
     }
     URLConnection urlConnection = url.openConnection();
     return urlConnection;
   }
 
   public long getFileLastModified(String path, boolean virtual) throws IOException {
     long lastModified = 0L;
     try {
       URLConnection urlConnection = getURLConnection(path, virtual);
       lastModified = urlConnection.getLastModified();
     }
     catch (IOException localIOException) {
     }
     return lastModified;
   }
 
   public long getFileSize(String path, boolean virtual) throws IOException {
     long fileSize = -1L;
     try {
       URLConnection urlConnection = getURLConnection(path, virtual);
       fileSize = urlConnection.getContentLength();
     }
     catch (IOException localIOException) {
     }
     return fileSize;
   }
 
   public String getFileText(String originalPath, boolean virtual)
     throws IOException
   {
     try
     {
       ServletContextAndPath csAndP = getServletContextAndPath(originalPath, virtual);
       ServletContext context = csAndP.getServletContext();
       String path = csAndP.getPath();
       byte[] bytes = (byte[])null;
       String ext = ServletUtil.getUrlExtension(path);
       if ((".html".equals(ext)) || (".shtml".equals(ext)) || (".htm".equals(ext))) {
         bytes = getFileContent(path);
       } else {
         RequestDispatcher rd = context.getRequestDispatcher(path);
         if (rd == null) {
           throw new IOException("Couldn't get request dispatcher for path: " + path);
         }
         ByteArrayServletOutputStream basos = new ByteArrayServletOutputStream();
         ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(context, this.req, this.res, basos);
         rd.include(this.req, responseIncludeWrapper);
 
         responseIncludeWrapper.flushOutputStreamOrWriter();
         bytes = basos.toByteArray();
       }
       String retVal;
       if (this.inputEncoding == null)
         retVal = new String(bytes);
       else {
         retVal = new String(bytes, this.inputEncoding);
       }
 
       if ((retVal.equals("")) && (!(this.req.getMethod().equalsIgnoreCase("HEAD")))) {
         throw new IOException("Couldn't find file: " + path);
       }
       return retVal;
     } catch (Exception e) {
       throw new IOException("Couldn't include file: " + originalPath + " because of ServletException: " + 
         e.getMessage());
     }
   }
 
   private byte[] getFileContent(String path)
   {
     long current = System.currentTimeMillis();
     String realPath = Config.getContextRealPath() + path;
     Long lastTime = (Long)this.timeMap.get(realPath);
     byte[] bs = (byte[])null;
     if (lastTime == null) {
       synchronized (this) {
         lastTime = new Long(current);
         this.timeMap.put(path, lastTime);
         if (new File(realPath).exists()) {
           bs = FileUtil.readByte(realPath);
           this.fileMap.put(path, bs);
         } else {
           bs = "Include file is not exists!".getBytes();
         }
       }
     }
     else if (lastTime.longValue() < current - 3000L) {
       if (new File(realPath).lastModified() > lastTime.longValue())
         synchronized (this) {
           lastTime = new Long(current);
           this.timeMap.put(path, lastTime);
           if (new File(realPath).exists()) {
             bs = FileUtil.readByte(realPath);
             this.fileMap.put(path, bs);
           } else {
             bs = "Include file is not exists!".getBytes();
           }
           this.fileMap.put(path, bs);
         }
       else
         synchronized (this) {
           lastTime = new Long(current);
           this.timeMap.put(path, lastTime);
         }
     }
     else {
       bs = (byte[])this.fileMap.get(path);
     }
     return bs;
   }
 
   protected class ServletContextAndPath {
     protected ServletContext servletContext;
     protected String path;
 
     public ServletContextAndPath(ServletContext paramServletContext, String paramString) {
       this.servletContext = paramServletContext;
       this.path = path;
     }
 
     public ServletContext getServletContext() {
       return this.servletContext;
     }
 
     public String getPath() {
       return this.path;
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.SSIServletExternalResolver
 * JD-Core Version:    0.5.3
 */