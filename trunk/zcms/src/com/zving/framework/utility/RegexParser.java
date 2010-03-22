 package com.zving.framework.utility;
 
 import java.util.ArrayList;
 import java.util.regex.Matcher;
 import org.apache.oro.text.regex.MalformedPatternException;
 import org.apache.oro.text.regex.MatchResult;
 import org.apache.oro.text.regex.PatternCompiler;
 import org.apache.oro.text.regex.PatternMatcherInput;
 import org.apache.oro.text.regex.Perl5Compiler;
 import org.apache.oro.text.regex.Perl5Matcher;
 
 public class RegexParser
 {
   private String regexTemplate;
   private String[] itemFields;
   private org.apache.oro.text.regex.Pattern pattern;
   private boolean fullMatch = false;
 
   public static final java.util.regex.Pattern PField = java.util.regex.Pattern.compile("\\$\\{(\\w+?)(\\:[^\\}]+?)?\\}");
 
   public static final java.util.regex.Pattern PSpecial = java.util.regex.Pattern.compile("\\$\\{\\{(.+?)(\\|\\|[^\\}]+?)?\\}\\}");
 
   public RegexParser(String regexTemplate) {
     this.regexTemplate = regexTemplate;
     compileItem();
   }
 
   public RegexParser(String regexTemplate, boolean fullMatch) {
     this.fullMatch = fullMatch;
     this.regexTemplate = regexTemplate;
     compileItem();
   }
 
   private void compileItem() {
     this.regexTemplate = this.regexTemplate.replaceAll("\\r\\n", "\n");
     String[] lines = this.regexTemplate.split("\\n");
     StringBuffer sb = new StringBuffer();
     boolean firstFlag = true;
     for (int i = 0; i < lines.length; ++i) {
       String line = lines[i].trim();
       if (StringUtil.isEmpty(line)) {
         continue;
       }
       if (!(firstFlag)) {
         sb.append("\\s*");
       }
       firstFlag = false;
       sb.append(encodeRegex(line));
     }
     this.regexTemplate = sb.toString();
     Matcher matcher = PField.matcher(this.regexTemplate);
     int lastIndex = 0;
     ArrayList arr = new ArrayList();
     sb = new StringBuffer();
     String name;
     while (matcher.find(lastIndex)) {
       sb.append(this.regexTemplate.substring(lastIndex, matcher.start()));
       String type = matcher.group(1);
       name = matcher.group(2);
       if (!(StringUtil.isEmpty(name))) {
         sb.append("(");
       }
       if (type.equalsIgnoreCase("A"))
         sb.append("");
       else if (type.equalsIgnoreCase("D"))
         sb.append("\\d*?");
       else if (type.equalsIgnoreCase("W"))
         sb.append("\\w*?");
       else if (type.equalsIgnoreCase("-D"))
         sb.append("\\D*?");
       else if (type.equalsIgnoreCase("-W")) {
         sb.append("\\W*?");
       }
       if (!(StringUtil.isEmpty(name))) {
         sb.append(")");
         arr.add(name.substring(1));
       }
       lastIndex = matcher.end();
     }
     sb.append(this.regexTemplate.substring(lastIndex));
 
     this.regexTemplate = sb.toString();
     matcher = PSpecial.matcher(this.regexTemplate);
     sb = new StringBuffer();
     lastIndex = 0;
     while (matcher.find(lastIndex)) {
       sb.append(this.regexTemplate.substring(lastIndex, matcher.start()));
       String pattern = matcher.group(1);
       name = matcher.group(2);
       if (!(StringUtil.isEmpty(name))) {
         sb.append("(");
       }
       sb.append(pattern);
       if (!(StringUtil.isEmpty(name))) {
         sb.append(")");
         arr.add(name.substring(2));
       }
       lastIndex = matcher.end();
     }
     sb.append(this.regexTemplate.substring(lastIndex));
 
     this.itemFields = new String[arr.size()];
     for (int i = 0; i < arr.size(); ++i) {
       this.itemFields[i] = ((String)arr.get(i));
     }
     this.regexTemplate = sb.toString();
     this.regexTemplate = StringUtil.replaceEx(this.regexTemplate, "$", "\\$");
     this.regexTemplate = StringUtil.replaceEx(this.regexTemplate, "{", "\\{");
     this.regexTemplate = StringUtil.replaceEx(this.regexTemplate, "}", "\\}");
 
     if (this.fullMatch) {
       this.regexTemplate = "^" + this.regexTemplate + "$";
     }
     PatternCompiler compiler = new Perl5Compiler();
     try {
       this.pattern = compiler.compile(this.regexTemplate, 9);
     }
     catch (MalformedPatternException e) {
       e.printStackTrace();
     }
   }
 
   public static String encodeRegex(String str) {
     str = str.trim();
     str = StringUtil.replaceEx(str, "\"", "\\\"");
     str = StringUtil.replaceEx(str, "'", "\\'");
     str = StringUtil.replaceEx(str, "?", "\\?");
     str = StringUtil.replaceEx(str, "^", "\\^");
     str = StringUtil.replaceEx(str, "&", "\\&");
     str = StringUtil.replaceEx(str, "-", "\\-");
     str = StringUtil.replaceEx(str, "*", "\\*");
     str = StringUtil.replaceEx(str, ".", "\\.");
     str = StringUtil.replaceEx(str, "(", "\\(");
     str = StringUtil.replaceEx(str, ")", "\\)");
     str = StringUtil.replaceEx(str, "[", "\\[");
     str = StringUtil.replaceEx(str, "]", "\\]");
     str = StringUtil.replaceEx(str, "=", "\\=");
     str = StringUtil.replaceEx(str, "!", "\\!");
     str = StringUtil.replaceEx(str, "\t", "\\t");
     str = StringUtil.replaceEx(str, "\r", "\\r");
     return str;
   }
 
   public boolean matches(String content) {
     content = content.replaceAll("\\r\\n", "\n");
     PatternMatcherInput input = new PatternMatcherInput(content);
     Perl5Matcher matcher = new Perl5Matcher();
     return matcher.contains(input, this.pattern);
   }
 
   public String replace(String content, String replacement) {
     content = content.replaceAll("\\r\\n", "\n");
     PatternMatcherInput input = new PatternMatcherInput(content);
     Perl5Matcher matcher = new Perl5Matcher();
     StringBuffer sb = new StringBuffer();
     int lastIndex = 0;
     while (matcher.contains(input, this.pattern)) {
       MatchResult result = matcher.getMatch();
       sb.append(content.substring(lastIndex, input.getCurrentOffset() - result.length()));
       sb.append(replacement);
       lastIndex = input.getCurrentOffset();
     }
     sb.append(content.substring(lastIndex, content.length()));
     return sb.toString();
   }
 
   public MatchedMap[] getMatchedMaps(String content) {
     content = content.replaceAll("\\r\\n", "\n");
     ArrayList arr = new ArrayList();
     PatternMatcherInput input = new PatternMatcherInput(content);
     Perl5Matcher matcher = new Perl5Matcher();
     while (matcher.contains(input, this.pattern)) {
       MatchResult result = matcher.getMatch();
       MatchedMap map = new MatchedMap();
       for (int i = 0; i < this.itemFields.length; ++i) {
         map.put(this.itemFields[i], result.group(i + 1));
       }
       map.MatchedString = result.group(0);
       arr.add(map);
     }
     MatchedMap[] maps = new MatchedMap[arr.size()];
     for (int i = 0; i < arr.size(); ++i) {
       maps[i] = ((MatchedMap)arr.get(i));
     }
     return maps;
   }
 
   public String getRegexTemplate()
   {
     return this.regexTemplate;
   }
 
   public String[] getKeys() {
     ArrayList arr = new ArrayList();
     for (int i = 0; i < this.itemFields.length; ++i) {
       arr.add(this.itemFields[i]);
     }
     String[] r = new String[arr.size()];
     for (int i = 0; i < r.length; ++i) {
       r[i] = ((String)arr.get(i));
     }
     return r;
   }
 
   public static class MatchedMap extends Mapx
   {
     private static final long serialVersionUID = 1L;
     private String MatchedString;
 
     public String get(String key)
     {
       return ((String)super.get(key));
     }
 
     public String getMatchedString() {
       return this.MatchedString;
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.RegexParser
 * JD-Core Version:    0.5.3
 */