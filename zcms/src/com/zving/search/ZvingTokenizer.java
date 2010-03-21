 package com.zving.search;
 
 import java.io.IOException;
 import java.io.Reader;
 import org.apache.lucene.analysis.Token;
 import org.apache.lucene.analysis.TokenStream;
 import org.apache.lucene.analysis.Tokenizer;
 import org.wltea.analyzer.lucene.IKAnalyzer;
 
 public class ZvingTokenizer extends Tokenizer
 {
   public TokenStream st;
   public Token currentToken;
   public boolean splitingFlag = false;
   public int offSet;
   public StringBuffer sb = new StringBuffer();
 
   public ZvingTokenizer(String fieldName, Reader reader) {
     this.st = new IKAnalyzer().tokenStream(fieldName, reader);
     this.input = reader;
   }
 
   public Token next() throws IOException {
     if (!(this.splitingFlag)) {
       this.currentToken = this.st.next();
       if (this.currentToken == null) {
         this.splitingFlag = true;
       } else {
         this.sb.append(new String(this.currentToken.termBuffer(), 0, this.currentToken.termLength()));
         return this.currentToken;
       }
     }
     if ((this.splitingFlag) && 
       (this.offSet < this.sb.length())) {
       return new Token(this.sb.substring(this.offSet, this.offSet + 1), this.offSet, ++this.offSet);
     }
 
     return null;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.ZvingTokenizer
 * JD-Core Version:    0.5.3
 */