 package com.zving.framework.ssi;
 
 import com.zving.framework.Config;
 import com.zving.framework.Constant;
 import com.zving.framework.extend.ExtendManager;
 import com.zving.framework.utility.StringUtil;
 import java.io.ByteArrayInputStream;
 import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStream;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.io.Reader;
 import java.io.Writer;
 import java.util.Date;
 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletContext;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 public class SSIFilter
   implements Filter
 {
   protected FilterConfig config = null;
 
   protected int debug = 0;
 
   protected Long expires = null;
 
   protected boolean isVirtualWebappRelative = true;
 
   public void init(FilterConfig config)
     throws ServletException
   {
     this.config = config;
 
     String value = null;
     try {
       value = config.getInitParameter("debug");
       this.debug = Integer.parseInt(value);
     }
     catch (Throwable localThrowable)
     {
     }
 
     try
     {
       value = config.getInitParameter("expires");
       if (StringUtil.isEmpty(value)) {
         value = "0";
       }
       this.expires = Long.valueOf(value);
     } catch (NumberFormatException e) {
       this.expires = null;
       config.getServletContext().log("Invalid format for expires initParam; expected integer (seconds)");
     }
     catch (Throwable localThrowable1) {
     }
     if (this.debug > 0)
       config.getServletContext().log("SSIFilter.init() SSI invoker started with 'debug'=" + this.debug);
   }
 
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
   {
     if (ExtendManager.hasAction("BeforeSSIFilter")) {
       ExtendManager.executeAll("BeforeSSIFilter", new Object[] { request, response, chain });
     }
 
     HttpServletRequest req = (HttpServletRequest)request;
     HttpServletResponse res = (HttpServletResponse)response;
 
     if ((Config.ServletMajorVersion == 2) && (Config.ServletMinorVersion == 3))
       response.setContentType("text/html;charset=" + Constant.GlobalCharset);
     else {
       response.setCharacterEncoding(Constant.GlobalCharset);
     }
     request.setCharacterEncoding(Constant.GlobalCharset);
 
     req.setAttribute("org.apache.catalina.ssi.SSIServlet", "true");
 
     ByteArrayServletOutputStream basos = new ByteArrayServletOutputStream();
     ResponseIncludeWrapper responseIncludeWrapper = new ResponseIncludeWrapper(this.config.getServletContext(), req, 
       res, basos);
 
     chain.doFilter(req, responseIncludeWrapper);
 
     responseIncludeWrapper.flushOutputStreamOrWriter();
     byte[] bytes = basos.toByteArray();
 
     String encoding = res.getCharacterEncoding();
 
     SSIExternalResolver ssiExternalResolver = new SSIServletExternalResolver(this.config.getServletContext(), req, res, 
       this.isVirtualWebappRelative, this.debug, encoding);
     SSIProcessor ssiProcessor = new SSIProcessor(ssiExternalResolver, this.debug);
 
     Reader reader = new InputStreamReader(new ByteArrayInputStream(bytes), encoding);
     ByteArrayOutputStream ssiout = new ByteArrayOutputStream();
     PrintWriter writer = new PrintWriter(new OutputStreamWriter(ssiout, encoding));
 
     long lastModified = ssiProcessor.process(reader, responseIncludeWrapper.getLastModified(), writer);
 
     writer.flush();
     bytes = ssiout.toByteArray();
 
     if (this.expires != null) {
       res.setDateHeader("expires", new Date().getTime() + this.expires.longValue() * 1000L);
     }
     if (lastModified > 0L) {
       res.setDateHeader("last-modified", lastModified);
     }
     res.setContentLength(bytes.length);
 
     res.setContentType("text/html;charset=" + Constant.GlobalCharset);
     try
     {
       OutputStream out = res.getOutputStream();
       out.write(bytes);
     } catch (Throwable t) {
       Writer out = res.getWriter();
       out.write(new String(bytes));
     }
   }
 
   public void destroy()
   {
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.SSIFilter
 * JD-Core Version:    0.5.3
 */