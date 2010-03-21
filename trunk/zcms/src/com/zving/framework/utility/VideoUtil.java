 package com.zving.framework.utility;
 
 import com.zving.framework.Config;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 
 public class VideoUtil
 {
   public static final String _ConvertAvi2Flv = " -of lavf -oac mp3lame -lameopts abr:br=56 -ovc lavc -lavcopts vcodec=flv:vbitrate=200:mbd=2:mv0:trell:v4mv:cbp:last_pred=3:dia=4:cmp=6:vb_strategy=1 -vf scale=512:-3 -ofps 12\t -srate 22050";
   public static final String _ConvertRm2Flv = " -of lavf -oac mp3lame -lameopts abr:br=56 -ovc lavc -lavcopts vcodec=flv:vbitrate=200:mbd=2:mv0:trell:v4mv:cbp:last_pred=3 -srate 22050";
   public static final String _Identify = " -nosound -vc dummy -vo null";
 
   public static void main(String[] args)
   {
   }
 
   public static boolean captureDefaultImage(String src, String destImage, int duration)
   {
     if (duration < 30) {
       return captureImage(src, destImage, duration / 3);
     }
     return captureImage(src, destImage, 15);
   }
 
   public static boolean captureImage(String src, String destImage, int startSecond)
   {
     return captureImage(src, destImage, startSecond, 240, 180);
   }
 
   public static boolean captureImage(String src, String destImage, int ss, int width, int height) {
     return exec("\"" + Config.getContextRealPath() + "Tools/" + "ffmpeg\" -i " + "\"" + src + "\"" + 
       " -y -f image2 -ss " + ss + " -t 0.001 -s " + width + "*" + height + " \"" + destImage + "\"");
   }
 
   public static boolean convert2Flv(String src, String dest)
   {
     if (src.toLowerCase().lastIndexOf(".flv") != -1)
       return true;
     if ((src.toLowerCase().lastIndexOf(".rm") != -1) || (src.toLowerCase().lastIndexOf(".rmvb") != -1)) {
       return exec("\"" + Config.getContextRealPath() + "Tools/" + "mencoder\" " + "\"" + src + "\"" + " -o " + 
         "\"" + dest + "\"" + " -of lavf -oac mp3lame -lameopts abr:br=56 -ovc lavc -lavcopts vcodec=flv:vbitrate=200:mbd=2:mv0:trell:v4mv:cbp:last_pred=3 -srate 22050");
     }
     return exec("\"" + Config.getContextRealPath() + "Tools/" + "mencoder\" " + "\"" + src + "\"" + " -o " + 
       "\"" + dest + "\"" + " -of lavf -oac mp3lame -lameopts abr:br=56 -ovc lavc -lavcopts vcodec=flv:vbitrate=200:mbd=2:mv0:trell:v4mv:cbp:last_pred=3:dia=4:cmp=6:vb_strategy=1 -vf scale=512:-3 -ofps 12\t -srate 22050");
   }
 
   public static boolean convert2FlvSlit(String src, String dest, int ss, int endpos)
   {
     if ((src.toLowerCase().lastIndexOf(".rm") != -1) || (src.toLowerCase().lastIndexOf(".rmvb") != -1)) {
       return exec("\"" + Config.getContextRealPath() + "Tools/" + "mencoder\" " + "\"" + src + "\"" + " -o " + 
         "\"" + dest + "\"" + " -of lavf -oac mp3lame -lameopts abr:br=56 -ovc lavc -lavcopts vcodec=flv:vbitrate=200:mbd=2:mv0:trell:v4mv:cbp:last_pred=3 -srate 22050");
     }
     return exec("\"" + Config.getContextRealPath() + "Tools/" + "mencoder\" " + "\"" + src + "\"" + " -o " + 
       "\"" + dest + "\"" + " -ss " + ss + " -endpos " + endpos + " " + " -of lavf -oac mp3lame -lameopts abr:br=56 -ovc lavc -lavcopts vcodec=flv:vbitrate=200:mbd=2:mv0:trell:v4mv:cbp:last_pred=3:dia=4:cmp=6:vb_strategy=1 -vf scale=512:-3 -ofps 12\t -srate 22050");
   }
 
   public static int getDuration(String src)
   {
     String command = "\"" + Config.getContextRealPath() + "Tools/" + "mplayer\" -identify " + "\"" + src + "\"" + 
       " -nosound -vc dummy -vo null";
     int duration = 0;
     try {
       LogUtil.info("Video.getDuration:" + command);
       Process process = Runtime.getRuntime().exec(command);
 
       InputStream is = process.getInputStream();
       BufferedReader br = new BufferedReader(new InputStreamReader(is));
       String line = null;
       try {
         while ((line = br.readLine()) != null) {
           System.out.println(line);
           if (line.indexOf("ID_LENGTH=") > -1) {
             duration = (int)Math.ceil(Double.parseDouble(line.substring(10)));
           }
 
         }
 
       }
       catch (IOException e)
       {
         e.printStackTrace();
         return duration;
       }
     }
     catch (IOException e) {
       e.printStackTrace();
       return duration;
     }
     LogUtil.info("VodeoUtil duration:" + duration);
     return duration;
   }
 
   public static int[] getWidthHeight(String src) {
     String command = "\"" + Config.getContextRealPath() + "Tools/" + "mplayer\" -identify " + "\"" + src + "\"" + 
       " -nosound -vc dummy -vo null";
     int[] WidthHeight = new int[2];
     try {
       LogUtil.info("VideoUtil.getWidthHeight:" + command);
       Process process = Runtime.getRuntime().exec(command);
 
       InputStream is = process.getInputStream();
       BufferedReader br = new BufferedReader(new InputStreamReader(is));
       String line = null;
       try {
         while ((line = br.readLine()) != null) {
           System.out.println(line);
           if (line.indexOf("ID_VIDEO_WIDTH=") > -1) {
             WidthHeight[0] = (int)Math.ceil(Double.parseDouble(line.substring(15)));
           }
           if (line.indexOf("ID_VIDEO_HEIGHT=") > -1)
             WidthHeight[1] = (int)Math.ceil(Double.parseDouble(line.substring(16)));
         }
       }
       catch (IOException e) {
         e.printStackTrace();
         return WidthHeight;
       }
     }
     catch (IOException e) {
       e.printStackTrace();
       return WidthHeight;
     }
     LogUtil.info("VideoUtil WidthHeight:" + WidthHeight[0] + "x" + WidthHeight[1]);
     return WidthHeight;
   }
 
   public static boolean exec(String command) {
     try {
       LogUtil.info("VideoUtil.exec:" + command);
       Process process = Runtime.getRuntime().exec(command);
 
       InputStream is1 = process.getInputStream();
       new Thread(new Runnable(is1) {
         public void run() {
           BufferedReader br = new BufferedReader(new InputStreamReader(VideoUtil.this));
           String line = null;
           try {
             while ((line = br.readLine()) != null)
               System.out.println(line);
           } catch (IOException e) {
             e.printStackTrace();
           }
         }
       }).start();
       InputStream is2 = process.getErrorStream();
       BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
       StringBuffer buf = new StringBuffer();
       String line = null;
       while ((line = br2.readLine()) != null)
         buf.append(line);
       LogUtil.info("VideoUtil 输出为：" + buf);
     } catch (IOException e) {
       e.printStackTrace();
       return false;
     }
     return true;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.VideoUtil
 * JD-Core Version:    0.5.3
 */