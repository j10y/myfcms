 package com.htsoft.oa.model.archive;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.Department;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Archives extends BaseModel
 {
   public static final Short STATUS_DRAFT = 0;
 
   public static final Short STATUS_ISSUE = 1;
 
   public static final Short STATUS_ARCHIVE = 2;
 
   public static final Short STATUS_HANDLE = 1;
 
   public static final Short STATUS_HANDLEING = 2;
 
   public static final Short STATUS_LEADERREAD = 3;
 
   public static final Short STATUS_DISPATCH = 4;
 
   public static final Short STATUS_READ = 5;
 
   public static final Short STATUS_READING = 6;
 
   public static final Short STATUS_END = 7;
 
   public static final Short ARCHIVE_TYPE_DISPATCH = 0;
 
   public static final Short ARCHIVE_TYPE_RECEIVE = 1;
 
   @Expose
   protected Long archivesId;
 
   @Expose
   protected String typeName;
 
   @Expose
   protected String archivesNo;
 
   @Expose
   protected String issueDep;
 
   @Expose
   protected String subject;
 
   @Expose
   protected Date issueDate;
 
   @Expose
   protected Date createtime;
 
   @Expose
   protected Short status;
 
   @Expose
   protected String shortContent;
 
   @Expose
   protected Integer fileCounts;
 
   @Expose
   protected String privacyLevel;
 
   @Expose
   protected String urgentLevel;
 
   @Expose
   protected String issuer;
 
   @Expose
   protected Long issuerId;
 
   @Expose
   protected String keywords;
 
   @Expose
   protected String sources;
 
   @Expose
   protected Short archType;
 
   @Expose
   protected String recDepIds;
 
   @Expose
   protected String recDepNames;
 
   @Expose
   protected String handlerUids;
 
   @Expose
   protected String handlerUnames;
 
   @Expose
   protected Long orgArchivesId;
 
   @Expose
   protected String depSignNo;
 
   @Expose
   protected ArchivesType archivesType;
 
   @Expose
   protected Department department;
 
   @Expose
   protected ArchRecType archRecType;
 
   @Expose
   protected Set archivesHandles = new HashSet();
 
   @Expose
   protected Set archivesDeps = new HashSet();
 
   @Expose
   protected Set archivesDocs = new HashSet();
   protected Set leaders = new HashSet();
   protected Set archivesDispatch = new HashSet();
   protected Set archivesAttends = new HashSet();
 
   public Set getArchivesAttends()
   {
     return this.archivesAttends;
   }
 
   public void setArchivesAttends(Set archivesAttends) {
     this.archivesAttends = archivesAttends;
   }
 
   public Set getArchivesDispatch() {
     return this.archivesDispatch;
   }
 
   public void setArchivesDispatch(Set archivesDispatch) {
     this.archivesDispatch = archivesDispatch;
   }
 
   public Set getLeaders() {
     return this.leaders;
   }
 
   public void setLeaders(Set leaders) {
     this.leaders = leaders;
   }
 
   public String getHandlerUids()
   {
     return this.handlerUids;
   }
 
   public void setHandlerUids(String handlerUids) {
     this.handlerUids = handlerUids;
   }
 
   public String getHandlerUnames() {
     return this.handlerUnames;
   }
 
   public void setHandlerUnames(String handlerUnames) {
     this.handlerUnames = handlerUnames;
   }
 
   public Archives()
   {
   }
 
   public Archives(Long in_archivesId)
   {
     setArchivesId(in_archivesId);
   }
 
   public Set getArchivesHandles() {
     return this.archivesHandles;
   }
 
   public void setArchivesHandles(Set archivesHandles) {
     this.archivesHandles = archivesHandles;
   }
 
   public ArchRecType getArchRecType() {
     return this.archRecType;
   }
 
   public void setArchRecType(ArchRecType archRecType) {
     this.archRecType = archRecType;
   }
 
   public ArchivesType getArchivesType() {
     return this.archivesType;
   }
 
   public void setArchivesType(ArchivesType in_archivesType) {
     this.archivesType = in_archivesType;
   }
 
   public Department getDepartment() {
     return this.department;
   }
 
   public void setDepartment(Department in_department) {
     this.department = in_department;
   }
 
   public Set getArchivesDeps() {
     return this.archivesDeps;
   }
 
   public void setArchivesDeps(Set in_archivesDeps) {
     this.archivesDeps = in_archivesDeps;
   }
 
   public Long getArchivesId()
   {
     return this.archivesId;
   }
 
   public void setArchivesId(Long aValue)
   {
     this.archivesId = aValue;
   }
 
   public Long getTypeId()
   {
     return (getArchivesType() == null) ? null : getArchivesType().getTypeId();
   }
 
   public void setTypeId(Long aValue)
   {
     if (aValue == null) {
       this.archivesType = null;
     } else if (this.archivesType == null) {
       this.archivesType = new ArchivesType(aValue);
       this.archivesType.setVersion(new Integer(0));
     } else {
       this.archivesType.setTypeId(aValue);
     }
   }
 
   public Long getRecTypeId()
   {
     return (getArchRecType() == null) ? null : getArchRecType().getRecTypeId();
   }
 
   public void setRecTypeId(Long aValue)
   {
     if (aValue == null) {
       this.archRecType = null;
     } else if (this.archRecType == null) {
       this.archRecType = new ArchRecType(aValue);
       this.archRecType.setVersion(new Integer(0));
     } else {
       this.archRecType.setRecTypeId(aValue);
     }
   }
 
   public String getTypeName()
   {
     return this.typeName;
   }
 
   public void setTypeName(String aValue)
   {
     this.typeName = aValue;
   }
 
   public String getArchivesNo()
   {
     return this.archivesNo;
   }
 
   public void setArchivesNo(String aValue)
   {
     this.archivesNo = aValue;
   }
 
   public String getIssueDep()
   {
     return this.issueDep;
   }
 
   public void setIssueDep(String aValue)
   {
     this.issueDep = aValue;
   }
 
   public Long getDepId()
   {
     return (getDepartment() == null) ? null : getDepartment().getDepId();
   }
 
   public void setDepId(Long aValue)
   {
     if (aValue == null) {
       this.department = null;
     } else if (this.department == null) {
       this.department = new Department(aValue);
       this.department.setVersion(new Integer(0));
     } else {
       this.department.setDepId(aValue);
     }
   }
 
   public String getSubject()
   {
     return this.subject;
   }
 
   public void setSubject(String aValue)
   {
     this.subject = aValue;
   }
 
   public Date getIssueDate()
   {
     return this.issueDate;
   }
 
   public void setIssueDate(Date aValue)
   {
     this.issueDate = aValue;
   }
 
   public Short getStatus()
   {
     return this.status;
   }
 
   public void setStatus(Short aValue)
   {
     this.status = aValue;
   }
 
   public String getShortContent()
   {
     return this.shortContent;
   }
 
   public void setShortContent(String aValue)
   {
     this.shortContent = aValue;
   }
 
   public Integer getFileCounts()
   {
     return this.fileCounts;
   }
 
   public void setFileCounts(Integer aValue)
   {
     this.fileCounts = aValue;
   }
 
   public String getPrivacyLevel()
   {
     return this.privacyLevel;
   }
 
   public void setPrivacyLevel(String aValue)
   {
     this.privacyLevel = aValue;
   }
 
   public String getUrgentLevel()
   {
     return this.urgentLevel;
   }
 
   public void setUrgentLevel(String aValue)
   {
     this.urgentLevel = aValue;
   }
 
   public String getIssuer()
   {
     return this.issuer;
   }
 
   public void setIssuer(String aValue)
   {
     this.issuer = aValue;
   }
 
   public Long getIssuerId()
   {
     return this.issuerId;
   }
 
   public void setIssuerId(Long aValue)
   {
     this.issuerId = aValue;
   }
 
   public String getKeywords()
   {
     return this.keywords;
   }
 
   public void setKeywords(String aValue)
   {
     this.keywords = aValue;
   }
 
   public String getSources()
   {
     return this.sources;
   }
 
   public void setSources(String aValue)
   {
     this.sources = aValue;
   }
 
   public Short getArchType()
   {
     return this.archType;
   }
 
   public void setArchType(Short aValue)
   {
     this.archType = aValue;
   }
 
   public String getRecDepIds() {
     return this.recDepIds;
   }
 
   public void setRecDepIds(String recDepIds) {
     this.recDepIds = recDepIds;
   }
 
   public String getRecDepNames() {
     return this.recDepNames;
   }
 
   public void setRecDepNames(String recDepNames) {
     this.recDepNames = recDepNames;
   }
 
   public Long getOrgArchivesId()
   {
     return this.orgArchivesId;
   }
 
   public void setOrgArchivesId(Long orgArchivesId) {
     this.orgArchivesId = orgArchivesId;
   }
 
   public String getDepSignNo() {
     return this.depSignNo;
   }
 
   public void setDepSignNo(String depSignNo) {
     this.depSignNo = depSignNo;
   }
 
   public Date getCreatetime() {
     return this.createtime;
   }
 
   public void setCreatetime(Date createtime) {
     this.createtime = createtime;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof Archives)) {
       return false;
     }
     Archives rhs = (Archives)object;
     return new EqualsBuilder()
       .append(this.archivesId, rhs.archivesId)
       .append(this.typeName, rhs.typeName)
       .append(this.archivesNo, rhs.archivesNo)
       .append(this.issueDep, rhs.issueDep)
       .append(this.subject, rhs.subject)
       .append(this.issueDate, rhs.issueDate)
       .append(this.status, rhs.status)
       .append(this.shortContent, rhs.shortContent)
       .append(this.fileCounts, rhs.fileCounts)
       .append(this.privacyLevel, rhs.privacyLevel)
       .append(this.urgentLevel, rhs.urgentLevel)
       .append(this.issuer, rhs.issuer)
       .append(this.issuerId, rhs.issuerId)
       .append(this.keywords, rhs.keywords)
       .append(this.sources, rhs.sources)
       .append(this.archType, rhs.archType)
       .append(this.recDepIds, rhs.recDepIds)
       .append(this.recDepNames, rhs.recDepNames)
       .append(this.orgArchivesId, rhs.orgArchivesId)
       .append(this.depSignNo, rhs.depSignNo)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.archivesId)
       .append(this.typeName)
       .append(this.archivesNo)
       .append(this.issueDep)
       .append(this.subject)
       .append(this.issueDate)
       .append(this.status)
       .append(this.shortContent)
       .append(this.fileCounts)
       .append(this.privacyLevel)
       .append(this.urgentLevel)
       .append(this.issuer)
       .append(this.issuerId)
       .append(this.keywords)
       .append(this.sources)
       .append(this.archType)
       .append(this.recDepIds)
       .append(this.recDepNames)
       .append(this.orgArchivesId)
       .append(this.depSignNo)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("archivesId", this.archivesId)
       .append("typeName", this.typeName)
       .append("archivesNo", this.archivesNo)
       .append("issueDep", this.issueDep)
       .append("subject", this.subject)
       .append("issueDate", this.issueDate)
       .append("status", this.status)
       .append("shortContent", this.shortContent)
       .append("fileCounts", this.fileCounts)
       .append("privacyLevel", this.privacyLevel)
       .append("urgentLevel", this.urgentLevel)
       .append("issuer", this.issuer)
       .append("issuerId", this.issuerId)
       .append("keywords", this.keywords)
       .append("sources", this.sources)
       .append("archType", this.archType)
       .append("recDepIds", this.recDepIds)
       .append("recDepNames", this.recDepNames)
       .append("orgArchivesId", this.orgArchivesId)
       .append("depSignNo", this.depSignNo)
       .toString();
   }
 
   public Set getArchivesDocs() {
     return this.archivesDocs;
   }
 
   public void setArchivesDocs(Set archivesDocs) {
     this.archivesDocs = archivesDocs;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.Archives
 * JD-Core Version:    0.5.4
 */