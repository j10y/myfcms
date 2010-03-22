package com.zving.framework.utility;

import java.util.Enumeration;
import java.util.NoSuchElementException;

class IntHashtableEnumerator implements Enumeration {
	boolean keys;
	int index;
	IntHashtableEntry[] table;
	IntHashtableEntry entry;

	IntHashtableEnumerator(IntHashtableEntry[] table, boolean keys) {
		this.table = table;
		this.keys = keys;
		this.index = table.length;
	}

	public boolean hasMoreElements() {
		if (entry != null)
			return true;
		while (index-- > 0)
			if ((entry = table[index]) != null)
				return true;
		return false;
	}

	public Object nextElement() {
		if (entry == null)
			while (index-- > 0 && (entry = table[index]) == null)
				;
		if (entry != null) {
			IntHashtableEntry e = entry;
			entry = e.next;
			return keys ? new Integer(e.key) : e.value;
		} else {
			throw new NoSuchElementException("IntHashtableEnumerator");
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.utility.IntHashtableEnumerator JD-Core Version: 0.5.3
 */