package com.htsoft.oa.model.admin;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class BookSn extends BaseModel {
	protected Long bookSnId;
	protected String bookSN;
	protected Short status;
	protected Book book;
	protected Set bookBorRets = new HashSet();

	public BookSn() {
	}

	public BookSn(Long in_bookSnId) {
		setBookSnId(in_bookSnId);
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book in_book) {
		this.book = in_book;
	}

	public Set getBookBorRets() {
		return this.bookBorRets;
	}

	public void setBookBorRets(Set in_bookBorRets) {
		this.bookBorRets = in_bookBorRets;
	}

	public Long getBookSnId() {
		return this.bookSnId;
	}

	public void setBookSnId(Long aValue) {
		this.bookSnId = aValue;
	}

	public Long getBookId() {
		return (getBook() == null) ? null : getBook().getBookId();
	}

	public void setBookId(Long aValue) {
		if (aValue == null) {
			this.book = null;
		} else if (this.book == null) {
			this.book = new Book(aValue);
			this.book.setVersion(new Integer(0));
		} else {
			this.book.setBookId(aValue);
		}
	}

	public String getBookSN() {
		return this.bookSN;
	}

	public void setBookSN(String aValue) {
		this.bookSN = aValue;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short aValue) {
		this.status = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof BookSn)) {
			return false;
		}
		BookSn rhs = (BookSn) object;
		return new EqualsBuilder().append(this.bookSnId, rhs.bookSnId).append(this.bookSN,
				rhs.bookSN).append(this.status, rhs.status).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.bookSnId).append(this.bookSN)
				.append(this.status).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("bookSnId", this.bookSnId).append("bookSN",
				this.bookSN).append("status", this.status).toString();
	}
}


 
 
 
 