
package com.hxzy.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * <p>
 * 类名: FileUtil
 * </p>
 * <p>
 * 描述: 文件工具类
 * </p>
 */
public class FileUtil {

    /**
     * 描述: 文件复制缓冲区大小
     */
    private static int BUFFER_SIZE = 1048576;

    /**
     * 新建目录
     * 
     * @param folderPath
     *            String 如 c:/fqf
     * @return boolean 创建成功返回true,失败返回false
     */
    public static boolean newFolder(String folderPath) {
        try {
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                return myFilePath.mkdirs();
            }
            return true;
        } catch (Exception e) {
            System.out.println("create folder failed!");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 新建文件
     * 
     * @param filePathAndName
     *            String 文件路径及名称 如c:/fqf.txt
     * @param fileContent
     *            String 文件内容
     * @return boolean 创建成功返回true,失败返回false
     */
    public static boolean newFile(String filePathAndName, String fileContent) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            String strContent = fileContent;
            myFile.println(strContent);
            resultFile.flush();
            resultFile.close();
            return true;
        } catch (Exception e) {
            System.out.println("create file failed!");
            e.printStackTrace();
            return false;

        }
    }

    /**
     * 删除文件
     * 
     * @param filePathAndName
     *            String 文件路径及名称 如c:/fqf.txt
     * @return boolean 删除成功返回true,失败返回false
     */
    public static boolean delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myDelFile = new File(filePath);
            return myDelFile.delete();
        } catch (Exception e) {
            System.out.println("delete file failed");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除文件夹
     * 
     * @param folderPath
     *            String 文件夹路径及名称 如c:/fqf
     * @return boolean 删除成功返回true,失败返回false
     */
    public static boolean delFolder(String folderPath) {
        try {
            // 删除完里面所有内容
            if (delAllFile(folderPath) == false)
                return false;
            String filePath = folderPath;
            // filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
            return true;
        } catch (Exception e) {
            System.out.println("delete folder failed!");
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 删除文件夹里面的所有文件
     * 
     * @param path
     *            String 文件夹路径 如 c:/fqf
     */
    public static boolean delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                if (temp.delete() == false)
                    return false;
            }
            if (temp.isDirectory()) {
                // 先删除文件夹里面的文件
                if (delAllFile(path + "/" + tempList[i]) == false)
                    return false;
                // 再删除空文件夹
                if (delFolder(path + "/" + tempList[i]) == false)
                    return false;
            }
        }
        return true;
    }

    /**
     * 复制单个文件
     * 
     * @param oldPath
     *            String 原文件路径 如：c:/fqf.txt
     * @param newPath
     *            String 复制后路径 如：f:/fqf.txt
     * @return boolean 复制成功返回true,失败返回false
     */
    public static boolean copyFile(String oldPath, String newPath) {
        try {
            // int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.flush();
                fs.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println("copy file failed!");
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 复制整个文件夹内容
     * 
     * @param oldPath
     *            String 原文件路径 如：c:/fqf
     * @param newPath
     *            String 复制后路径 如：f:/fqf/ff
     * @return boolean 复制成功返回true,失败返回false
     */
    public static boolean copyFolder(String oldPath, String newPath) {
        try {
            // 如果文件夹不存在 则建立新文件夹
            if ((new File(newPath)).mkdirs() == false)
                return false;
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());
                    byte[] b = new byte[BUFFER_SIZE];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    if (copyFolder(oldPath + "/" + file[i], newPath + "/"
                            + file[i]) == false)
                        return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("copy folder failed!");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移动文件到指定目录
     * 
     * @param oldPath
     *            String 如：c:/fqf.txt
     * @param newPath
     *            String 如：d:/fqf.txt
     */
    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);
    }

    /**
     * 移动文件到指定目录
     * 
     * @param oldPath
     *            String 如：c:/fqf.txt
     * @param newPath
     *            String 如：d:/fqf.txt
     */
    public static void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);
    }

    /**
     * 描述: 判断文件或目录是否存在
     * 
     * @param filePath
     *            文件或目录路径
     * @return 存在返回true,不存在返回false
     */
    public static boolean isFileExist(String filePath) {
        return (new File(filePath)).exists();
    }

    /**
     * 
     * 描述: 返回当前文件名的后缀名
     * 
     * @param fileName
     *            当前的文件名
     * @return 文件的后缀名;
     */
    public static String getFileExe(String fileName) {
        if (fileName == null || fileName.equals("")) {
            return "";
        } else {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
    }

    /**
     * 
     * 描述:读文件到Byte[]
     * 
     * @param fileName
     *            文件路径
     * @return
     */
    public static byte[] readFileToByte(String fileName) {
        try {
            FileInputStream inFile = new FileInputStream(fileName);
            int i = inFile.available(); // 得到文件大小
            byte[] data = new byte[i];
            inFile.read(data); // 读数据
            inFile.close();
            return data;
        } catch (FileNotFoundException fnfe) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}