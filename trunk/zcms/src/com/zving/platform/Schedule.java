 package com.zving.platform;
 
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataColumn;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.schedule.AbstractTaskManager;
 import com.zving.framework.schedule.CronManager;
 import com.zving.framework.schedule.CronMonitor;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZDScheduleSchema;
 import java.io.PrintStream;
 import java.util.Calendar;
 import java.util.Date;
 
 public class Schedule extends Page
 {
   public static Mapx init(Mapx map)
   {
     String types = HtmlUtil.mapxToOptions(CronManager.getInstance().getTaskTypes(), null, true);
     map.put("TypeCode", types);
     return map;
   }
 
   public void getUsableTask() {
     String type = $V("TypeCode");
     Mapx map = CronManager.getInstance().getConfigEnableTasks(type);
     Object[] ks = map.keyArray();
     Object[] vs = map.valueArray();
     for (int i = 0; i < map.size(); ++i)
       $S(ks[i].toString(), vs[i]);
   }
 
   public static void dg1DataBind(DataGridAction dga)
   {
     String sql = "select * from ZDSchedule " + dga.getSortString();
     DataTable dt = new QueryBuilder(sql).executeDataTable();
     Mapx map = new Mapx();
     map.put("Y", "启用");
     map.put("N", "停用");
     dt.decodeColumn("IsUsing", map);
 
     map = CronManager.getInstance().getTaskTypes();
     dt.decodeColumn("TypeCode", map);
 
     dt.insertColumn("SourceIDName");
     dt.insertColumn(new DataColumn("NextRunTime", 0));
     for (int i = dt.getRowCount() - 1; i >= 0; --i) {
       String typeCode = dt.getString(i, "TypeCode");
       String sourceID = dt.getString(i, "SourceID");
 
       Mapx taskMap = CronManager.getInstance().getConfigEnableTasks(typeCode);
       if (taskMap == null) {
         dt.deleteRow(i);
       }
       else {
         String sourceIDName = taskMap.getString(sourceID);
         if (StringUtil.isEmpty(sourceIDName)) {
           dt.deleteRow(i);
         }
         else {
           dt.set(i, "SourceIDName", sourceIDName);
           Date nextRunTime = null;
           try {
             nextRunTime = CronMonitor.getNextRunTime(dt.getString(i, "CronExpression"));
           } catch (Exception e) {
             nextRunTime = DateUtil.parse("2999-01-01");
           }
           dt.set(i, "NextRunTime", nextRunTime); }
       }
     }
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(*) from ZDSchedule"));
     }
     dga.bindData(dt);
   }
 
   public void save() {
     ZDScheduleSchema schedule = new ZDScheduleSchema();
     String id = $V("ID");
     if (StringUtil.isEmpty(id)) {
       schedule.setID(NoUtil.getMaxID("ScheduleID"));
       schedule.setAddTime(new Date());
       schedule.setAddUser(User.getUserName());
     } else {
       schedule.setID(Long.parseLong(id));
       schedule.fill();
       schedule.setModifyTime(new Date());
       schedule.setModifyUser(User.getUserName());
     }
     String startTime = $V("StartDate") + " " + $V("StartTime");
     this.Request.put("StartTime", startTime);
     if ($V("PlanType").equals("Period")) {
       String period = $V("Period");
       Calendar c = Calendar.getInstance();
       c.setTime(DateUtil.parseDateTime(startTime));
       StringBuffer sb = new StringBuffer();
       int minute;
       if ($V("PeriodType").equals("Minute")) {
         minute = c.get(12);
         sb.append(minute);
         sb.append("-");
         if (minute == 0)
           sb.append("59");
         else {
           sb.append(minute - 1);
         }
         sb.append("/");
         sb.append(period);
         sb.append(" * * * *");
       }
       else
       {
         int hour;
         if ($V("PeriodType").equals("Hour")) {
           minute = c.get(12);
           hour = c.get(11);
           sb.append(minute);
           sb.append(" ");
           sb.append(hour);
           sb.append("-");
           if (hour == 0)
             sb.append("23");
           else {
             sb.append(hour - 1);
           }
           sb.append("/");
           sb.append(period);
           sb.append(" * * *");
         }
         else
         {
           int day;
           if ($V("PeriodType").equals("Day")) {
             minute = c.get(12);
             hour = c.get(11);
             day = c.get(5);
             sb.append(minute);
             sb.append(" ");
             sb.append(hour);
             sb.append(" ");
             sb.append(day);
             sb.append("-");
             sb.append(day - 1);
             sb.append("/");
             sb.append(period);
             sb.append(" * *");
           } else if ($V("PeriodType").equals("Month")) {
             minute = c.get(12);
             hour = c.get(11);
             day = c.get(5);
             int month = c.get(2);
             sb.append(minute);
             sb.append(" ");
             sb.append(hour);
             sb.append(" ");
             sb.append(day);
             sb.append(" ");
             sb.append(month);
             sb.append("-");
             sb.append(month - 1);
             sb.append("/");
             sb.append(period);
             sb.append(" *"); } }
       }
       this.Request.put("CronExpression", sb.toString());
     }
     schedule.setValue(this.Request);
     try {
       CronMonitor.getNextRunTime(schedule.getCronExpression());
       boolean flag = (StringUtil.isEmpty(id)) ? schedule.insert() : schedule.update();
       if (!(flag)) {
         this.Response.setError("发生错误!"); return;
       }
       this.Response.setMessage("操作成功!");
     }
     catch (Exception e) {
       this.Response.setError("发生错误:Cron表达式不正确!");
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
     String sql = "delete from ZDSchedule where id in (" + ids + ")";
     Transaction trans = new Transaction();
     trans.add(new QueryBuilder(sql));
     if (!(trans.commit())) {
       UserLog.log("System", "DelSchedule", "删除定时任务失败", this.Request.getClientIP());
       this.Response.setError("发生错误!");
     } else {
       UserLog.log("System", "DelSchedule", "删除定时任务成功", this.Request.getClientIP());
       this.Response.setMessage("操作成功!");
     }
   }
 
   public void execute() {
     long id = Long.parseLong($V("ID"));
     ZDScheduleSchema s = new ZDScheduleSchema();
     s.setID(id);
     if (!(s.fill())) {
       this.Response.setError("任务不存在或己被删除!");
       return;
     }
     AbstractTaskManager ctm = CronManager.getInstance().getCronTaskManager(s.getTypeCode());
     if (ctm.isRunning(s.getSourceID())) {
       this.Response.setMessage("<font color=red>任务正在运行，请等待该任务运行完毕!</font>");
     } else {
       ctm.execute(s.getSourceID());
       this.Response.setMessage("任务开始运行!");
     }
   }
 
   public static void main(String[] args) {
     try {
       Date d = CronMonitor.getNextRunTime("50 16 25-24/1 * *");
       System.out.println(DateUtil.toDateTimeString(d));
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.Schedule
 * JD-Core Version:    0.5.3
 */