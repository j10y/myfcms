package laji;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * �ļ�ѹ����ѹ������
 * 
 * @author huwei(jshuwei.org.cn)
 * @since 1.2
 * 
 */
public class ZipUtil {
	/**
	 * ѹ��ZIP�ļ�����baseDirNameĿ¼�µ������ļ�ѹ����targetFileName.ZIP�ļ���
	 * 
	 * @param baseDirName
	 *            ��Ҫѹ�����ļ��ĸ�Ŀ¼
	 * @param targetFileName
	 *            ѹ�������ɵ��ļ���
	 */
	public static void zipFile(String baseDirName, String targetFileName) {
		if (baseDirName == null) {
			return;
		}
		File file = new File(baseDirName);
		if (!file.exists()) {
			return;
		}
		String baseDirPath = file.getAbsolutePath();
		File targetFile = new File(targetFileName);
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetFile));
			if (file.isFile()) {
				fileToZip(baseDirPath, file, out);
			} else {
				dirToZip(baseDirPath, file, out);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ѹ��ZIP�ļ�����ZIP�ļ�������ݽ�ѹ��targetBaseDirNameĿ¼��
	 * 
	 * @param zipFileName
	 *            ����ѹ����ZIP�ļ���
	 * @param targetBaseDirName
	 *            Ŀ��Ŀ¼
	 */
	@SuppressWarnings("unchecked")
	public static void unzipFile(String zipFileName, String targetBaseDirName) {
		if (!targetBaseDirName.endsWith(File.separator)) {
			targetBaseDirName += File.separator;
		}
		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			ZipEntry entry = null;
			String entryName = null;
			String targetFileName = null;
			byte[] buffer = new byte[4096];
			int bytes_read;
			Enumeration entrys = zipFile.getEntries();// .entries();
			while (entrys.hasMoreElements()) {
				entry = (ZipEntry) entrys.nextElement();
				entryName = entry.getName();
				targetFileName = targetBaseDirName + entryName;
				if (entry.isDirectory()) {
					new File(targetFileName).mkdirs();
					continue;
				} else {
					new File(targetFileName).getParentFile().mkdirs();
				}
				File targetFile = new File(targetFileName);
				FileOutputStream os = new FileOutputStream(targetFile);
				InputStream is = zipFile.getInputStream(entry);
				while ((bytes_read = is.read(buffer)) != -1) {
					os.write(buffer, 0, bytes_read);
				}
				os.close();
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void dirToZip(String baseDirPath, File dir, ZipOutputStream out) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files.length == 0) {
				ZipEntry entry = new ZipEntry(getEntryName(baseDirPath, dir));
				try {
					out.putNextEntry(entry);
					out.closeEntry();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					fileToZip(baseDirPath, files[i], out);
				} else {
					dirToZip(baseDirPath, files[i], out);
				}
			}
		}
	}

	private static void fileToZip(String baseDirPath, File file, ZipOutputStream out) {
		FileInputStream in = null;
		ZipEntry entry = null;
		byte[] buffer = new byte[4096];
		int bytes_read;
		if (file.isFile()) {
			try {
				in = new FileInputStream(file);
				entry = new ZipEntry(getEntryName(baseDirPath, file));
				out.putNextEntry(entry);
				while ((bytes_read = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytes_read);
				}
				out.closeEntry();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getEntryName(String baseDirPath, File file) {
		if (!baseDirPath.endsWith(File.separator)) {
			baseDirPath += File.separator;
		}
		String filePath = file.getAbsolutePath();
		if (file.isDirectory()) {
			filePath += "/";
		}
		int index = filePath.indexOf(baseDirPath);
		return filePath.substring(index + baseDirPath.length());
	}
}