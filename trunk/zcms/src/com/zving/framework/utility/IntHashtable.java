package com.zving.framework.utility;

import java.util.Dictionary;
import java.util.Enumeration;

class IntHashtable extends Dictionary implements Cloneable {
	private IntHashtableEntry[] table;
	private int count;
	private int threshold;
	private float loadFactor;

	public IntHashtable(int initialCapacity, float loadFactor) {
		if ((initialCapacity <= 0) || (loadFactor <= 0.0D)) {
			throw new IllegalArgumentException();
		}
		this.loadFactor = loadFactor;
		this.table = new IntHashtableEntry[initialCapacity];
		this.threshold = (int) (initialCapacity * loadFactor);
	}

	public IntHashtable(int initialCapacity) {
		this(initialCapacity, 0.75F);
	}

	public IntHashtable() {
		this(101, 0.75F);
	}

	public int size() {
		return this.count;
	}

	public boolean isEmpty() {
		return (this.count == 0);
	}

	public synchronized Enumeration keys() {
		return new IntHashtableEnumerator(this.table, true);
	}

	public synchronized Enumeration elements() {
		return new IntHashtableEnumerator(this.table, false);
	}

	public synchronized boolean contains(Object value) {
		if (value == null) {
			throw new NullPointerException();
		}
		IntHashtableEntry[] tab = this.table;
		for (int i = tab.length; i-- > 0;) {
			for (IntHashtableEntry e = tab[i]; e != null; e = e.next) {
				if (e.value.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	public synchronized boolean containsKey(int key) {
		IntHashtableEntry[] tab = this.table;
		int hash = key;
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (IntHashtableEntry e = tab[index]; e != null; e = e.next) {
			if ((e.hash == hash) && (e.key == key)) {
				return true;
			}
		}
		return false;
	}

	public synchronized Object get(int key) {
		IntHashtableEntry[] tab = this.table;
		int hash = key;
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (IntHashtableEntry e = tab[index]; e != null; e = e.next) {
			if ((e.hash == hash) && (e.key == key)) {
				return e.value;
			}
		}
		return null;
	}

	public Object get(Object okey) {
		if (!(okey instanceof Integer)) {
			throw new InternalError("key is not an Integer");
		}
		Integer ikey = (Integer) okey;
		int key = ikey.intValue();
		return get(key);
	}

	protected void rehash() {
		int oldCapacity = this.table.length;
		IntHashtableEntry[] oldTable = this.table;

		int newCapacity = oldCapacity * 2 + 1;
		IntHashtableEntry[] newTable = new IntHashtableEntry[newCapacity];

		this.threshold = (int) (newCapacity * this.loadFactor);
		this.table = newTable;

		for (int i = oldCapacity; i-- > 0;)
			for (IntHashtableEntry old = oldTable[i]; old != null;) {
				IntHashtableEntry e = old;
				old = old.next;

				int index = (e.hash & 0x7FFFFFFF) % newCapacity;
				e.next = newTable[index];
				newTable[index] = e;
			}
	}

	public synchronized Object put(int key, Object value) {
		if (value == null) {
			throw new NullPointerException();
		}

		IntHashtableEntry[] tab = this.table;
		int hash = key;
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (IntHashtableEntry e = tab[index]; e != null; e = e.next) {
			if ((e.hash == hash) && (e.key == key)) {
				Object old = e.value;
				e.value = value;
				return old;
			}
		}

		if (this.count >= this.threshold) {
			rehash();
			return put(key, value);
		}

		IntHashtableEntry e = new IntHashtableEntry();
		e.hash = hash;
		e.key = key;
		e.value = value;
		e.next = tab[index];
		tab[index] = e;
		this.count += 1;
		return null;
	}

	public Object put(Object okey, Object value) {
		if (!(okey instanceof Integer)) {
			throw new InternalError("key is not an Integer");
		}
		Integer ikey = (Integer) okey;
		int key = ikey.intValue();
		return put(key, value);
	}

	public synchronized Object remove(int key) {
		IntHashtableEntry[] tab = this.table;
		int hash = key;
		int index = (hash & 0x7FFFFFFF) % tab.length;
		IntHashtableEntry e = tab[index];
		for (IntHashtableEntry prev = null; e != null; e = e.next) {
			if ((e.hash == hash) && (e.key == key)) {
				if (prev != null)
					prev.next = e.next;
				else {
					tab[index] = e.next;
				}
				this.count -= 1;
				return e.value;
			}
			prev = e;
		}

		return null;
	}

	public Object remove(Object okey) {
		if (!(okey instanceof Integer)) {
			throw new InternalError("key is not an Integer");
		}
		Integer ikey = (Integer) okey;
		int key = ikey.intValue();
		return remove(key);
	}

	public synchronized void clear() {
		IntHashtableEntry[] tab = this.table;
		for (int index = tab.length; --index >= 0;) {
			tab[index] = null;
		}
		this.count = 0;
	}

	public synchronized Object clone() {
		try {
			IntHashtable t = (IntHashtable) super.clone();
			t.table = new IntHashtableEntry[this.table.length];
			for (int i = this.table.length; i-- > 0;) {
				t.table[i] = ((this.table[i] != null) ? (IntHashtableEntry) this.table[i].clone()
						: null);
			}
			return t;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	public synchronized String toString() {
		int max = size() - 1;
		StringBuffer buf = new StringBuffer();
		Enumeration k = keys();
		Enumeration e = elements();
		buf.append("{");

		for (int i = 0; i <= max; ++i) {
			String s1 = k.nextElement().toString();
			String s2 = e.nextElement().toString();
			buf.append(s1 + "=" + s2);
			if (i < max) {
				buf.append(", ");
			}
		}
		buf.append("}");
		return buf.toString();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.utility.IntHashtable JD-Core Version: 0.5.3
 */