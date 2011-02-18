package com.htsoft.oa.action.admin;

import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.Book;
import com.htsoft.oa.model.admin.BookSn;
import com.htsoft.oa.model.admin.BookType;
import com.htsoft.oa.service.admin.BookService;
import com.htsoft.oa.service.admin.BookSnService;
import com.htsoft.oa.service.admin.BookTypeService;

import flexjson.JSONSerializer;

public class BookAction extends BaseAction {

	@Resource
	private BookService bookService;

	@Resource
	private BookTypeService bookTypeService;

	@Resource
	private BookSnService bookSnService;
	private Book book;
	private Long bookId;
	private Long typeId;
	private BookType bookType;

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public BookType getBookType() {
		return this.bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public Long getBookId() {
		return this.bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.bookService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = new JSONSerializer();
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.bookService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		Book book = (Book) this.bookService.get(this.bookId);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = new JSONSerializer();
		sb.append(serializer.exclude(new String[] { "class" }).serialize(book));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String save() {
		String bookSnNumber = "";

		if (this.book.getBookId() == null) {
			this.book.setLeftAmount(this.book.getAmount());
			this.bookService.save(this.book);
			for (int i = 1; i <= this.book.getAmount().intValue(); ++i) {
				BookSn bookSn = new BookSn();

				bookSnNumber = this.book.getIsbn() + "-" + i;
				bookSn.setBookId(this.book.getBookId());
				bookSn.setBookSN(bookSnNumber);

				bookSn.setStatus((short) 0);

				this.bookSnService.save(bookSn);
			}
		} else {
			this.bookService.save(this.book);
		}
		setJsonString("{success:true,bookSnNumber:'" + bookSnNumber + "'}");
		return "success";
	}

	public String updateAmount() {
		Long bookId = Long.valueOf(getRequest().getParameter("bookId"));
		this.book = ((Book) this.bookService.get(bookId));
		int addAmount = Integer.parseInt(getRequest().getParameter("addAmount"));
		int amount = this.book.getAmount().intValue() + addAmount;
		BookSn bookSn = null;
		String bookSnNumber = "";
		for (int i = this.book.getAmount().intValue() + 1; i <= this.book.getAmount().intValue()
				+ addAmount; ++i) {
			bookSn = new BookSn();

			bookSnNumber = this.book.getIsbn() + "-" + i;
			bookSn.setBookId(this.book.getBookId());
			bookSn.setBookSN(bookSnNumber);

			bookSn.setStatus((short) 0);

			this.bookSnService.save(bookSn);
		}
		this.book.setAmount(Integer.valueOf(amount));
		this.book.setLeftAmount(Integer.valueOf(this.book.getLeftAmount().intValue() + addAmount));
		this.bookService.save(this.book);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = new JSONSerializer();
		sb.append(serializer.exclude(new String[] { "class" }).serialize(this.book));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String updateAmountAndLeftAmount() {
		Long bookId = Long.valueOf(getRequest().getParameter("bookId"));
		this.book = ((Book) this.bookService.get(bookId));
		int amount = this.book.getAmount().intValue() - 1;
		int leftAmount = this.book.getLeftAmount().intValue() - 1;
		this.book.setAmount(Integer.valueOf(amount));
		this.book.setLeftAmount(Integer.valueOf(leftAmount));
		this.bookService.save(this.book);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = new JSONSerializer();
		sb.append(serializer.exclude(new String[] { "class" }).serialize(this.book));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}
}
