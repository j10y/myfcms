 package com.zving.cms.site;
 
 import com.zving.framework.Page;
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
 import com.zving.schema.ZCImagePlayerSchema;
 import com.zving.schema.ZCImagePlayerSet;
 import java.util.Date;
 
 public class ImagePlayerBasic extends Page
 {
   public static Mapx init(Mapx params)
   {
     Object o = params.get("ImagePlayerID");
     if ((o != null) && (!(o.equals("")))) {
       long ID = Long.parseLong(o.toString());
       ZCImagePlayerSchema ImagePlayer = new ZCImagePlayerSchema();
       ImagePlayer.setID(ID);
       ImagePlayer.fill();
       Mapx map = ImagePlayer.toMapx();
       map.put("ImagePlayerID", ImagePlayer.getID());
       map.put("radiosProp1", HtmlUtil.codeToRadios("Prop1", "YesOrNo", ImagePlayer.getProp1()));
       return map;
     }
     params.put("radiosProp1", HtmlUtil.codeToRadios("Prop1", "YesOrNo", "Y"));
 
     return params;
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     String sql = "select ID,Name,Code,SiteID,DisplayType,ImageSource,Height,Width,Displaycount from ZCImagePlayer order by ID ";
     if (dga.getTotal() == 0) {
       String sql2 = "select count(*) from ZCImagePlayer";
       dga.setTotal(new QueryBuilder(sql2));
     }
     dga.bindData(new QueryBuilder(sql));
   }
 
   public void add() {
     Object o = $V("ImagePlayerID");
     long ImagePlayerID = 0L;
     if ((o != null) && (!("".equals(o)))) {
       ImagePlayerID = Long.parseLong(o.toString());
     }
     ZCImagePlayerSchema ImagePlayer = new ZCImagePlayerSchema();
     ImagePlayer.setID(ImagePlayerID);
     if (ImagePlayer.getID() != 0L)
     {
       ImagePlayer.fill();
       ImagePlayer.setValue(this.Request);
       ImagePlayer.setModifyTime(new Date());
       ImagePlayer.setModifyUser(User.getUserName());
       ImagePlayer.setImageSource("0");
       ImagePlayer.setDisplayType("1");
       if (ImagePlayer.update()) {
         this.Response.setStatus(1);
         this.Response.put("ImagePlayerID", ImagePlayer.getID());
         this.Response.setMessage("保存成功,您可以去‘预览’查看修改后的效果!");
       } else {
         this.Response.setStatus(0);
         this.Response.setMessage("发生错误!");
       }
     }
     else {
       DataTable checkDT = new QueryBuilder("select * from zcimageplayer where code=? and siteID=?", 
         $V("Code"), Application.getCurrentSiteID()).executeDataTable();
       if (checkDT.getRowCount() > 0) {
         this.Response.setLogInfo(0, "已经存在代码为‘ <b style='color:#F00'>" + $V("Code") + "</b>’ 的图片播放器，请更换播放器代码！");
         return;
       }
       ImagePlayer.setID(NoUtil.getMaxID("ImagePlayerID"));
       ImagePlayer.setValue(this.Request);
       ImagePlayer.setImageSource("0");
       ImagePlayer.setDisplayType("1");
       ImagePlayer.setSiteID(Application.getCurrentSiteID());
       ImagePlayer.setAddTime(new Date());
       ImagePlayer.setAddUser(User.getUserName());
 
       if (ImagePlayer.insert()) {
         this.Response.put("ImagePlayerID", ImagePlayer.getID());
         this.Response.setStatus(1);
         this.Response.setMessage("新建成功,您现在可以关联图片了!");
       } else {
         this.Response.setStatus(0);
         this.Response.setMessage("发生错误!");
       }
     }
   }
 
   public void del()
   {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     Transaction trans = new Transaction();
     ZCImagePlayerSchema ImagePlayer = new ZCImagePlayerSchema();
     ZCImagePlayerSet set = ImagePlayer.query(new QueryBuilder("where id in (" + ids + ")"));
     trans.add(set, 5);
 
     if (trans.commit()) {
       this.Response.setMessage("删除成功,您可以去‘预览’查看删除后的效果!");
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.ImagePlayerBasic
 * JD-Core Version:    0.5.3
 */