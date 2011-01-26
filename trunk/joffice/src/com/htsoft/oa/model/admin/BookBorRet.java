 package com.htsoft.oa.model.admin;
 
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class BookBorRet extends BaseModel
 {
   protected Long recordId;
   protected Date borrowTime;
   protected Date returnTime;
   protected Date lastReturnTime;
   protected String borrowIsbn;
   protected String bookName;
   protected String registerName;
   protected String fullname;
   protected BookSn bookSn;
 
   public BookBorRet()
   {
   }
 
   public BookBorRet(Long in_recordId)
   {
     setRecordId(in_recordId);
   }
 
   public BookSn getBookSn()
   {
     return this.bookSn;
   }
 
   public void setBookSn(BookSn in_bookSn) {
     this.bookSn = in_bookSn;
   }
 
   public String getRegisterName() {
     return this.registerName;
   }
 
   public void setRegisterName(String registerName) {
     this.registerName = registerName;
   }
 
   public String getFullname() {
     return this.fullname;
   }
 
   public void setFullname(String fullname) {
     this.fullname = fullname;
   }
 
   public Long getRecordId()
   {
     return this.recordId;
   }
 
   public void setRecordId(Long aValue)
   {
     this.recordId = aValue;
   }
 
   public Long getBookSnId()
   {
     return (getBookSn() == null) ? null : getBookSn().getBookSnId();
   }
 
   public void setBookSnId(Long aValue)
   {
     if (aValue == null) {
       this.bookSn = null;
     } else if (this.bookSn == null) {
       this.bookSn = new BookSn(aValue);
       this.bookSn.setVersion(new Integer(0));
     } else {
       this.bookSn.setBookSnId(aValue);
     }
   }
 
   public Date getBorrowTime()
   {
     return this.borrowTime;
   }
 
   public void setBorrowTime(Date aValue)
   {
     this.borrowTime = aValue;
   }
 
   public Date getReturnTime()
   {
     return this.returnTime;
   }
 
   public void setReturnTime(Date aValue)
   {
     this.returnTime = aValue;
   }
 
   public Date getLastReturnTime()
   {
     return this.lastReturnTime;
   }
 
   public void setLastReturnTime(Date aValue)
   {
     this.lastReturnTime = aValue;
   }
 
   public String getBorrowIsbn()
   {
     return this.borrowIsbn;
   }
 
   public void setBorrowIsbn(String aValue)
   {
     this.borrowIsbn = aValue;
   }
 
   public String getBookName()
   {
     return this.bookName;
   }
 
   public void setBookName(String aValue)
   {
     this.bookName = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof BookBorRet) {
       return false;
     }
     BookBorRet rhs = (BookBorRet)object;
     return new EqualsBuilder()
       .append(this.recordId, rhs.recordId)
       .append(this.borrowTime, rhs.borrowTime)
       .append(this.returnTime, rhs.returnTime)
       .append(this.lastReturnTime, rhs.lastReturnTime)
       .append(this.borrowIsbn, rhs.borrowIsbn)
       .append(this.bookName, rhs.bookName)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.recordId)
       .append(this.borrowTime)
       .append(this.returnTime)
       .append(this.lastReturnTime)
       .append(this.borrowIsbn)
       .append(this.bookName)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("recordId", this.recordId)
       .append("borrowTime", this.borrowTime)
       .append("returnTime", this.returnTime)
       .append("lastReturnTime", this.lastReturnTime)
       .append("borrowIsbn", this.borrowIsbn)
       .append("bookName", this.bookName)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.BookBorRet
 * JD-Core Version:    0.5.4
 */