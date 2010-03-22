 package com.zving.cms.site;
 
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.platform.pub.NoUtil;
 import com.zving.platform.pub.OrderUtil;
 import com.zving.schema.ZCLinkGroupSchema;
 import com.zving.schema.ZCLinkGroupSet;
 import com.zving.schema.ZCLinkSchema;
 import com.zving.schema.ZCLinkSet;
 import java.util.ArrayList;
 import java.util.Date;
 
 public class LinkGroup extends Page
 {
   public static final String TYPE_TEXT = "text";
   public static final String TYPE_IMAGE = "image";
   public static final Mapx TYPE_MAP = new Mapx();
 
   static {
     TYPE_MAP.put("text", "文字链接");
     TYPE_MAP.put("image", "图片链接");
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     String sql1 = "select * from ZCLinkGroup where SiteID=" + Application.getCurrentSiteID() + " order by OrderFlag asc,id desc";
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(*) from ZCLinkGroup where SiteID=" + Application.getCurrentSiteID()));
     }
     DataTable dt = new QueryBuilder(sql1).executeDataTable();
     dt.decodeColumn("Type", TYPE_MAP);
     dga.bindData(dt);
   }
 
   public static Mapx initDialog(Mapx params) {
     params.put("Type", HtmlUtil.mapxToRadios("Type", TYPE_MAP, "text"));
     return params;
   }
 
   public void save() {
     DataTable dt = (DataTable)this.Request.get("DT");
     Transaction trans = new Transaction();
     String logMessage = "保存成功！";
     ArrayList list = new ArrayList();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       DataTable checkdt = new QueryBuilder("select * from zclinkgroup where name=?", dt.getString(i, "Name")).executeDataTable();
       if (checkdt.getRowCount() > 0) {
         list.add(dt.getString(i, "Name"));
       }
       else {
         QueryBuilder qb = new QueryBuilder("update ZCLinkGroup set Name=?,ModifyUser=?,ModifyTime=? where ID=?");
         qb.add(dt.getString(i, "Name"));
         qb.add(User.getUserName());
         qb.add(new Date());
         qb.add(dt.getString(i, "ID"));
         trans.add(qb); }
     }
     if (list.size() > 0) {
       logMessage = logMessage + "链接分类名称";
       logMessage = logMessage + StringUtil.join(list.toArray(), "、");
       logMessage = logMessage + "已存在,请更换...";
     }
     if (trans.commit())
       this.Response.setLogInfo(1, logMessage);
     else
       this.Response.setLogInfo(0, "保存失败！");
   }
 
   public void add()
   {
     String name = $V("Name");
     DataTable dt = new QueryBuilder("select * from zclinkgroup where name=?", name).executeDataTable();
     if (dt.getRowCount() > 0) {
       this.Response.setLogInfo(0, "该链接分类名称已存在，请更换...");
       return;
     }
     ZCLinkGroupSchema linkGroup = new ZCLinkGroupSchema();
     linkGroup.setValue(this.Request);
     linkGroup.setID(NoUtil.getMaxID("LinkGroupID"));
     linkGroup.setOrderFlag(OrderUtil.getDefaultOrder());
     linkGroup.setSiteID(Application.getCurrentSiteID());
     linkGroup.setAddTime(new Date());
     linkGroup.setAddUser(User.getUserName());
     if (linkGroup.insert())
       this.Response.setLogInfo(1, "新增成功");
     else
       this.Response.setLogInfo(0, "新增" + linkGroup.getName() + "失败!");
   }
 
   public void del()
   {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setLogInfo(0, "传入ID时发生错误!");
       return;
     }
     if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
       this.Response.setLogInfo(0, "传入ID时发生错误!");
       return;
     }
     Transaction trans = new Transaction();
     ZCLinkGroupSchema linkGroup = new ZCLinkGroupSchema();
     ZCLinkGroupSet set = linkGroup.query(new QueryBuilder("where id in (" + ids + ")"));
     trans.add(set, 3);
 
     for (int i = 0; i < set.size(); ++i) {
       linkGroup = set.get(i);
       ZCLinkSchema link = new ZCLinkSchema();
       link.setLinkGroupID(linkGroup.getID());
       ZCLinkSet LinkSet = link.query();
       trans.add(LinkSet, 3);
     }
     if (trans.commit())
       this.Response.setLogInfo(1, "删除成功");
     else
       this.Response.setLogInfo(0, "删除失败");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.LinkGroup
 * JD-Core Version:    0.5.3
 */