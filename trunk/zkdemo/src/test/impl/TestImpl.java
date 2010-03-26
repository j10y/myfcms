/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 24, 2010</p>
 * <p>更新：</p>
 */
package test.impl;

import java.util.ArrayList;
import java.util.List;

import test.Test;

/**
 * @author xiacc
 *
 * 描述：
 */
public class TestImpl implements Test {

	/* (non-Javadoc)
	 * @see test.DataSource#getList()
	 */
	public List getList() {
	    List list = new ArrayList();
	       list.add("Tom");
	       list.add("Henri");
	       list.add("Jim");
	  
	       return list;
	}

	/* (non-Javadoc)
	 * @see test.DataSource#getList(java.lang.String)
	 */
	public List getList(String name) {
		List list = new ArrayList();
	       list.add(name+"Tom");
	       list.add(name+"Henri");
	       list.add(name+"Jim");
		return list;
	}

}
