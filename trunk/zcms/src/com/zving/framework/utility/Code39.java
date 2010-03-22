 package com.zving.framework.utility;
 
 import org.apache.commons.logging.Log;
 
 class Code39
 {
   private static String[][] codeSet = { { "000110100", "0" }, { "100100001", "1" }, { "001100001", "2" }, 
     { "101100000", "3" }, { "000110001", "4" }, { "100110000", "5" }, { "001110000", "6" }, 
     { "000100101", "7" }, { "100100100", "8" }, { "001100100", "9" }, { "100001001", "A" }, 
     { "001001001", "B" }, { "101001000", "C" }, { "000011001", "D" }, { "100011000", "E" }, 
     { "001011000", "F" }, { "000001101", "G" }, { "100001100", "H" }, { "001001100", "I" }, 
     { "000011100", "J" }, { "100000011", "K" }, { "001000011", "L" }, { "101000010", "M" }, 
     { "000010011", "N" }, { "100010010", "O" }, { "001010010", "P" }, { "000000111", "Q" }, 
     { "100000110", "R" }, { "001000110", "S" }, { "000010110", "T" }, { "110000001", "U" }, 
     { "011000001", "V" }, { "111000000", "W" }, { "010010001", "X" }, { "110010000", "Y" }, 
     { "011010000", "Z" }, { "010000101", "-" }, { "110000100", "." }, { "011000100", " " }, 
     { "010101000", "$" }, { "010100010", "/" }, { "010001010", "+" }, { "000101010", "%" }, 
     { "010010100", "*" } };
 
   public static String getCode(char c)
   {
     for (int i = 0; i < 44; ++i) {
       if (codeSet[i][1].charAt(0) == c) {
         return codeSet[i][0];
       }
     }
     return "";
   }
 
   public static String transferCode(String str) {
     StringBuffer bf = new StringBuffer();
     String t_Str = "";
     char[] cArray = str.toUpperCase().toCharArray();
 
     bf.append(getCode('*'));
     bf.append('0');
     for (int i = 0; i < cArray.length; ++i) {
       t_Str = getCode(cArray[i]);
       if (!(t_Str.equals(""))) {
         bf.append(t_Str);
         bf.append('0');
       } else {
         LogUtil.getLogger().warn("Code39:非法字符");
         return "";
       }
     }
     bf.append(getCode('*'));
     return bf.toString();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.Code39
 * JD-Core Version:    0.5.3
 */