 package com.zving.framework.ssi;
 
 import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.io.OutputStreamWriter;
 import java.util.BitSet;
 
 public class URLEncoder
 {
   protected static final char[] hexadecimal = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 
     'E', 'F' };
 
   protected BitSet safeCharacters = new BitSet(256);
 
   public URLEncoder() {
     for (char i = 'a'; i <= 'z'; i = (char)(i + '\1')) {
       addSafeCharacter(i);
     }
     for (i = 'A'; i <= 'Z'; i = (char)(i + '\1')) {
       addSafeCharacter(i);
     }
     for (i = '0'; i <= '9'; i = (char)(i + '\1'))
       addSafeCharacter(i);
   }
 
   public void addSafeCharacter(char c)
   {
     this.safeCharacters.set(c);
   }
 
   public String encode(String path) {
     int maxBytesPerChar = 10;
     StringBuffer rewrittenPath = new StringBuffer(path.length());
     ByteArrayOutputStream buf = new ByteArrayOutputStream(maxBytesPerChar);
     OutputStreamWriter writer = null;
     try {
       writer = new OutputStreamWriter(buf, "UTF8");
     } catch (Exception e) {
       e.printStackTrace();
       writer = new OutputStreamWriter(buf);
     }
 
     for (int i = 0; i < path.length(); ++i) {
       int c = path.charAt(i);
       if (this.safeCharacters.get(c)) {
         rewrittenPath.append((char)c);
       }
       else {
         try {
           writer.write((char)c);
           writer.flush();
         } catch (IOException e) {
           buf.reset();
           break label207:
         }
         byte[] ba = buf.toByteArray();
         for (int j = 0; j < ba.length; ++j)
         {
           byte toEncode = ba[j];
           rewrittenPath.append('%');
           int low = toEncode & 0xF;
           int high = (toEncode & 0xF0) >> 4;
           rewrittenPath.append(hexadecimal[high]);
           rewrittenPath.append(hexadecimal[low]);
         }
         label207: buf.reset();
       }
     }
     return rewrittenPath.toString();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.URLEncoder
 * JD-Core Version:    0.5.3
 */