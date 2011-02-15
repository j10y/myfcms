package com.htsoft.oa.action.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.Book;
import com.htsoft.oa.model.admin.BookBorRet;
import com.htsoft.oa.model.admin.BookSn;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.admin.BookBorRetService;
import com.htsoft.oa.service.admin.BookService;
import com.htsoft.oa.service.admin.BookSnService;

import flexjson.JSONSerializer;

public class BookBorRetAction extends BaseAction {

	@Resource
	private BookBorRetService bookBorRetService;
	private BookBorRet bookBorRet;

	@Resource
	private BookSnService bookSnService;

	@Resource
	private BookService bookService;
	private Long recordId;
	private Long bookSnId;

	public Long getBookSnId() {
		return this.bookSnId;
	}

	public void setBookSnId(Long bookSnId) {
		this.bookSnId = bookSnId;
	}

	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public BookBorRet getBookBorRet() {
		return this.bookBorRet;
	}

	public void setBookBorRet(BookBorRet bookBorRet) {
		this.bookBorRet = bookBorRet;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.bookBorRetService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "borrowTime",
				"returnTime", "lastReturnTime" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String listBorrow() {
		PagingBean pb = getInitPagingBean();
		List list = this.bookBorRetService.getBorrowInfo(pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				pb.getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "borrowTime",
				"returnTime", "lastReturnTime" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String listReturn() {
		PagingBean pb = getInitPagingBean();
		List list = this.bookBorRetService.getReturnInfo(pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				pb.getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "borrowTime",
				"returnTime", "lastReturnTime" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.bookBorRetService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		BookBorRet bookBorRet = (BookBorRet) this.bookBorRetService.get(this.recordId);
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "borrowTime",
				"returnTime", "lastReturnTime" });
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(serializer.exclude(new String[] { "class" }).serialize(bookBorRet));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String saveBorrow() {
		Long snId = this.bookBorRet.getBookSn().getBookSnId();
		Long recordId = this.bookBorRetService.getBookBorRetId(snId);
		if (recordId != null) {
			this.bookBorRetService.remove(recordId);
		}
		this.bookBorRet.setBorrowTime(new Date());
		AppUser user = ContextUtil.getCurrentUser();
		this.bookBorRet.setRegisterName(user.getFullname());
		this.bookBorRetService.save(this.bookBorRet);
		BookSn bookSn = (BookSn) this.bookSnService.get(this.bookBorRet.getBookSnId());

		bookSn.setStatus((short) 1);
		this.bookSnService.save(bookSn);
		Book book = (Book) this.bookService.get(bookSn.getBookId());

		book.setLeftAmount(Integer.valueOf(book.getLeftAmount().intValue() - 1));
		this.bookService.save(book);
		setJsonString("{success:true}");
		return "success";
	}

	public String saveReturn() {
		this.bookBorRet.setLastReturnTime(new Date());
		AppUser user = ContextUtil.getCurrentUser();
		this.bookBorRet.setRegisterName(user.getFullname());
		this.bookBorRetService.save(this.bookBorRet);
		BookSn bookSn = (BookSn) this.bookSnService.get(this.bookBorRet.getBookSnId());

		bookSn.setStatus((short) 0);
		this.bookSnService.save(bookSn);
		Book book = (Book) this.bookService.get(bookSn.getBookId());

		book.setLeftAmount(Integer.valueOf(book.getLeftAmount().intValue() + 1));
		this.bookService.save(book);
		setJsonString("{success:true}");
		return "success";
	}

	public String getBorRetTime() {
		Long bookSnId = Long.valueOf(getRequest().getParameter("bookSnId"));
		BookBorRet bookBorRet = this.bookBorRetService.getByBookSnId(bookSnId);
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "borrowTime",
				"returnTime" });
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(serializer.exclude(new String[] { "class" }).serialize(bookBorRet));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}
}
