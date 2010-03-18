/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2006 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2006-5-26</p>
 * <p>���£�</p>
 */
package base.util;

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
 * ����: FileUtil
 * </p>
 * <p>
 * ����: �ļ�������
 * </p>
 */
public class FileUtil {

    /**
     * ����: �ļ����ƻ�������С
     */
    private static int BUFFER_SIZE = 1048576;

    /**
     * �½�Ŀ¼
     * 
     * @param folderPath
     *            String �� c:/fqf
     * @return boolean �����ɹ�����true,ʧ�ܷ���false
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
     * �½��ļ�
     * 
     * @param filePathAndName
     *            String �ļ�·�������� ��c:/fqf.txt
     * @param fileContent
     *            String �ļ�����
     * @return boolean �����ɹ�����true,ʧ�ܷ���false
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
     * ɾ���ļ�
     * 
     * @param filePathAndName
     *            String �ļ�·�������� ��c:/fqf.txt
     * @return boolean ɾ���ɹ�����true,ʧ�ܷ���false
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
     * ɾ���ļ���
     * 
     * @param folderPath
     *            String �ļ���·�������� ��c:/fqf
     * @return boolean ɾ���ɹ�����true,ʧ�ܷ���false
     */
    public static boolean delFolder(String folderPath) {
        try {
            // ɾ����������������
            if (delAllFile(folderPath) == false)
                return false;
            String filePath = folderPath;
            // filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // ɾ�����ļ���
            return true;
        } catch (Exception e) {
            System.out.println("delete folder failed!");
            e.printStackTrace();
            return false;
        }

    }

    /**
     * ɾ���ļ�������������ļ�
     * 
     * @param path
     *            String �ļ���·�� �� c:/fqf
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
                // ��ɾ���ļ���������ļ�
                if (delAllFile(path + "/" + tempList[i]) == false)
                    return false;
                // ��ɾ�����ļ���
                if (delFolder(path + "/" + tempList[i]) == false)
                    return false;
            }
        }
        return true;
    }

    /**
     * ���Ƶ����ļ�
     * 
     * @param oldPath
     *            String ԭ�ļ�·�� �磺c:/fqf.txt
     * @param newPath
     *            String ���ƺ�·�� �磺f:/fqf.txt
     * @return boolean ���Ƴɹ�����true,ʧ�ܷ���false
     */
    public static boolean copyFile(String oldPath, String newPath) {
        try {
            // int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // �ļ�����ʱ
                InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�
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
     * ���������ļ�������
     * 
     * @param oldPath
     *            String ԭ�ļ�·�� �磺c:/fqf
     * @param newPath
     *            String ���ƺ�·�� �磺f:/fqf/ff
     * @return boolean ���Ƴɹ�����true,ʧ�ܷ���false
     */
    public static boolean copyFolder(String oldPath, String newPath) {
        try {
            // ����ļ��в����� �������ļ���
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
                if (temp.isDirectory()) {// ��������ļ���
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
     * �ƶ��ļ���ָ��Ŀ¼
     * 
     * @param oldPath
     *            String �磺c:/fqf.txt
     * @param newPath
     *            String �磺d:/fqf.txt
     */
    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);
    }

    /**
     * �ƶ��ļ���ָ��Ŀ¼
     * 
     * @param oldPath
     *            String �磺c:/fqf.txt
     * @param newPath
     *            String �磺d:/fqf.txt
     */
    public static void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);
    }

    /**
     * ����: �ж��ļ���Ŀ¼�Ƿ����
     * 
     * @param filePath
     *            �ļ���Ŀ¼·��
     * @return ���ڷ���true,�����ڷ���false
     */
    public static boolean isFileExist(String filePath) {
        return (new File(filePath)).exists();
    }

    /**
     * 
     * ����: ���ص�ǰ�ļ����ĺ�׺��
     * 
     * @param fileName
     *            ��ǰ���ļ���
     * @return �ļ��ĺ�׺��;
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
     * ����:���ļ���Byte[]
     * 
     * @param fileName
     *            �ļ�·��
     * @return
     */
    public static byte[] readFileToByte(String fileName) {
        try {
            FileInputStream inFile = new FileInputStream(fileName);
            int i = inFile.available(); // �õ��ļ���С
            byte[] data = new byte[i];
            inFile.read(data); // ������
            inFile.close();
            return data;
        } catch (FileNotFoundException fnfe) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}