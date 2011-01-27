 package com.htsoft.oa.model.admin;
 
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Book extends BaseModel
 {
   protected Long bookId;
   protected String bookName;
   protected String author;
   protected String isbn;
   protected String publisher;
   protected BigDecimal price;
   protected String location;
   protected String department;
   protected Integer amount;
   protected Integer leftAmount;
   protected BookType bookType;
   protected Set bookSns = new HashSet();
 
   public Book()
   {
   }
 
   public Book(Long in_bookId)
   {
     setBookId(in_bookId);
   }
 
   public BookType getBookType()
   {
     return this.bookType;
   }
 
   public void setBookType(BookType in_bookType) {
     this.bookType = in_bookType;
   }
 
   public Set getBookSns() {
     return this.bookSns;
   }
 
   public void setBookSns(Set in_bookSns) {
     this.bookSns = in_bookSns;
   }
 
   public Long getBookId()
   {
     return this.bookId;
   }
 
   public void setBookId(Long aValue)
   {
     this.bookId = aValue;
   }
 
   public Long getTypeId()
   {
     return (getBookType() == null) ? null : getBookType().getTypeId();
   }
 
   public void setTypeId(Long aValue)
   {
     if (aValue == null) {
       this.bookType = null;
     } else if (this.bookType == null) {
       this.bookType = new BookType(aValue);
       this.bookType.setVersion(new Integer(0));
     } else {
       this.bookType.setTypeId(aValue);
     }
   }
 
   public String getBookName()
   {
     return this.bookName;
   }
 
   public void setBookName(String aValue)
   {
     this.bookName = aValue;
   }
 
   public String getAuthor()
   {
     return this.author;
   }
 
   public void setAuthor(String aValue)
   {
     this.author = aValue;
   }
 
   public String getIsbn()
   {
     return this.isbn;
   }
 
   public void setIsbn(String aValue)
   {
     this.isbn = aValue;
   }
 
   public String getPublisher()
   {
     return this.publisher;
   }
 
   public void setPublisher(String aValue)
   {
     this.publisher = aValue;
   }
 
   public BigDecimal getPrice()
   {
     return this.price;
   }
 
   public void setPrice(BigDecimal aValue)
   {
     this.price = aValue;
   }
 
   public String getLocation()
   {
     return this.location;
   }
 
   public void setLocation(String aValue)
   {
     this.location = aValue;
   }
 
   public String getDepartment()
   {
     return this.department;
   }
 
   public void setDepartment(String aValue)
   {
     this.department = aValue;
   }
 
   public Integer getAmount()
   {
     return this.amount;
   }
 
   public void setAmount(Integer aValue)
   {
     this.amount = aValue;
   }
   public Integer getLeftAmount() {
     return this.leftAmount;
   }
 
   public void setLeftAmount(Integer leftAmount) {
     this.leftAmount = leftAmount;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof Book)) {
       return false;
     }
     Book rhs = (Book)object;
     return new EqualsBuilder()
       .append(this.bookId, rhs.bookId)
       .append(this.bookName, rhs.bookName)
       .append(this.author, rhs.author)
       .append(this.isbn, rhs.isbn)
       .append(this.publisher, rhs.publisher)
       .append(this.price, rhs.price)
       .append(this.location, rhs.location)
       .append(this.department, rhs.department)
       .append(this.amount, rhs.amount)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.bookId)
       .append(this.bookName)
       .append(this.author)
       .append(this.isbn)
       .append(this.publisher)
       .append(this.price)
       .append(this.location)
       .append(this.department)
       .append(this.amount)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("bookId", this.bookId)
       .append("bookName", this.bookName)
       .append("author", this.author)
       .append("isbn", this.isbn)
       .append("publisher", this.publisher)
       .append("price", this.price)
       .append("location", this.location)
       .append("department", this.department)
       .append("amount", this.amount)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.Book
 * JD-Core Version:    0.5.4
 */