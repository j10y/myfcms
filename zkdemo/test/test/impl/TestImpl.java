/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 24, 2010</p>
 * <p>���£�</p>
 */
package test.impl;

import java.util.ArrayList;
import java.util.List;

import test.Test;

/**
 * @author xiacc
 *
 * ������
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
