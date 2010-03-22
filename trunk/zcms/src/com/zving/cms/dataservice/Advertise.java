package com.zving.cms.dataservice;

import com.zving.cms.datachannel.Deploy;
import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCAdPositionSchema;
import com.zving.schema.ZCAdvertisementSchema;
import com.zving.schema.ZCAdvertisementSet;
import com.zving.schema.ZCImageSchema;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Advertise extends Page {
	public static Mapx ADTypes = new Mapx();

	static {
		ADTypes.put("image", "图片");
		ADTypes.put("flash", "动画");
		ADTypes.put("text", "文本");
		ADTypes.put("code", "代码");
		ADTypes.put("", "无广告");
	}

	public static Mapx init(Mapx params) {
		String PosID = params.getString("PosID");
		params.put("PositionID", PosID);
		params.put("PositionName", new QueryBuilder(
				"select PositionName from zcadposition where ID = ?", PosID).executeString());
		return params;
	}

	public static void dg1DataBind(DataGridAction dga) {
		String PosID = dga.getParam("PosID");
		String SearchContent = dga.getParam("SearchContent");
		StringBuffer conditions = new StringBuffer();
		conditions.append(" where PositionID = ?");
		if (StringUtil.isNotEmpty(SearchContent)) {
			conditions.append(" and AdName like '");
			conditions.append("%");
			conditions.append(SearchContent.trim());
			conditions.append("%'");
		}
		dga.setTotal(new QueryBuilder("select count(*) from zcadvertisement" + conditions, PosID));
		DataTable dt = new QueryBuilder(
				"select id,AdName,ADType,(select b.PositionName from zcadposition b where b.id=zcadvertisement.positionid) PositionName,case isopen when 'Y' then '√' else '' end isopen,HitCount,AddTime from zcadvertisement "
						+ conditions + " order by id desc", PosID).executeDataTable();
		dt.decodeColumn("ADType", ADTypes);
		dga.bindData(dt);
	}

	public static Mapx DialogInit(Mapx params) {
		String id = (String) params.get("ID");
		String type = (String) params.get("Type");
		String PosID = params.getString("PosID");
		String PositionID = params.getString("PositionID");
		if (!(StringUtil.isEmpty(PosID))) {
			if (!("New".equals(type))) {
				id = (String) new QueryBuilder(
						"select ID from zcadvertisement where PositionID = ? and IsOpen = 'Y'",
						PosID).executeOneValue();
				if ((StringUtil.isEmpty(id)) || (id.equals("null"))) {
					id = "";
				}
			}
			PositionID = PosID;
		}

		if (StringUtil.isNotEmpty(id)) {
			ZCAdvertisementSchema ad = new ZCAdvertisementSchema();
			ad.setID(id);
			ad.fill();
			PositionID = String.valueOf(ad.getPositionID());
			String StartTime = String.valueOf(ad.getStartTime());
			String EndTime = String.valueOf(ad.getEndTime());
			if (StringUtil.isNotEmpty(StartTime)) {
				params.put("StartDate", StartTime.substring(0, 10));
				params.put("STime", StartTime.substring(11, 19));
			}
			if (StringUtil.isNotEmpty(EndTime)) {
				params.put("EndDate", EndTime.substring(0, 10));
				params.put("ETime", EndTime.substring(11, 19));
			}
			params.putAll(ad.toMapx());
			if (ad.getAdType().equals("code")) {
				params.put("AdContent", ad.getAdContent().replaceAll("\"", "'"));
			}
		}
		String PositionType = new QueryBuilder(
				"select PositionType from zcadposition where ID = ?", PositionID).executeString();
		params.put("PositionID", PositionID);
		params.put("PositionType", PositionType);
		params.put("PositionName", new QueryBuilder(
				"select PositionName from zcadposition where ID = ?", PositionID).executeString());
		params.put("PositionTypeName", AdvertiseLayout.PosTypes.get(PositionType));
		params.put("imgADLinkUrl", "http://");
		params.put("textLinkUrl", "http://");
		params.put("showAlt", "Y");
		if (PositionType.equalsIgnoreCase("imagechange"))
			params.put("AdTypeOptions", "<span value='image' selected>图片</span>");
		else if ((PositionType.equalsIgnoreCase("fixure"))
				|| (PositionType.equalsIgnoreCase("float"))
				|| (PositionType.equalsIgnoreCase("couplet")))
			params.put("AdTypeOptions",
					"<span value='image' selected>图片</span><span value='flash'>动画</span>");
		else if (PositionType.equalsIgnoreCase("code"))
			params.put("AdTypeOptions", "<span value='code' selected>代码</span>");
		else if (PositionType.equalsIgnoreCase("banner")) {
			params
					.put("AdTypeOptions",
							"<span value='image' selected>图片</span><span value='flash'>动画</span><span value='text'>文本</span>");
		}
		params.put("UploadFilePath", Config.getContextPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/");
		return params;
	}

	public void getImgSrc() {
		String ImgID = $V("ImgID");
		ZCImageSchema image = new ZCImageSchema();
		image.setID(ImgID);
		if (image.fill())
			this.Response.put("ImgSrc", image.getPath()
					+ image.getSrcFileName().replaceAll("//", "/"));
		else
			return;
	}

	public void start() {
		String ID = $V("ID");
		if (!(StringUtil.isEmpty(ID))) {
			ZCAdvertisementSchema ad = new ZCAdvertisementSchema();
			ad.setID(ID);
			ad.fill();
			ZCAdPositionSchema adp = new ZCAdPositionSchema();
			adp.setID(ad.getPositionID());
			adp.fill();
			new QueryBuilder("update zcadvertisement set IsOpen = 'N' where positionid=?", ad
					.getPositionID()).executeNoQuery();
			ad.setIsOpen("Y");
			ad.update();
			if (CreateJSCode(ad, adp))
				this.Response.setLogInfo(1, "启用广告成功！");
			else
				this.Response.setLogInfo(0, "启用广告失败！");
		}
	}

	public void add() {
		String id = $V("ID");

		ZCAdvertisementSchema ad = new ZCAdvertisementSchema();
		ZCAdPositionSchema adp = new ZCAdPositionSchema();
		adp.setID($V("PositionID"));
		adp.fill();
		if (StringUtil.isNotEmpty(id)) {
			ad.setID(id);
			ad.fill();
			ad.setModifyUser(User.getUserName());
			ad.setModifyTime(new Date());
		} else {
			ad.setID(NoUtil.getMaxID("AdvertiseID"));
			ad.setAddUser(User.getUserName());
			ad.setAddTime(new Date());
			if (new QueryBuilder("select Count(*) from ZCAdvertisement where PositionID = ?", adp
					.getID()).executeInt() == 0)
				ad.setIsOpen("Y");
			else {
				ad.setIsOpen("N");
			}
		}
		String StartTime = "";
		String EndTime = "";
		if (StringUtil.isNotEmpty($V("StartDate"))) {
			StartTime = StartTime + $V("StartDate") + " ";
			if (StringUtil.isNotEmpty($V("STime")))
				StartTime = StartTime + $V("STime");
			else
				StartTime = StartTime + "00:00:00";
		} else {
			StartTime = DateUtil.getCurrentDateTime();
		}
		if (StringUtil.isNotEmpty($V("EndDate"))) {
			EndTime = EndTime + $V("EndDate") + " ";
			if (StringUtil.isNotEmpty($V("ETime")))
				EndTime = EndTime + $V("ETime");
			else
				EndTime = EndTime + "23:59:59";
		} else {
			EndTime = "2999-12-31 23:59:59";
		}
		ad.setStartTime(DateUtil.parseDateTime(StartTime, "yyyy-MM-dd hh:mm:ss"));
		ad.setEndTime(DateUtil.parseDateTime(EndTime, "yyyy-MM-dd hh:mm:ss"));
		ad.setPositionID(adp.getID());
		ad.setPositionCode(adp.getCode());
		ad.setSiteID(Application.getCurrentSiteID());
		ad.setAdName($V("AdName"));
		ad.setAdType($V("AdType"));
		ad.setIsHitCount("N");
		ad.setHitCount(0L);

		if ("image".equals(ad.getAdType())) {
			ad = imageAD(ad, adp);
		}

		if ("flash".equals(ad.getAdType())) {
			ad = flashAD(ad, adp);
		}

		if ("text".equals(ad.getAdType())) {
			ad = textAD(ad);
		}

		if ("code".equals(ad.getAdType())) {
			ad = codeAD(ad);
		}

		boolean flag = true;
		try {
			DateFormat df;
			Date now;
			if (StringUtil.isNotEmpty(StartTime)) {
				df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				Date start = df.parse(StartTime);
				now = df.parse(DateUtil.getCurrentDateTime());
				if (start.getTime() > now.getTime()) {
					flag = false;
				}
			}
			if (StringUtil.isNotEmpty(EndTime)) {
				df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date end = df.parse(EndTime);
				now = df.parse(DateUtil.getCurrentDateTime());
				if (end.getTime() < now.getTime())
					flag = false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (StringUtil.isNotEmpty(id)) {
			if (ad.update())
				this.Response.setLogInfo(1, "修改广告成功!");
			else {
				this.Response.setLogInfo(0, "修改广告发生错误!");
			}
		} else if (ad.insert())
			this.Response.setLogInfo(1, "新增广告成功!");
		else {
			this.Response.setLogInfo(0, "新增广告发生错误!");
		}

		if ((flag) && (ad.getIsOpen().equals("Y")))
			CreateJSCode(ad, adp);
	}

	public ZCAdvertisementSchema imageAD(ZCAdvertisementSchema ad, ZCAdPositionSchema adp) {
		String[] Urls = StringUtil.splitEx($V("imgADLinkUrl"), "^");
		String[] Alts = StringUtil.splitEx($V("imgADAlt"), "^");
		String[] Paths = StringUtil.splitEx($V("ImgPath"), "^");
		String showAlt = $V("showAlt");
		if ((StringUtil.isEmpty(showAlt)) || (!(showAlt.equals("Y")))) {
			showAlt = "N";
		}
		String ContentStr = "{'Images':[";
		for (int i = 0; i < Paths.length; ++i) {
			ContentStr = ContentStr + "{'imgADLinkUrl':'" + Urls[i] + "','imgADAlt':'" + Alts[i]
					+ "','ImgPath':'" + Paths[i] + "'}";
			if (i != Paths.length - 1) {
				ContentStr = ContentStr + ",";
			}
		}
		ContentStr = ContentStr + "],'imgADLinkTarget':'" + $V("imgADLinkTarget") + "','Count':'"
				+ Paths.length + "','showAlt':'" + showAlt + "'}";
		ad.setAdContent(ContentStr);
		return ad;
	}

	public ZCAdvertisementSchema flashAD(ZCAdvertisementSchema ad, ZCAdPositionSchema adp) {
		String[] Paths = StringUtil.splitEx($V("SwfFilePath"), "^");
		String ContentStr = "{'Flashes':[";
		for (int i = 0; i < Paths.length; ++i) {
			ContentStr = ContentStr + "{'SwfFilePath':'" + Paths[i] + "'}";
			if (i != Paths.length - 1) {
				ContentStr = ContentStr + ",";
			}
		}
		ContentStr = ContentStr + "],'Count':'" + Paths.length + "'}";
		ad.setAdContent(ContentStr);
		return ad;
	}

	public ZCAdvertisementSchema textAD(ZCAdvertisementSchema ad) {
		String[] textContent = StringUtil.splitEx($V("textContent"), "^");
		String[] textLinkUrl = StringUtil.splitEx($V("textLinkUrl"), "^");
		String[] TextColor = StringUtil.splitEx($V("TextColor"), "^");
		String MaxDisPlay = $V("MaxDisPlay");
		if (StringUtil.isEmpty(MaxDisPlay)) {
			MaxDisPlay = "10";
		}
		String ContentStr = "{'Text':[";
		for (int i = 0; i < textContent.length; ++i) {
			ContentStr = ContentStr + "{'textContent':'" + textContent[i] + "','textLinkUrl':'"
					+ textLinkUrl[i] + "','TextColor':'" + TextColor[i] + "'}";
			if (i != textContent.length - 1) {
				ContentStr = ContentStr + ",";
			}
		}
		ContentStr = ContentStr + "],'Count':'" + textContent.length + "','MaxDisPlay':'"
				+ MaxDisPlay + "'}";
		ad.setAdContent(ContentStr);
		return ad;
	}

	public ZCAdvertisementSchema codeAD(ZCAdvertisementSchema ad) {
		String ContentStr = $V("codeContent");
		ad.setAdContent(ContentStr);
		return ad;
	}

	public void del() {
		String ids = $V("IDs");
		if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		Transaction trans = new Transaction();
		ZCAdvertisementSchema ad = new ZCAdvertisementSchema();
		ZCAdvertisementSet set = ad.query(new QueryBuilder("where id in (" + ids + ")"));
		trans.add(set, 5);
		if (trans.commit()) {
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("操作数据库时发生错误!");
		}
	}

	public void copy() {
		String id = $V("ID");
		ZCAdvertisementSchema ad = new ZCAdvertisementSchema();
		ad.setID(Long.parseLong(id));
		ad.fill();
		String AdName = ad.getAdName();
		AdName = "复制  " + AdName;
		ad.setID(NoUtil.getMaxID("AdvertiseID"));
		ad.setAddUser(User.getUserName());
		ad.setAddTime(new Date());
		ad.setAdName(AdName);
		ad.setIsOpen("Y");
		if (ad.insert()) {
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("发生错误!");
		}
	}

	public static boolean CreateJSCode(String PositionID) {
		ZCAdPositionSchema adp = new ZCAdPositionSchema();
		ZCAdvertisementSchema ad = new ZCAdvertisementSchema();
		adp.setID(PositionID);
		adp.fill();
		String AdID = String.valueOf(new QueryBuilder(new StringBuffer(
				"select ID from ZCAdvertisement where PositionID = ").append(PositionID).append(
				" and StartTime < '").append(DateUtil.getCurrentDateTime()).append(
				"' and EndTime > '").append(DateUtil.getCurrentDateTime()).append(
				"' order by AddTime desc").toString()).executeOneValue());
		if ((StringUtil.isEmpty(AdID)) || (AdID.equalsIgnoreCase("null"))) {
			File f = new File(Config.getContextRealPath() + Config.getValue("UploadDir") + "/"
					+ SiteUtil.getAlias(adp.getSiteID()) + "/" + adp.getJsName());
			if (f.exists()) {
				f.delete();
			}
			FileUtil.writeText(f.getPath(), "document.write('广告内容已过期，请管理员更换');");
		} else {
			ad.setID(AdID);
			ad.fill();
			CreateJSCode(ad, null);
		}
		return true;
	}

	public static boolean CreateJSCode(ZCAdvertisementSchema adv, ZCAdPositionSchema adp) {
		ArrayList deployList = new ArrayList();
		File file = null;
		StringBuffer sb = new StringBuffer();
		ZCAdPositionSchema adposition = new ZCAdPositionSchema();
		String OutString = "";
		String TempPath = Config.getContextRealPath() + "DataService/ADTemplate/";
		if (adp == null) {
			adposition.setID(adv.getPositionID());
			adposition.fill();
		} else {
			adposition = (ZCAdPositionSchema) adp.clone();
		}

		if (adposition.getPositionType().equals("banner")) {
			file = new File(TempPath + "ZCMSAD_Banner.js");
		} else if (adposition.getPositionType().equals("fixure")) {
			file = new File(TempPath + "ZCMSAD_Fixure.js");
		} else if (adposition.getPositionType().equals("float")) {
			file = new File(TempPath + "ZCMSAD_Float.js");
			sb.append(" \n");
			sb.append("function cmsAD_" + adposition.getID() + "_pause_resume(){if(cmsAD_"
					+ adposition.getID() + ".Pause){clearInterval(cmsAD_" + adposition.getID()
					+ ".Interval);cmsAD_" + adposition.getID() + ".Pause = false;}");
			sb.append("else {cmsAD_" + adposition.getID() + ".Interval = setInterval(cmsAD_"
					+ adposition.getID() + ".Start(cmsAD_" + adposition.getID() + "),cmsAD_"
					+ adposition.getID() + ".Delay);cmsAD_" + adposition.getID()
					+ ".Pause = true;}}");
		} else if (adposition.getPositionType().equals("couplet")) {
			file = new File(TempPath + "ZCMSAD_Couplet.js");
		} else if (adposition.getPositionType().equals("imagechange")) {
			file = new File(TempPath + "ZCMSAD_ImageChange.js");
		} else if (adposition.getPositionType().equals("code")) {
			file = new File(TempPath + "ZCMSAD_Code.js");
		}
		OutString = FileUtil.readText(file);

		sb.append(" \n");
		if (adposition.getPositionType().equals("code")) {
			sb.append("document.write('" + adv.getAdContent() + "');");
		} else {
			sb.append("var cmsAD_" + adposition.getID() + " = new ZCMSAD('cmsAD_"
					+ adposition.getID() + "'); \n");
			sb.append("cmsAD_" + adposition.getID() + ".ADID = " + adposition.getID() + "; \n");
			sb.append("cmsAD_" + adposition.getID() + ".ADType = \"" + adv.getAdType() + "\"; \n");
			sb.append("cmsAD_" + adposition.getID() + ".ADName = \"" + adv.getAdName() + "\"; \n");
			sb.append("cmsAD_" + adposition.getID() + ".ADContent = \"" + adv.getAdContent()
					+ "\"; \n");
			if ((adposition.getPositionType().equals("fixure"))
					|| (adposition.getPositionType().equals("float"))
					|| (adposition.getPositionType().equals("couplet"))) {
				sb.append("cmsAD_" + adposition.getID() + ".PaddingLeft = "
						+ adposition.getPaddingLeft() + "; \n");
				sb.append("cmsAD_" + adposition.getID() + ".PaddingTop = "
						+ adposition.getPaddingTop() + "; \n");
			}
			sb.append("cmsAD_" + adposition.getID() + ".Width = " + adposition.getPositionWidth()
					+ "; \n");
			sb.append("cmsAD_" + adposition.getID() + ".Height = " + adposition.getPositionHeight()
					+ "; \n");
			sb.append("cmsAD_" + adposition.getID() + ".UploadFilePath = \""
					+ Config.getContextPath() + Config.getValue("UploadDir") + "/"
					+ SiteUtil.getAlias(adposition.getSiteID()) + "/" + "\"; \n");
			sb.append("cmsAD_" + adposition.getID() + ".ShowAD();\n");
			if (adposition.getPositionType().equals("float")) {
				sb.append("document.getElementById('ZCMSAD_" + adposition.getID()
						+ "').visibility = 'visible';\n");
				sb.append("cmsAD_" + adposition.getID() + ".Interval = setInterval(cmsAD_"
						+ adposition.getID() + ".Start(cmsAD_" + adposition.getID() + "),cmsAD_"
						+ adposition.getID() + ".Delay);");
			} else if (adposition.getPositionType().equals("couplet")) {
				sb.append("cmsAD_" + adposition.getID() + ".Start();\n");
			} else if (adposition.getPositionType().equals("imagechange")) {
				sb.append("ldh.on(window,'load',function (){\n");
				sb.append("shower.init({box:'ZCMSAD_" + adposition.getID() + "_Box',tip:'ZCMSAD_"
						+ adposition.getID() + "_Tip'});})\n");
				sb.append("displayAlt(cmsAD_" + adposition.getID() + ".ADContent,'ZCMSAD_"
						+ adposition.getID() + "_Tip');");
			}
		}
		OutString = OutString + sb.toString();
		String positionPath = Config.getContextRealPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(adposition.getSiteID()) + "/"
				+ adposition.getJsName().substring(0, adposition.getJsName().lastIndexOf("/"))
				+ "/";
		File f = new File(positionPath);
		if (!(f.exists())) {
			FileUtil.mkdir(positionPath);
		}
		FileUtil.writeText(Config.getContextRealPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(adposition.getSiteID()) + "/" + adposition.getJsName(),
				OutString);

		deployList.add(Config.getContextRealPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(adposition.getSiteID()) + "/" + adposition.getJsName());
		Deploy d = new Deploy();
		d.addJobs(adposition.getSiteID(), deployList);

		return true;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.dataservice.Advertise JD-Core Version: 0.5.3
 */