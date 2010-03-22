package com.zving.FCKeditor.uploader;

import com.zving.cms.datachannel.Deploy;
import com.zving.cms.datachannel.Publisher;
import com.zving.cms.dataservice.ColumnUtil;
import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Config;
import com.zving.framework.Constant;
import com.zving.framework.User;
import com.zving.framework.data.DataCollection;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.NumberUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.framework.utility.ZipUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.ImageUtilEx;
import com.zving.platform.pub.NoUtil;
import com.zving.platform.pub.OrderUtil;
import com.zving.platform.pub.VideoUtilEx;
import com.zving.schema.ZCAttachmentSchema;
import com.zving.schema.ZCAudioSchema;
import com.zving.schema.ZCCatalogSchema;
import com.zving.schema.ZCImageSchema;
import com.zving.schema.ZCVideoSchema;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

public class SimpleUploaderServlet extends HttpServlet {
	public static final String IMAGE = "Image";
	public static final String VIDEO = "Video";
	public static final String AUDIO = "Audio";
	public static final String ATTACH = "Attach";
	private static String baseDir;
	private static boolean debug = false;

	private static boolean enabled = false;
	public static Hashtable allowedExtensions;
	public static Hashtable deniedExtensions;

	public void init() throws ServletException {
		debug = new Boolean(getInitParameter("debug")).booleanValue();
		baseDir = Config.getValue("UploadDir");
		if (baseDir == null) {
			baseDir = "";
		}
		enabled = new Boolean(getInitParameter("enabled")).booleanValue();

		allowedExtensions = new Hashtable();
		deniedExtensions = new Hashtable();

		String[] arr = { "Image", "Video", "Audio", "Attach" };
		for (int i = 0; i < arr.length; ++i) {
			allowedExtensions.put(arr[i], stringToArrayList(getInitParameter("AllowedExtensions"
					+ arr[i])));
			deniedExtensions.put(arr[i], stringToArrayList(getInitParameter("DeniedExtensions"
					+ arr[i])));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (StringUtil.isEmpty(baseDir)) {
			baseDir = Config.getValue("UploadDir");
		}
		if (debug)
			System.out.println("--- BEGIN DOPOST ---");
		response.setContentType("text/html; charset=" + Constant.GlobalCharset);
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		String returnIDs = "";
		String returnValue = "0";
		String errorMessage = "";
		String taskID = "";
		String filePath = "";
		String fromObjec = null;

		if (!(enabled)) {
			returnValue = "1";
			errorMessage = "This file uploader is disabled. Please check the WEB-INF/web.xml file";
		} else {
			FileItemFactory fileFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fileFactory);
			upload.setHeaderEncoding(Constant.GlobalCharset);
			upload.setSizeMax(2097152000L);
			try {
				List items = upload.parseRequest(request);
				Mapx fields = new Mapx();
				Mapx files = new Mapx();
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						fields.put(item.getFieldName(), item.getString(Constant.GlobalCharset));
					} else {
						String OldFileName = item.getName();
						long size = item.getSize();
						if ((((OldFileName == null) || (OldFileName.equals("")))) && (size == 0L)) {
							continue;
						}
						System.out.println("-----UploadFileName:-----" + OldFileName);
						files.put(item.getFieldName(), item);
					}

				}

				String repeat = (String) fields.get("Repeat");

				fromObjec = (String) fields.get("FromObject");

				String[] arr = (String[]) null;
				if ("1".equals(repeat))
					arr = repeatUpload(files, fields);
				else {
					arr = upload(files, fields);
				}

				returnIDs = arr[0];
				returnValue = arr[1];
				errorMessage = arr[2];
				taskID = arr[3];
				if (arr.length > 4)
					filePath = arr[4];
			} catch (Exception ex) {
				if (debug)
					ex.printStackTrace();
				errorMessage = "上传文件失败";
			}
		}
		if ("activex".equalsIgnoreCase(fromObjec)) {
			out.print(filePath);
			out.close();
		} else {
			out.println("taskid:" + taskID);
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.onUploadCompleted(" + returnValue + ",'"
					+ returnIDs.toString() + "','" + errorMessage + "');");
			out.println("</script>");
			out.flush();
			out.close();
		}

		if (debug)
			System.out.println("--- END DOPOST ---");
	}

	private String[] repeatUpload(Mapx files, Mapx fields) throws Exception {
		StringBuffer returnIDs = new StringBuffer();
		String returnValue = "0";
		String errorMessage = "";
		String taskID = "";
		String SiteAlias;
		Iterator it;
		FileItem uplFile;
		String AbsolutePath;
		if ("Image".equalsIgnoreCase((String) fields.get("FileType"))) {
			ZCImageSchema image = new ZCImageSchema();
			image.setID(Long.parseLong(fields.get("RepeatID").toString()));
			if (!(image.fill())) {
				returnValue = "";
				errorMessage = "没有查找到原图片";
			}
			SiteAlias = SiteUtil.getAlias(image.getSiteID());
			it = files.keySet().iterator();
			while (it.hasNext()) {
				uplFile = (FileItem) files.get(it.next());
				AbsolutePath = getServletContext().getRealPath(
						new StringBuffer(String.valueOf(baseDir)).append("/").append(SiteAlias)
								.append("/").append(image.getPath()).toString())
						+ "/";
				uplFile.write(new File(AbsolutePath + image.getSrcFileName()));
				ImageUtilEx.afterUploadImage(image, AbsolutePath, fields);
			}
			image.setModifyTime(new Date());
			image.setModifyUser(User.getUserName());
			image.update();
		} else if ("Attach".equalsIgnoreCase((String) fields.get("FileType"))) {
			ZCAttachmentSchema attach = new ZCAttachmentSchema();
			attach.setID(Long.parseLong(fields.get("RepeatID").toString()));
			if (!(attach.fill())) {
				returnValue = "";
				errorMessage = "没有查找到原附件";
			}
			SiteAlias = SiteUtil.getAlias(attach.getSiteID());
			it = files.keySet().iterator();
			while (it.hasNext()) {
				uplFile = (FileItem) files.get(it.next());
				AbsolutePath = getServletContext().getRealPath(
						new StringBuffer(String.valueOf(baseDir)).append("/").append(SiteAlias)
								.append("/").append(attach.getPath()).toString())
						+ "/";
				uplFile.write(new File(AbsolutePath + attach.getSrcFileName()));
			}
			attach.setModifyTime(new Date());
			attach.setModifyUser(User.getUserName());
			attach.update();
		} else if ("Audio".equalsIgnoreCase((String) fields.get("FileType"))) {
			ZCAudioSchema audio = new ZCAudioSchema();
			audio.setID(Long.parseLong(fields.get("RepeatID").toString()));
			if (!(audio.fill())) {
				returnValue = "";
				errorMessage = "没有查找到原音频文件";
			}
			SiteAlias = SiteUtil.getAlias(audio.getSiteID());
			it = files.keySet().iterator();
			while (it.hasNext()) {
				uplFile = (FileItem) files.get(it.next());
				AbsolutePath = getServletContext().getRealPath(
						new StringBuffer(String.valueOf(baseDir)).append("/").append(SiteAlias)
								.append("/").append(audio.getPath()).toString())
						+ "/";
				uplFile.write(new File(AbsolutePath + audio.getSrcFileName()));
			}
			audio.setModifyTime(new Date());
			audio.setModifyUser(User.getUserName());
			audio.update();
		}
		return new String[] { returnIDs.toString(), returnValue, errorMessage, taskID };
	}

	private String[] upload(Mapx files, Mapx fields) throws Exception {
		StringBuffer returnIDs = new StringBuffer();
		String returnValue = "0";
		String errorMessage = "";
		String taskID = "";
		String filePath = "";
		String CatalogID = (String) fields.get("CatalogID");
		String AbsolutePath = (String) fields.get("AbsolutePath");
		String FileType = (String) fields.get("FileType");

		ZCCatalogSchema catalog = new ZCCatalogSchema();
		String innerCode = "";
		String siteAlias = "";
		String path = "";
		long siteID = 0L;

		if (CatalogID == null) {
			if ("Image".equals(FileType))
				CatalogID = new QueryBuilder(
						"select ID from ZCCatalog  where type='4' and Name ='默认图片'  and siteid=?",
						Application.getCurrentSiteID()).executeString();
			else if ("Video".equals(FileType))
				CatalogID = new QueryBuilder(
						"select ID from ZCCatalog  where type='5' and Name ='默认视频' and siteid=?",
						Application.getCurrentSiteID()).executeString();
			else if ("Attach".equals(FileType))
				CatalogID = new QueryBuilder(
						"select ID from ZCCatalog  where type='7' and Name ='默认附件' and siteid=?",
						Application.getCurrentSiteID()).executeString();
			else if ("Audio".equals(FileType)) {
				CatalogID = new QueryBuilder(
						"select ID from ZCCatalog  where type='6' and Name ='默认音频' and siteid=?",
						Application.getCurrentSiteID()).executeString();
			}
		}
		catalog.setID(Long.parseLong(CatalogID));
		if (!(catalog.fill())) {
			System.out.println("没有找到上传库");
		}
		siteID = catalog.getSiteID();
		siteAlias = SiteUtil.getAlias(siteID);
		innerCode = catalog.getInnerCode();
		String CatalogAlias = CatalogUtil.getPath(catalog.getID());
		path = "upload/" + FileType + "/" + CatalogAlias;
		AbsolutePath = getServletContext().getRealPath(
				new StringBuffer(String.valueOf(baseDir)).append("/").append(siteAlias).append("/")
						.append(path).toString())
				+ "/";
		System.out.println("文件上传path：" + path);
		System.out.println("文件上传AbsolutePath：" + AbsolutePath);

		if ((!("".equals(AbsolutePath))) && (AbsolutePath != null)) {
			File dir = new File(AbsolutePath);
			if (!(dir.exists())) {
				dir.mkdirs();
			}
		}
		fields.put("AbsolutePath", AbsolutePath);

		ArrayList fileList = new ArrayList();
		Transaction trans = new Transaction();
		DataCollection dc = new DataCollection();
		dc.putAll(fields);
		if ("Image".equalsIgnoreCase(FileType)) {
			String rootPath = Config.getContextPath() + Config.getValue("UploadDir") + "/"
					+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
			rootPath = rootPath.replaceAll("///", "/");
			rootPath = rootPath.replaceAll("//", "/");

			Object[] vs = files.valueArray();
			for (int m = files.size() - 1; m >= 0; --m) {
				FileItem uplFile = (FileItem) vs[m];
				String fileNameLong = uplFile.getName();
				fileNameLong = fileNameLong.replaceAll("\\\\", "/");
				String oldName = fileNameLong.substring(fileNameLong.lastIndexOf("/") + 1);
				String ext = getExtension(oldName);

				if (extIsAllowed(FileType, ext)) {
					if ("zip".equalsIgnoreCase(ext)) {
						String tempPath = getServletContext().getRealPath(
								new StringBuffer(String.valueOf(baseDir)).append(siteAlias)
										.toString())
								+ "/Temp/";
						new File(tempPath).mkdirs();
						File oldZipFile = new File(tempPath, oldName);
						uplFile.write(oldZipFile);
						String unZipDir = tempPath + oldName.substring(0, oldName.lastIndexOf("."));
						FileUtil.deleteFromDir(unZipDir);
						ZipUtil.unzip(tempPath + oldName, unZipDir, false);
						File[] imageFiles = new File(unZipDir).listFiles();
						Arrays.sort(imageFiles, new Comparator() {
							public int compare(Object o1, Object o2) {
								File file1 = (File) o1;
								File file2 = (File) o2;
								return (-file1.getName().compareTo(file2.getName()));
							}
						});
						for (int i = 0; i < imageFiles.length; ++i) {
							oldName = imageFiles[i].getName();
							ext = getExtension(oldName);
							oldName = oldName.substring(0, oldName.indexOf("."));
							if ((!(extIsAllowed(FileType, ext)))
									|| (!("Image".equalsIgnoreCase(FileType))))
								continue;
							long imageID = NoUtil.getMaxID("DocID");
							int random = NumberUtil.getRandomInt(10000);
							String srcFileName = imageID + random + "." + ext;
							FileUtil.copy(imageFiles[i], AbsolutePath + srcFileName);

							ZCImageSchema image = new ZCImageSchema();
							image.setID(imageID);
							returnIDs.append(imageID + ",");
							String fileinfo = fields.get(uplFile.getFieldName() + "Info")
									.toString();
							image.setName(oldName);
							if (fileinfo.equals(""))
								image.setInfo(oldName);
							else {
								image.setInfo(fileinfo);
							}
							image.setOldName(oldName);
							image.setSiteID(siteID);
							image.setCatalogID(Long.parseLong(CatalogID));
							image.setCatalogInnerCode(innerCode);
							image.setBranchInnerCode(catalog.getBranchInnerCode());
							image.setPath(path);
							if (("tif".equalsIgnoreCase(ext)) || ("tiff".equalsIgnoreCase(ext)))
								image
										.setFileName(imageID + NumberUtil.getRandomInt(10000)
												+ ".jpg");
							else {
								image.setFileName(imageID + NumberUtil.getRandomInt(10000) + "."
										+ ext);
							}
							image.setSrcFileName(srcFileName);
							image.setSuffix(ext);
							image.setWidth(0L);
							image.setHeight(0L);
							image.setOrderFlag(OrderUtil.getDefaultOrder());
							image.setFileSize(FileUtils.byteCountToDisplaySize(imageFiles[i]
									.length()));
							image.setHitCount(0L);
							image.setStickTime(0L);
							String ProduceTime = (String) fields.get("ProduceTime");
							if (StringUtil.isNotEmpty(ProduceTime)) {
								image.setProduceTime(DateUtil.parse(ProduceTime));
							}
							image.setAuthor((String) fields.get("Author"));
							image.setSystem("CMS");

							image.setAddTime(new Date());
							image.setAddUser(User.getUserName());
							image.setModifyTime(new Date());
							image.setModifyUser(User.getUserName());
							trans.add(image, 1);
							ArrayList imageList = null;
							try {
								System.out.println(fields);
								imageList = ImageUtilEx.afterUploadImage(image, AbsolutePath,
										fields);
							} catch (Exception e) {
								System.out.println(imageFiles[i].getName() + " 该图片已被损坏！");

							}

							if (StringUtil.isNotEmpty((String) fields.get("OtherIDs"))) {
								String[] OtherIDs = StringUtil.splitEx((String) fields
										.get("OtherIDs"), ",");
								for (int j = 0; j < OtherIDs.length; ++j) {
									ZCImageSchema imageClone = (ZCImageSchema) image.clone();
									imageClone.setID(NoUtil.getMaxID("DocID"));
									imageClone.setCatalogID(OtherIDs[j]);
									imageClone.setCatalogInnerCode(CatalogUtil
											.getInnerCode(OtherIDs[j]));
									trans.add(imageClone, 1);
								}

							}

							fileList.add(AbsolutePath + srcFileName);
							fileList.addAll(imageList);

							returnValue = "0";
							errorMessage = "";
						}

						oldZipFile.delete();
						FileUtil.delete(unZipDir);
					} else {
						long imageID = NoUtil.getMaxID("DocID");
						int random = NumberUtil.getRandomInt(10000);
						String srcFileName = imageID + random + "." + ext;
						uplFile.write(new File(AbsolutePath, srcFileName));

						ZCImageSchema image = new ZCImageSchema();
						image.setID(imageID);
						returnIDs.append(imageID + ",");
						Object obj = fields.get(uplFile.getFieldName() + "Name");
						String fileName = (obj == null) ? "无标题" : obj.toString();
						image.setName(fileName);
						image.setOldName(oldName.substring(0, oldName.lastIndexOf(".")));
						image.setSiteID(siteID);
						image.setCatalogID(Long.parseLong(CatalogID));
						image.setCatalogInnerCode(innerCode);
						image.setBranchInnerCode(catalog.getBranchInnerCode());
						image.setPath(path);
						if (("tif".equalsIgnoreCase(ext)) || ("tiff".equalsIgnoreCase(ext)))
							image.setFileName(imageID + NumberUtil.getRandomInt(10000) + ".jpg");
						else {
							image.setFileName(imageID + NumberUtil.getRandomInt(10000) + "." + ext);
						}

						image.setSrcFileName(srcFileName);
						image.setSuffix(ext);
						image.setWidth(0L);
						image.setHeight(0L);
						image.setOrderFlag(OrderUtil.getDefaultOrder());
						image.setFileSize(FileUtils.byteCountToDisplaySize(uplFile.getSize()));
						image.setHitCount(0L);
						image.setStickTime(0L);
						image.setInfo((String) fields.get(uplFile.getFieldName() + "Info"));
						String ProduceTime = (String) fields.get("ProduceTime");
						if (StringUtil.isNotEmpty(ProduceTime)) {
							image.setProduceTime(DateUtil.parse(ProduceTime));
						}
						image.setAuthor((String) fields.get("Author"));
						image.setSystem("CMS");

						image.setAddTime(new Date());
						image.setAddUser(User.getUserName());
						image.setModifyTime(new Date());
						image.setModifyUser(User.getUserName());

						trans.add(image, 1);
						ArrayList imageList = null;
						try {
							imageList = ImageUtilEx.afterUploadImage(image, AbsolutePath, fields);
						} catch (Exception e) {
							System.out.println("-------------" + fileName
									+ " 该图片已被损坏！-------------");
						}

						if (StringUtil.isNotEmpty((String) fields.get("OtherIDs"))) {
							String[] OtherIDs = StringUtil.splitEx((String) fields.get("OtherIDs"),
									",");
							for (int j = 0; j < OtherIDs.length; ++j) {
								ZCImageSchema imageClone = (ZCImageSchema) image.clone();
								imageClone.setID(NoUtil.getMaxID("DocID"));
								imageClone.setCatalogID(OtherIDs[j]);
								imageClone.setCatalogInnerCode(CatalogUtil
										.getInnerCode(OtherIDs[j]));
								trans.add(imageClone, 1);
							}

						}

						fileList.add(AbsolutePath + srcFileName);
						fileList.addAll(imageList);

						returnValue = "0";
						errorMessage = "";

						if ((m == 0) || (files.size() == 1))
							filePath = rootPath + path + "1_" + image.getFileName();
						else {
							filePath = rootPath + path + "1_" + image.getFileName() + "|";
						}
					}
				}
			}
			trans
					.add(new QueryBuilder(
							"update zccatalog set isdirty = 1,total = (select count(*) from zcimage where catalogID =?) where ID =?",
							catalog.getID(), catalog.getID()));
		} else {
			Iterator it;
			FileItem uplFile;
			String fileNameLong;
			String oldName;
			String ext;
			int random;
			String srcFileName;
			if ("Video".equalsIgnoreCase(FileType)) {
				it = files.keySet().iterator();
				while (it.hasNext()) {
					uplFile = (FileItem) files.get(it.next());
					fileNameLong = uplFile.getName();
					fileNameLong = fileNameLong.replaceAll("\\\\", "/");
					oldName = fileNameLong.substring(fileNameLong.lastIndexOf("/") + 1);
					ext = getExtension(oldName);

					if (extIsAllowed(FileType, ext)) {
						long videoID = NoUtil.getMaxID("DocID");
						random = NumberUtil.getRandomInt(10000);
						srcFileName = videoID + random + "." + ext;
						uplFile.write(new File(AbsolutePath, srcFileName));

						ZCVideoSchema video = new ZCVideoSchema();
						video.setID(videoID);
						returnIDs.append(videoID + ",");
						if (fields.get("Name") == null)
							video.setName(oldName);
						else {
							video.setName(fields.get("Name").toString());
						}

						video.setOldName(oldName.substring(0, oldName.lastIndexOf(".")));
						video.setSiteID(siteID);
						video.setCatalogID(Long.parseLong(CatalogID));
						video.setCatalogInnerCode(innerCode);
						video.setBranchInnerCode(catalog.getBranchInnerCode());
						String ProduceTime = (String) fields.get("ProduceTime");
						if (StringUtil.isNotEmpty(ProduceTime)) {
							video.setProduceTime(DateUtil.parse(ProduceTime));
						}
						String Integral = (String) fields.get("Integral");
						if (StringUtil.isNotEmpty(Integral)) {
							video.setIntegral(Integer.parseInt(Integral));
						}
						video.setPath(path);
						int random1 = NumberUtil.getRandomInt(10000);
						video.setFileName(videoID + random1 + ".flv");
						video.setSrcFileName(srcFileName);
						video.setSuffix(ext);
						video.setInfo(fields.get("Info").toString());
						video.setTag(fields.get("Tag").toString());
						video.setIsOriginal(fields.get("IsOriginal").toString());
						video.setFileSize(FileUtils.byteCountToDisplaySize(uplFile.getSize()));
						video.setHitCount(0L);
						video.setStickTime(0L);
						video.setSystem("CMS");
						video.setOrderFlag(OrderUtil.getDefaultOrder());
						video.setAddTime(new Date());
						video.setAddUser(User.getUserName());
						video.setModifyTime(new Date());
						video.setModifyUser(User.getUserName());

						video.setImageName(videoID + random1 + ".jpg");
						fileList.add(AbsolutePath + videoID + random1 + ".jpg");

						boolean hasImage = false;
						if (it.hasNext()) {
							hasImage = true;
							uplFile = (FileItem) files.get(it.next());
							fileNameLong = uplFile.getName();
							fileNameLong = fileNameLong.replaceAll("\\\\", "/");
							oldName = fileNameLong.substring(fileNameLong.lastIndexOf("/") + 1);
							ext = getExtension(oldName);
							uplFile.write(new File(AbsolutePath, video.getImageName()));
						}
						if (!(VideoUtilEx.afterUploadVideo(video, AbsolutePath, hasImage))) {
							returnValue = "0";
							errorMessage = "视频转化出错";
						}

						if (StringUtil.isNotEmpty((String) fields.get("OtherIDs"))) {
							String[] OtherIDs = StringUtil.splitEx((String) fields.get("OtherIDs"),
									",");
							for (int j = 0; j < OtherIDs.length; ++j) {
								ZCVideoSchema videoClone = (ZCVideoSchema) video.clone();
								videoClone.setID(NoUtil.getMaxID("DocID"));
								videoClone.setCatalogID(OtherIDs[j]);
								videoClone.setCatalogInnerCode(CatalogUtil
										.getInnerCode(OtherIDs[j]));
								trans.add(videoClone, 1);
							}

						}

						fileList.add(AbsolutePath + "0_" + video.getFileName());

						System.out.println(video.getStickTime());
						trans.add(video, 1);
					}
				}

				trans
						.add(new QueryBuilder(
								"update zccatalog set isdirty = 1,total = (select count(*) from zcvideo where catalogID =?) where ID =?",
								catalog.getID(), catalog.getID()));
			} else if ("Audio".equalsIgnoreCase(FileType)) {
				it = files.keySet().iterator();
				while (it.hasNext()) {
					uplFile = (FileItem) files.get(it.next());
					fileNameLong = uplFile.getName();
					fileNameLong = fileNameLong.replaceAll("\\\\", "/");
					oldName = fileNameLong.substring(fileNameLong.lastIndexOf("/") + 1);
					ext = getExtension(oldName);

					if (!(extIsAllowed(FileType, ext)))
						continue;
					if ("zip".equalsIgnoreCase(ext)) {
						String tempPath = getServletContext().getRealPath(
								new StringBuffer(String.valueOf(baseDir)).append(siteAlias)
										.toString())
								+ "/Temp/";
						new File(tempPath).mkdirs();
						File oldZipFile = new File(tempPath, oldName);
						uplFile.write(oldZipFile);
						String unZipDir = tempPath + oldName.substring(0, oldName.lastIndexOf("."));
						FileUtil.deleteFromDir(unZipDir);
						ZipUtil.unzip(tempPath + oldName, unZipDir, false);
						File[] audioFiles = new File(unZipDir).listFiles();
						Arrays.sort(audioFiles, new Comparator() {
							public int compare(Object o1, Object o2) {
								File file1 = (File) o1;
								File file2 = (File) o2;
								return (-file1.getName().compareTo(file2.getName()));
							}
						});
						for (int i = 0; i < audioFiles.length; ++i) {
							oldName = audioFiles[i].getName();
							ext = getExtension(oldName);
							oldName = oldName.substring(0, oldName.indexOf("."));
							if ((!(extIsAllowed(FileType, ext)))
									|| (!("Audio".equalsIgnoreCase(FileType))))
								continue;
							long audioID = NoUtil.getMaxID("DocID");
							random = NumberUtil.getRandomInt(10000);
							srcFileName = audioID + random + "." + ext;
							FileUtil.copy(audioFiles[i], AbsolutePath + srcFileName);

							ZCAudioSchema audio = new ZCAudioSchema();
							audio.setID(audioID);
							returnIDs.append(audioID + ",");

							audio.setName(oldName);

							audio.setOldName(oldName);
							audio.setSiteID(siteID);
							audio.setCatalogID(Long.parseLong(CatalogID));
							audio.setCatalogInnerCode(innerCode);
							audio.setBranchInnerCode(catalog.getBranchInnerCode());
							audio.setPath(path);
							audio.setFileName(audioID + NumberUtil.getRandomInt(10000) + ".jpg");
							audio.setSrcFileName(srcFileName);
							audio.setSuffix(ext);
							audio.setFileSize(FileUtils.byteCountToDisplaySize(audioFiles[i]
									.length()));
							audio.setSystem("CMS");
							audio.setOrderFlag(OrderUtil.getDefaultOrder());
							audio.setAddTime(new Date());
							audio.setAddUser(User.getUserName());

							String ProduceTime = (String) fields.get("ProduceTime");
							if (StringUtil.isNotEmpty(ProduceTime)) {
								audio.setProduceTime(DateUtil.parse(ProduceTime));
							}
							audio.setAuthor((String) fields.get("Author"));
							audio.setSystem("CMS");

							audio.setAddTime(new Date());
							audio.setAddUser(User.getUserName());
							audio.setModifyTime(new Date());
							audio.setModifyUser(User.getUserName());

							trans.add(audio, 1);
							trans.add(ColumnUtil.getValueFromRequest(Long.parseLong(CatalogID),
									audio.getID(), dc), 1);

							if (StringUtil.isNotEmpty((String) fields.get("OtherIDs"))) {
								String[] OtherIDs = StringUtil.splitEx((String) fields
										.get("OtherIDs"), ",");
								for (int j = 0; j < OtherIDs.length; ++j) {
									ZCAudioSchema audioClone = (ZCAudioSchema) audio.clone();
									audioClone.setID(NoUtil.getMaxID("DocID"));
									audioClone.setCatalogID(OtherIDs[j]);
									audioClone.setCatalogInnerCode(CatalogUtil
											.getInnerCode(OtherIDs[j]));
									trans.add(audioClone, 1);
								}

							}

							fileList.add(AbsolutePath + srcFileName);

							returnValue = "0";
							errorMessage = "";
						}

						oldZipFile.delete();
						FileUtil.delete(unZipDir);
					} else {
						long audioID = NoUtil.getMaxID("DocID");
						random = NumberUtil.getRandomInt(10000);
						srcFileName = audioID + random + "." + ext;
						uplFile.write(new File(AbsolutePath, srcFileName));

						fileList.add(AbsolutePath + srcFileName);

						ZCAudioSchema audio = new ZCAudioSchema();
						audio.setID(audioID);
						returnIDs.append(audioID + ",");
						audio.setName(fields.get(uplFile.getFieldName() + "Name").toString());
						audio.setOldName(oldName.substring(0, oldName.lastIndexOf(".")));
						audio.setSiteID(siteID);
						audio.setCatalogID(Long.parseLong(CatalogID));
						audio.setCatalogInnerCode(innerCode);
						audio.setBranchInnerCode(catalog.getBranchInnerCode());
						audio.setPath(path);
						audio.setFileName(srcFileName);
						audio.setSrcFileName(srcFileName);
						audio.setSuffix(ext);

						audio.setTag(fields.get(uplFile.getFieldName() + "Tag").toString());
						audio.setIsOriginal("N");
						if (fields.get(uplFile.getFieldName() + "IsOriginal") != null) {
							audio.setIsOriginal((String) fields.get(uplFile.getFieldName()
									+ "IsOriginal"));
						}
						audio.setFileSize(FileUtils.byteCountToDisplaySize(uplFile.getSize()));
						audio.setSystem("CMS");
						audio.setOrderFlag(OrderUtil.getDefaultOrder());
						audio.setAddTime(new Date());
						audio.setAddUser(User.getUserName());
						audio.setModifyTime(new Date());
						audio.setModifyUser(User.getUserName());
						trans.add(audio, 1);
					}
				}

				trans
						.add(new QueryBuilder(
								"update zccatalog set isdirty = 1,total = (select count(*) from zcaudio where catalogID =?) where ID =?",
								catalog.getID(), catalog.getID()));
			} else if ("Attach".equalsIgnoreCase(FileType)) {
				it = files.keySet().iterator();
				while (it.hasNext()) {
					uplFile = (FileItem) files.get(it.next());
					fileNameLong = uplFile.getName();
					fileNameLong = fileNameLong.replaceAll("\\\\", "/");
					oldName = fileNameLong.substring(fileNameLong.lastIndexOf("/") + 1);
					ext = getExtension(oldName);

					if (extIsAllowed(FileType, ext)) {
						long attachID = NoUtil.getMaxID("DocID");
						random = NumberUtil.getRandomInt(10000);
						srcFileName = attachID + random + "." + ext;
						uplFile.write(new File(AbsolutePath, srcFileName));

						fileList.add(AbsolutePath + srcFileName);

						ZCAttachmentSchema attachment = new ZCAttachmentSchema();
						attachment.setID(attachID);
						returnIDs.append(attachID + ",");
						attachment.setName(fields.getString(new StringBuffer(String.valueOf(uplFile
								.getFieldName())).append("Name").toString()));
						attachment.setOldName(oldName.substring(0, oldName.lastIndexOf(".")));
						attachment.setSiteID(siteID);
						attachment.setCatalogID(Long.parseLong(CatalogID));
						attachment.setCatalogInnerCode(innerCode);
						attachment.setBranchInnerCode(catalog.getBranchInnerCode());
						attachment.setPath(path);
						attachment.setImagePath(fields.getString("ImagePath"));
						attachment.setFileName(srcFileName);
						attachment.setSrcFileName(srcFileName);
						attachment.setSuffix(ext);
						attachment.setIsLocked(fields.getString(new StringBuffer(String
								.valueOf(uplFile.getFieldName())).append("Locked").toString()));
						if ("Y".equals(attachment.getIsLocked())) {
							attachment.setPassword(StringUtil.md5Hex(fields.getString(uplFile
									.getFieldName()
									+ "Password")));
						}

						attachment.setSystem("CMS");
						attachment.setFileSize(FileUtils.byteCountToDisplaySize(uplFile.getSize()));
						attachment.setAddTime(new Date());
						attachment.setAddUser("admin");
						attachment.setOrderFlag(OrderUtil.getDefaultOrder());
						attachment.setModifyTime(new Date());
						attachment.setModifyUser(User.getUserName());
						attachment.setProp1("0");
						trans.add(attachment, 1);
						trans.add(ColumnUtil.getValueFromRequest(Long.parseLong(CatalogID),
								attachment.getID(), dc), 1);
					}
				}
				trans
						.add(new QueryBuilder(
								"update zccatalog set isdirty = 1,total = (select count(*) from zcattachment where catalogID =?) where ID =?",
								catalog.getID(), catalog.getID()));
			}
		}

		if ((StringUtil.isNotEmpty(catalog.getIndexTemplate()))
				|| (StringUtil.isNotEmpty(catalog.getListTemplate()))
				|| (StringUtil.isNotEmpty(catalog.getDetailTemplate()))) {
			Publisher p = new Publisher();
			p.publishCatalog(catalog.getID(), false, true);
		}

		Deploy d = new Deploy();
		trans.add(d.getJobs(siteID, fileList), 1);

		trans.commit();
		int t = returnIDs.lastIndexOf(",");
		if (t != -1) {
			returnIDs.deleteCharAt(t);
		}
		return new String[] { returnIDs.toString(), returnValue, errorMessage, taskID, filePath };
	}

	private static String getNameWithoutExtension(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	private ArrayList stringToArrayList(String str) {
		String[] strArr = str.split("\\|");
		ArrayList tmp = new ArrayList();
		if (str.length() > 0) {
			for (int i = 0; i < strArr.length; ++i) {
				tmp.add(strArr[i].toLowerCase());
			}
		}
		return tmp;
	}

	private boolean extIsAllowed(String fileType, String ext) {
		ext = ext.toLowerCase();
		ArrayList allowList = (ArrayList) allowedExtensions.get(fileType);
		ArrayList denyList = (ArrayList) deniedExtensions.get(fileType);
		if (fileType.equals("Attach")) {
			return (!(denyList.contains(ext.toLowerCase())));
		}

		return (allowList.contains(ext.toLowerCase()));
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.FCKeditor.uploader.SimpleUploaderServlet JD-Core Version: 0.5.3
 */