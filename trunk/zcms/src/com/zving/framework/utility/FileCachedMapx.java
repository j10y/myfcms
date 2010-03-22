package com.zving.framework.utility;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.apache.commons.lang.ArrayUtils;

public class FileCachedMapx {
	private Mapx map;
	private boolean compressible;
	private int total;
	private int size;
	private int modCount;
	private short maxFileIndex;
	private int maxItemInMemory;
	private String cacheDirectory;
	private BufferedRandomAccessFile[] addressFiles;
	private BufferedRandomAccessFile[] keyFiles;
	private BufferedRandomAccessFile[] valueFiles;
	private static final int AddressCountInOneFile = 268435456;
	private static final int MaxFileSize = 2097152000;
	private static final int DefaultCountInMemory = 100;
	private int addressFileCount;

	public FileCachedMapx(String cacheDir) {
		this(cacheDir, 65536, 100);
	}

	public FileCachedMapx(String cacheDir, boolean compressiable) {
		this(cacheDir, 65536, compressiable, 100);
	}

	public FileCachedMapx(String cacheDir, int initEntrySize) {
		this(cacheDir, initEntrySize, true, 100);
	}

	public FileCachedMapx(String cacheDir, boolean compressiable, int maxItemInMemory) {
		this(cacheDir, 65536, compressiable, maxItemInMemory);
	}

	public FileCachedMapx(String cacheDir, int initEntrySize, int maxItemInMemory) {
		this(cacheDir, initEntrySize, true, maxItemInMemory);
	}

	public FileCachedMapx(String cacheDir, int initEntrySize, boolean compressiable,
			int maxItemInMemory) {
		this.maxItemInMemory = 1000;

		this.addressFiles = null;

		this.keyFiles = null;

		this.valueFiles = null;

		this.cacheDirectory = cacheDir;
		this.total = new Double(Math.pow(2.0D, Math.ceil(Math.log(initEntrySize) / Math.log(2.0D))))
				.intValue();
		this.compressible = compressiable;
		this.maxItemInMemory = maxItemInMemory;
		this.map = new Mapx(maxItemInMemory);
		loadInfo();
	}

	private void initFiles() {
		try {
			this.addressFileCount = new Double(Math.ceil(this.total * 1.0D / 268435456.0D))
					.intValue();
			this.addressFiles = new BufferedRandomAccessFile[this.addressFileCount];
			int prefix = new Double(Math.log(this.total / 16) / Math.log(2.0D)).intValue();
			for (int i = 0; i < this.addressFileCount; ++i) {
				this.addressFiles[i] = new BufferedRandomAccessFile(this.cacheDirectory + prefix
						+ "key" + i + ".idx", "rw");
				if (i == this.addressFileCount - 1) {
					this.addressFiles[i]
							.setLength((this.total - ((this.addressFileCount - 1) * 268435456)) * 9);
				}
			}
			this.keyFiles = new BufferedRandomAccessFile[this.maxFileIndex + 1];
			this.valueFiles = new BufferedRandomAccessFile[this.maxFileIndex + 1];
			for (int i = 0; i <= this.maxFileIndex; ++i) {
				this.keyFiles[i] = new BufferedRandomAccessFile(this.cacheDirectory + "key" + i
						+ ".dat", "rw");
				this.valueFiles[i] = new BufferedRandomAccessFile(this.cacheDirectory + "value" + i
						+ ".dat", "rw");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeData(String k, Object v) {
		int index = hash(k);
		int c = index % 268435456;
		index /= 268435456;
		if (this.addressFiles == null)
			initFiles();
		try {
			BufferedRandomAccessFile f = this.addressFiles[index];
			f.seek(c * 9);
			Key key = getKey(f);
			if (key == null) {
				f.seek(c * 9);
				writeFile(f, k, v);
				return;
			}
			if (key.KeyString.equals(k)) {
				updateByKey(key, v);
				return;
			}
			do {
				f = this.keyFiles[key.KeyFileIndex];
				int pos = key.keyAddress + key.KeyLength - 9;
				f.seek(pos);
				key = getKey(f);
				if (key != null)
					continue;
				f.seek(pos);
				writeFile(f, k, v);
				return;
			} while (!(key.KeyString.equals(k)));
			updateByKey(key, v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeFile(BufferedRandomAccessFile f, String k, Object v) throws IOException {
		f.writeByte(1);
		f.writeShort(this.maxFileIndex);

		byte[] bk = k.getBytes();
		byte[] bv = toBytes(v);

		f.writeShort(22 + bk.length);
		BufferedRandomAccessFile kf = this.keyFiles[this.maxFileIndex];
		BufferedRandomAccessFile vf = this.valueFiles[this.maxFileIndex];
		int kpos = (int) kf.length();

		long kNewSize = kpos + 22 + bk.length;
		if (kNewSize > 2097152000L) {
			FileCachedMapx tmp88_87 = this;
			tmp88_87.maxFileIndex = (short) (tmp88_87.maxFileIndex + 1);
			BufferedRandomAccessFile fk = new BufferedRandomAccessFile(this.cacheDirectory + "key"
					+ this.maxFileIndex + ".dat", "rw");
			BufferedRandomAccessFile fv = new BufferedRandomAccessFile(this.cacheDirectory
					+ "value" + this.maxFileIndex + ".dat", "rw");
			this.keyFiles = ((BufferedRandomAccessFile[]) ArrayUtils.add(this.keyFiles, fk));
			this.valueFiles = ((BufferedRandomAccessFile[]) ArrayUtils.add(this.valueFiles, fv));
			kf = this.keyFiles[this.maxFileIndex];
			kNewSize = 22 + bk.length;
			kpos = 0;
		}

		int vpos = (int) vf.length();
		int vNewSize = vpos + 9 + bk.length + bv.length;
		if (vNewSize > 2097152000) {
			FileCachedMapx tmp274_273 = this;
			tmp274_273.maxFileIndex = (short) (tmp274_273.maxFileIndex + 1);
			BufferedRandomAccessFile fk = new BufferedRandomAccessFile(this.cacheDirectory + "key"
					+ this.maxFileIndex + ".dat", "rw");
			BufferedRandomAccessFile fv = new BufferedRandomAccessFile(this.cacheDirectory
					+ "value" + this.maxFileIndex + ".dat", "rw");
			this.keyFiles = ((BufferedRandomAccessFile[]) ArrayUtils.add(this.keyFiles, fk));
			this.valueFiles = ((BufferedRandomAccessFile[]) ArrayUtils.add(this.valueFiles, fv));
			vf = this.valueFiles[this.maxFileIndex];
			vNewSize = 9 + bk.length + bv.length;
			vpos = 0;
		}
		f.writeInt(kpos);

		kf.setLength(kNewSize);
		kf.seek(kpos);
		kf.writeByte(1);
		kf.writeShort(this.maxFileIndex);

		kf.writeInt(9 + bk.length + bv.length);
		kf.writeInt(vpos);
		kf.writeShort(bk.length);
		kf.write(bk);

		vf.setLength(vNewSize);
		vf.seek(vpos);
		vf.writeByte(1);
		vf.writeInt(bk.length);
		vf.writeInt(bv.length);
		vf.write(bk);
		vf.write(bv);
	}

	private void updateByKey(Key key, Object v) throws IOException {
		byte[] bv = toBytes(v);
		BufferedRandomAccessFile f = this.valueFiles[key.DataFileIndex];
		int pos = (int) f.length();
		int newDataLength = key.KeyLength - 13 + bv.length;
		if (newDataLength > key.DataLength) {
			if (key.DataFileIndex < this.maxFileIndex) {
				key.DataFileIndex = this.maxFileIndex;
				f = this.valueFiles[this.maxFileIndex];
				pos = (int) f.length();
			}

			int newSize = pos + newDataLength;
			if (newSize > 2097152000) {
				f.seek(key.DataAddress);
				f.writeByte(0);
				FileCachedMapx tmp115_114 = this;
				tmp115_114.maxFileIndex = (short) (tmp115_114.maxFileIndex + 1);
				BufferedRandomAccessFile fk = new BufferedRandomAccessFile(this.cacheDirectory
						+ "key" + this.maxFileIndex + ".dat", "rw");
				BufferedRandomAccessFile fv = new BufferedRandomAccessFile(this.cacheDirectory
						+ "value" + this.maxFileIndex + ".dat", "rw");
				this.keyFiles = ((BufferedRandomAccessFile[]) ArrayUtils.add(this.keyFiles, fk));
				this.valueFiles = ((BufferedRandomAccessFile[]) ArrayUtils.add(this.valueFiles, fv));
				f = this.valueFiles[this.maxFileIndex];
				key.DataFileIndex = this.maxFileIndex;
				pos = 0;
				f.setLength(newDataLength);
			} else {
				pos = (int) f.length();
				f.setLength(newSize);
			}

			f = this.keyFiles[key.KeyFileIndex];
			f.seek(key.keyAddress + 1);
			f.writeShort(this.maxFileIndex);
			f.writeInt(newDataLength);
			f.writeInt(pos);
			this.modCount += 1;
		} else {
			f = this.keyFiles[key.KeyFileIndex];
			f.seek(key.keyAddress + 3);
			f.writeInt(newDataLength);
			pos = key.DataAddress;
		}

		f = this.valueFiles[key.DataFileIndex];
		f.seek(pos);
		f.writeByte(1);
		byte[] bk = key.KeyString.getBytes();
		f.writeInt(bk.length);
		f.writeInt(bv.length);
		f.write(bk);
		f.write(bv);
	}

	private Key getKey(BufferedRandomAccessFile f) throws IOException {
		if ((f.length() == 0L) || (f.readByte() == 0)) {
			return null;
		}
		Key key = new Key();
		key.KeyFileIndex = f.readShort();
		key.KeyLength = f.readShort();
		key.keyAddress = f.readInt();

		f = this.keyFiles[key.KeyFileIndex];
		f.seek(key.keyAddress);
		if (f.readByte() == 0) {
			return null;
		}

		key.DataFileIndex = f.readShort();
		key.DataLength = f.readInt();
		key.DataAddress = f.readInt();
		f.readShort();
		byte[] bs = (byte[]) null;
		try {
			bs = new byte[key.KeyLength - 22];
		} catch (Exception e) {
			e.printStackTrace();
		}
		f.read(bs);
		key.KeyString = new String(bs);
		return key;
	}

	private Key readKey(int index, String k) {
		int c = index % 268435456;
		index /= 268435456;
		if (this.addressFiles == null)
			initFiles();
		try {
			BufferedRandomAccessFile f = this.addressFiles[index];
			f.seek(c * 9);
			Key key = getKey(f);
			if ((key == null) || (key.KeyString.equals(k))) {
				return key;
			}
			do {
				f = this.keyFiles[key.KeyFileIndex];
				int pos = key.keyAddress + key.KeyLength - 9;
				f.seek(pos);
				key = getKey(f);
			} while ((key != null) && (!(key.KeyString.equals(k))));
			return key;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object readData(String k) throws IOException {
		int i = hash(k);
		Key key = readKey(i, k);
		if (key == null) {
			return null;
		}
		BufferedRandomAccessFile f = this.valueFiles[key.DataFileIndex];
		f.seek(key.DataAddress + 9 + key.KeyLength - 22);
		byte[] bv = new byte[key.DataLength - 9 - key.KeyLength + 22];
		f.read(bv);
		return toObject(bv);
	}

	private Object toObject(byte bs[]) {
		if (bs == null || bs.length == 0)
			return null;
		if (compressible)
			try {
				bs = ZipUtil.unzip(bs);
			} catch (Exception e) {
				return null;
			}
		if (bs.length == 0)
			return null;
		byte type = bs[0];
		bs = ArrayUtils.subarray(bs, 1, bs.length);
		if (type == 1)
			return new Integer(NumberUtil.toInt(bs, 0));
		if (type == 2)
			return new Long(NumberUtil.toLong(bs));
		if (type == 3)
			return new String(bs);
		if (type == 4)
			return bs;
		if (type == 5) {
			int arr[] = new int[bs.length / 4];
			for (int i = 0; i < arr.length; i++)
				arr[i] = NumberUtil.toInt(bs, i * 4);

			return arr;
		}
		if (type == 6) {
			long arr[] = new long[bs.length / 8];
			for (int i = 0; i < arr.length; i++)
				arr[i] = NumberUtil.toInt(bs, i * 8);

			return arr;
		}
		if (type == 7) {
			ByteBuffer bb = ByteBuffer.allocate(bs.length);
			bb.put(bs);
			bb.flip();
			ArrayList arr = new ArrayList();
			int index = 0;
			do {
				int length = bb.getInt();
				byte t[] = new byte[length];
				bb.get(t);
				arr.add(new String(t));
				index += 4 + length;
			} while (index != bs.length);
			String r[] = new String[arr.size()];
			for (int i = 0; i < r.length; i++)
				r[i] = (String) arr.get(i);

			return r;
		}
		if (type == 8)
			return FileUtil.unserialize(bs);
		else
			return null;
	}

	private byte[] toBytes(Object v) {
		byte type = 0;
		byte[] bs = (byte[]) null;
		if (v instanceof Integer) {
			type = 1;
			bs = NumberUtil.toBytes(((Integer) v).intValue());
		} else if (v instanceof Long) {
			type = 2;
			bs = NumberUtil.toBytes(((Long) v).longValue());
		} else if (v instanceof String) {
			type = 3;
			bs = ((String) v).getBytes();
		} else if (v instanceof byte[]) {
			type = 4;
			bs = (byte[]) v;
		} else {
			int i;
			if (v instanceof int[]) {
				type = 5;
				int[] arr = (int[]) v;
				bs = new byte[4 * arr.length];
				for (i = 0; i < arr.length; ++i)
					NumberUtil.toBytes(arr[i], bs, i * 4);
			} else if (v instanceof long[]) {
				type = 6;
				long[] arr = (long[]) v;
				bs = new byte[8 * arr.length];
				for (i = 0; i < arr.length; ++i)
					NumberUtil.toBytes(arr[i], bs, i * 8);
			} else if (v instanceof String[]) {
				type = 7;
				String[] arr = (String[]) v;
				bs = new byte[0];
				for (i = 0; i < arr.length; ++i) {
					byte[] b = (byte[]) null;
					b = arr[i].getBytes();
					bs = ArrayUtils.addAll(bs, NumberUtil.toBytes(b.length));
					bs = ArrayUtils.addAll(bs, b);
				}
			} else if (v instanceof Serializable) {
				type = 8;
				bs = FileUtil.serialize((Serializable) v);
			}
		}
		if (this.compressible) {
			return ZipUtil.zip(ArrayUtils.add(bs, 0, type));
		}
		return ArrayUtils.add(bs, 0, type);
	}

	private void loadInfo() {
		File f = new File(this.cacheDirectory);
		if (!(f.exists())) {
			f.mkdirs();
		}

		f = new File(this.cacheDirectory + "meta.dat");
		if (f.exists()) {
			byte[] bs = FileUtil.readByte(f);
			this.size = NumberUtil.toInt(bs, 0);
			this.total = NumberUtil.toInt(bs, 4);
			this.modCount = NumberUtil.toInt(bs, 8);
			this.maxFileIndex = NumberUtil.toShort(bs, 12);
			this.compressible = (NumberUtil.toShort(bs, 14) == 1);
		} else {
			this.size = 0;

			this.maxFileIndex = 0;
			this.modCount = 0;
		}
	}

	public synchronized void save() {
		if (this.cacheDirectory != null) {
			File f = new File(this.cacheDirectory + "meta.dat");
			byte[] bs = new byte[16];
			NumberUtil.toBytes(this.size, bs, 0);
			NumberUtil.toBytes(this.total, bs, 4);
			NumberUtil.toBytes(this.modCount, bs, 8);
			NumberUtil.toBytes(this.maxFileIndex, bs, 12);
			NumberUtil.toBytes((short) ((this.compressible) ? 1 : 0), bs, 14);
			FileUtil.writeByte(f, bs);
		}
	}

	public synchronized void close() {
		save();
		int i;
		if (this.keyFiles != null) {
			for (i = 0; i < this.keyFiles.length; ++i) {
				try {
					this.keyFiles[i].close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (this.addressFiles != null) {
			for (i = 0; i < this.addressFiles.length; ++i) {
				try {
					this.addressFiles[i].close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (this.valueFiles != null)
			for (i = 0; i < this.valueFiles.length; ++i)
				try {
					this.valueFiles[i].close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

	public synchronized boolean containsKey(String k) {
		if (this.map.containsKey(k)) {
			return true;
		}
		int i = hash(k);

		return (readKey(i, k) != null);
	}

	public synchronized Entry firstEntry() {
		return Entry.first(this);
	}

	private synchronized void put2(String k, Object value) {
		Object o = null;
		if (maxItemInMemory != 0)
			o = map.put(k, value);
		try {
			if (o == null) {
				int i = hash(k);
				Key key = readKey(i, k);
				if (key == null) {
					writeData(k, value);
					size++;
					if ((double) size > (double) total * 0.75D)
						resize();
				} else {
					writeData(k, value);
				}
			} else {
				writeData(k, value);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		save();
	}

	public void put(String k, Serializable v) {
		put2(k, v);
	}

	public void put(String k, int v) {
		put2(k, new Integer(v));
	}

	public void put(String k, long v) {
		put2(k, new Long(v));
	}

	public void put(String k, String v) {
		put2(k, v);
	}

	public void put(String k, byte[] v) {
		put2(k, v);
	}

	public void put(String k, int[] v) {
		put2(k, v);
	}

	public void put(String k, long[] v) {
		put2(k, v);
	}

	public void put(String k, String[] v) {
		put2(k, v);
	}

	public int getInt(String key) {
		Object o = get(key);
		if (o == null) {
			return -2147483648;
		}
		if (o instanceof Integer) {
			return ((Integer) o).intValue();
		}
		throw new RuntimeException("Key对应的数据不是指定类型!");
	}

	public long getLong(String key) {
		Object o = get(key);
		if (o == null) {
			return -9223372036854775808L;
		}
		if (o instanceof Long) {
			return ((Long) o).longValue();
		}
		throw new RuntimeException("Key对应的数据不是指定类型!");
	}

	public String getString(String key) {
		Object o = get(key);
		if (o == null) {
			return null;
		}
		if (o instanceof String) {
			return ((String) o);
		}
		throw new RuntimeException("Key对应的数据不是指定类型!");
	}

	public byte[] getByteArray(String key) {
		Object o = get(key);
		if (o == null) {
			return null;
		}
		if (o instanceof byte[]) {
			return ((byte[]) o);
		}
		throw new RuntimeException("Key对应的数据不是指定类型:" + key);
	}

	public int[] getIntArray(String key) {
		Object o = get(key);
		if (o == null) {
			return null;
		}
		if (o instanceof int[]) {
			return ((int[]) o);
		}
		throw new RuntimeException("Key对应的数据不是指定类型!");
	}

	public long[] getLongArray(String key) {
		Object o = get(key);
		if (o == null) {
			return null;
		}
		if (o instanceof long[]) {
			return ((long[]) o);
		}
		throw new RuntimeException("Key对应的数据不是指定类型!");
	}

	public String[] getStringArray(String key) {
		Object o = get(key);
		if (o == null) {
			return null;
		}
		if (o instanceof String[]) {
			return ((String[]) o);
		}
		throw new RuntimeException("Key对应的数据不是指定类型!");
	}

	private synchronized void resize() throws IOException {
		if (this.size < this.total * 0.75D) {
			return;
		}
		int total2 = this.total * 2;
		int fileCount = new Double(Math.ceil(total2 * 1.0D / 268435456.0D)).intValue();
		BufferedRandomAccessFile[] files = new BufferedRandomAccessFile[fileCount];
		int prefix = new Double(Math.log(total2 / 16) / Math.log(2.0D)).intValue();
		for (int i = 0; i < fileCount; ++i) {
			files[i] = new BufferedRandomAccessFile(this.cacheDirectory + prefix + "key" + i
					+ ".idx", "rw");
			if (i == this.addressFileCount - 1)
				files[i].setLength((total2 - ((this.addressFileCount - 1) * 268435456)) * 9);
			else {
				files[i].setLength(-1879048192L);
			}
		}

		byte[] empty = new byte[9];
		for (int i = 0; i < this.keyFiles.length; ++i) {
			BufferedRandomAccessFile f = this.keyFiles[i];
			int pos = 0;
			while (f.length() > pos) {
				f.seek(pos);
				byte deleted = f.readByte();
				f.skipBytes(10);
				short len = f.readShort();

				if (deleted == 1) {
					byte[] bs = new byte[len];
					f.read(bs);
					f.seek(pos + 13 + len);
					f.write(empty);
					String k = new String(bs);
					int index = hash(k, total2);
					int c = index % 268435456;
					index /= 268435456;
					BufferedRandomAccessFile af = files[index];
					af.seek(c * 9);
					if (af.readByte() == 0) {
						af.seek(c * 9);
						af.writeByte(1);
						af.writeShort(i);
						af.writeShort(22 + bs.length);
						af.writeInt(pos);
					} else {
						af.seek(c * 9);
						Key key = getKey(af);
						if (key == null)
							throw new RuntimeException("发生致命错误，应当有Key的位置未找到Key.");
						BufferedRandomAccessFile kf;
						int pos2;
						do {
							kf = this.keyFiles[key.KeyFileIndex];
							pos2 = key.keyAddress + key.KeyLength - 9;
							kf.seek(pos2);
							key = getKey(kf);
						} while (key != null);

						kf.seek(pos2);
						kf.writeByte(1);
						kf.writeShort(i);
						kf.writeShort(22 + bs.length);
						kf.writeInt(pos);
					}

				}

				pos = pos + len + 22;
			}
		}

		BufferedRandomAccessFile[] tmp = this.addressFiles;
		this.addressFiles = files;
		this.total = total2;
		for (int i = 0; i < tmp.length; ++i)
			tmp[i].delete();
	}

	public synchronized Serializable get(String k) {
		Object o = null;
		if (this.maxItemInMemory != 0) {
			o = this.map.get(k);
		}
		if (o != null)
			return ((Serializable) o);
		try {
			return ((Serializable) readData(k));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized boolean remove(String k) {
		this.map.remove(k);
		int index = hash(k);
		int c = index % 268435456;
		index /= 268435456;
		try {
			if (this.addressFiles == null) {
				initFiles();
			}
			BufferedRandomAccessFile f = this.addressFiles[index];
			f.seek(c * 9);
			Key key = getKey(f);
			if (key == null) {
				return false;
			}
			if (key.KeyString.equals(k)) {
				f = this.keyFiles[key.KeyFileIndex];
				int pos = key.keyAddress + key.KeyLength - 9;
				f.seek(pos);
				if (f.readByte() == 0) {
					f = this.addressFiles[index];
					f.seek(c * 9);
					f.writeByte(0);
				} else {
					f.seek(pos);
					byte[] bs = new byte[9];
					f.read(bs);
					f = this.addressFiles[index];
					f.seek(c * 9);
					f.write(bs);
				}
				f = this.keyFiles[key.KeyFileIndex];
				f.seek(key.keyAddress);
				f.writeByte(0);

				f = this.valueFiles[key.DataFileIndex];
				f.seek(key.DataAddress);
				f.writeByte(0);
				this.size -= 1;
				this.modCount += 1;
				save();
				return true;
			}
			int index2;
			int pos2;
			do {
				index2 = key.KeyFileIndex;
				f = this.keyFiles[index2];
				pos2 = key.keyAddress + key.KeyLength - 9;
				f.seek(pos2);
				key = getKey(f);
				if (key == null)
					return false;
			} while (!(key.KeyString.equals(k)));
			f = this.keyFiles[key.KeyFileIndex];
			f.seek(key.keyAddress);
			f.writeByte(0);
			int pos = key.keyAddress + key.KeyLength - 9;
			f.seek(pos);
			if (f.readByte() == 1) {
				f.seek(pos);
				byte[] bs = new byte[9];
				f.read(bs);
				f = this.keyFiles[index2];
				f.seek(pos2);
				f.write(bs);
			}

			f = this.valueFiles[key.DataFileIndex];
			f.seek(key.DataAddress);
			f.writeByte(0);
			this.size -= 1;
			this.modCount += 1;
			save();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public synchronized void refactor() {
		if (this.total * 0.5D > this.modCount)
			return;
	}

	private int hash(Object x) {
		return hash(x, this.total);
	}

	public static int hash(Object x, int length) {
		int h = x.hashCode();
		h += (h << 9 ^ 0xFFFFFFFF);
		h ^= h >>> 14;
		h += (h << 4);
		h ^= h >>> 10;
		return (h & length - 1);
	}

	public int size() {
		return this.size;
	}

	public String getCacheDirectory() {
		return this.cacheDirectory;
	}

	public void setCacheDirectory(String cacheDirectory) {
		if ((cacheDirectory.endsWith("/")) || (cacheDirectory.endsWith("\\"))) {
			cacheDirectory = cacheDirectory + "/";
		}
		this.cacheDirectory = cacheDirectory;
	}

	public int getMaxItemInMemory() {
		return this.maxItemInMemory;
	}

	public void setMaxItemInMemory(int maxItemInMemory) {
		this.maxItemInMemory = maxItemInMemory;
	}

	public boolean isCompressible() {
		return this.compressible;
	}

	public static class Entry {
		private FileCachedMapx fcm;
		private FileCachedMapx.Key key;

		protected Entry(FileCachedMapx fcm, FileCachedMapx.Key key) {
			this.fcm = fcm;
			this.key = key;
		}

		public String getKey() {
			return this.key.KeyString;
		}

		public Object getValue() {
			BufferedRandomAccessFile f;
			synchronized (this.fcm) {
				f = this.fcm.valueFiles[this.key.DataFileIndex];
			}

			return null;
		}

		// ERROR //
		protected static Entry first(FileCachedMapx fcm) {
			return null;
			// Byte code:
			// 0: aload_0
			// 1: dup
			// 2: astore_1
			// 3: monitorenter
			// 4: aload_0
			// 5: invokestatic 80
			// com/zving/framework/utility/FileCachedMapx:access$2
			// (Lcom/zving/framework/utility/FileCachedMapx;)[Lcom/zving/framework/utility/BufferedRandomAccessFile;
			// 8: ifnonnull +7 -> 15
			// 11: aload_0
			// 12: invokestatic 83
			// com/zving/framework/utility/FileCachedMapx:access$3
			// (Lcom/zving/framework/utility/FileCachedMapx;)V
			// 15: aload_0
			// 16: invokestatic 87
			// com/zving/framework/utility/FileCachedMapx:access$4
			// (Lcom/zving/framework/utility/FileCachedMapx;)[Lcom/zving/framework/utility/BufferedRandomAccessFile;
			// 19: iconst_0
			// 20: aaload
			// 21: astore_2
			// 22: iconst_0
			// 23: istore_3
			// 24: new 26 com/zving/framework/utility/FileCachedMapx$Key
			// 27: dup
			// 28: invokespecial 90
			// com/zving/framework/utility/FileCachedMapx$Key:<init> ()V
			// 31: astore 4
			// 33: goto +119 -> 152
			// 36: aload_2
			// 37: iload_3
			// 38: i2l
			// 39: invokevirtual 50
			// com/zving/framework/utility/BufferedRandomAccessFile:seek (J)V
			// 42: aload_2
			// 43: invokevirtual 91
			// com/zving/framework/utility/BufferedRandomAccessFile:readByte ()B
			// 46: istore 5
			// 48: aload 4
			// 50: aload_2
			// 51: invokevirtual 95
			// com/zving/framework/utility/BufferedRandomAccessFile:readShort
			// ()S
			// 54: putfield 39
			// com/zving/framework/utility/FileCachedMapx$Key:DataFileIndex S
			// 57: aload 4
			// 59: aload_2
			// 60: invokevirtual 99
			// com/zving/framework/utility/BufferedRandomAccessFile:readInt ()I
			// 63: putfield 56
			// com/zving/framework/utility/FileCachedMapx$Key:DataLength I
			// 66: aload 4
			// 68: aload_2
			// 69: invokevirtual 99
			// com/zving/framework/utility/BufferedRandomAccessFile:readInt ()I
			// 72: putfield 43
			// com/zving/framework/utility/FileCachedMapx$Key:DataAddress I
			// 75: aload_2
			// 76: invokevirtual 95
			// com/zving/framework/utility/BufferedRandomAccessFile:readShort
			// ()S
			// 79: istore 6
			// 81: aload 4
			// 83: iload 6
			// 85: bipush 22
			// 87: iadd
			// 88: i2s
			// 89: putfield 47
			// com/zving/framework/utility/FileCachedMapx$Key:KeyLength S
			// 92: iload 5
			// 94: iconst_1
			// 95: if_icmpne +49 -> 144
			// 98: iload 6
			// 100: newarray byte
			// 102: astore 7
			// 104: aload_2
			// 105: aload 7
			// 107: invokevirtual 59
			// com/zving/framework/utility/BufferedRandomAccessFile:read ([B)I
			// 110: pop
			// 111: aload 4
			// 113: new 103 java/lang/String
			// 116: dup
			// 117: aload 7
			// 119: invokespecial 105 java/lang/String:<init> ([B)V
			// 122: putfield 25
			// com/zving/framework/utility/FileCachedMapx$Key:KeyString
			// Ljava/lang/String;
			// 125: aload 4
			// 127: iload_3
			// 128: putfield 108
			// com/zving/framework/utility/FileCachedMapx$Key:keyAddress I
			// 131: new 1 com/zving/framework/utility/FileCachedMapx$Entry
			// 134: dup
			// 135: aload_0
			// 136: aload 4
			// 138: invokespecial 111
			// com/zving/framework/utility/FileCachedMapx$Entry:<init>
			// (Lcom/zving/framework/utility/FileCachedMapx;Lcom/zving/framework/utility/FileCachedMapx$Key;)V
			// 141: aload_1
			// 142: monitorexit
			// 143: areturn
			// 144: iload_3
			// 145: iload 6
			// 147: bipush 22
			// 149: iadd
			// 150: iadd
			// 151: istore_3
			// 152: aload_2
			// 153: invokevirtual 113
			// com/zving/framework/utility/BufferedRandomAccessFile:length ()J
			// 156: iload_3
			// 157: i2l
			// 158: lcmp
			// 159: ifgt -123 -> 36
			// 162: goto +8 -> 170
			// 165: astore_2
			// 166: aload_2
			// 167: invokevirtual 117 java/lang/Exception:printStackTrace ()V
			// 170: aload_1
			// 171: monitorexit
			// 172: goto +6 -> 178
			// 175: aload_1
			// 176: monitorexit
			// 177: athrow
			// 178: aconst_null
			// 179: areturn
			//
			// Exception table:
			// from to target type
			// 4 141 165 java/lang/Exception
			// 144 162 165 java/lang/Exception
			// 4 143 175 finally
			// 144 172 175 finally
			// 175 177 175 finally
		}

		// ERROR //
		public Entry next() {
			return null;
			// Byte code:
			// 0: aload_0
			// 1: getfield 15
			// com/zving/framework/utility/FileCachedMapx$Entry:fcm
			// Lcom/zving/framework/utility/FileCachedMapx;
			// 4: dup
			// 5: astore_1
			// 6: monitorenter
			// 7: aload_0
			// 8: getfield 15
			// com/zving/framework/utility/FileCachedMapx$Entry:fcm
			// Lcom/zving/framework/utility/FileCachedMapx;
			// 11: invokestatic 87
			// com/zving/framework/utility/FileCachedMapx:access$4
			// (Lcom/zving/framework/utility/FileCachedMapx;)[Lcom/zving/framework/utility/BufferedRandomAccessFile;
			// 14: aload_0
			// 15: getfield 17
			// com/zving/framework/utility/FileCachedMapx$Entry:key
			// Lcom/zving/framework/utility/FileCachedMapx$Key;
			// 18: getfield 128
			// com/zving/framework/utility/FileCachedMapx$Key:KeyFileIndex S
			// 21: aaload
			// 22: astore_2
			// 23: aload_0
			// 24: getfield 17
			// com/zving/framework/utility/FileCachedMapx$Entry:key
			// Lcom/zving/framework/utility/FileCachedMapx$Key;
			// 27: getfield 108
			// com/zving/framework/utility/FileCachedMapx$Key:keyAddress I
			// 30: aload_0
			// 31: getfield 17
			// com/zving/framework/utility/FileCachedMapx$Entry:key
			// Lcom/zving/framework/utility/FileCachedMapx$Key;
			// 34: getfield 47
			// com/zving/framework/utility/FileCachedMapx$Key:KeyLength S
			// 37: iadd
			// 38: istore_3
			// 39: new 26 com/zving/framework/utility/FileCachedMapx$Key
			// 42: dup
			// 43: invokespecial 90
			// com/zving/framework/utility/FileCachedMapx$Key:<init> ()V
			// 46: astore 4
			// 48: aload 4
			// 50: aload_0
			// 51: getfield 17
			// com/zving/framework/utility/FileCachedMapx$Entry:key
			// Lcom/zving/framework/utility/FileCachedMapx$Key;
			// 54: getfield 128
			// com/zving/framework/utility/FileCachedMapx$Key:KeyFileIndex S
			// 57: putfield 128
			// com/zving/framework/utility/FileCachedMapx$Key:KeyFileIndex S
			// 60: iload_3
			// 61: i2l
			// 62: aload_2
			// 63: invokevirtual 113
			// com/zving/framework/utility/BufferedRandomAccessFile:length ()J
			// 66: lcmp
			// 67: ifne +58 -> 125
			// 70: aload_0
			// 71: getfield 17
			// com/zving/framework/utility/FileCachedMapx$Entry:key
			// Lcom/zving/framework/utility/FileCachedMapx$Key;
			// 74: getfield 128
			// com/zving/framework/utility/FileCachedMapx$Key:KeyFileIndex S
			// 77: aload_0
			// 78: getfield 15
			// com/zving/framework/utility/FileCachedMapx$Entry:fcm
			// Lcom/zving/framework/utility/FileCachedMapx;
			// 81: invokestatic 131
			// com/zving/framework/utility/FileCachedMapx:access$5
			// (Lcom/zving/framework/utility/FileCachedMapx;)S
			// 84: if_icmpne +7 -> 91
			// 87: aload_1
			// 88: monitorexit
			// 89: aconst_null
			// 90: areturn
			// 91: aload 4
			// 93: dup
			// 94: getfield 128
			// com/zving/framework/utility/FileCachedMapx$Key:KeyFileIndex S
			// 97: iconst_1
			// 98: iadd
			// 99: i2s
			// 100: putfield 128
			// com/zving/framework/utility/FileCachedMapx$Key:KeyFileIndex S
			// 103: aload 4
			// 105: iconst_0
			// 106: putfield 108
			// com/zving/framework/utility/FileCachedMapx$Key:keyAddress I
			// 109: aload_0
			// 110: getfield 15
			// com/zving/framework/utility/FileCachedMapx$Entry:fcm
			// Lcom/zving/framework/utility/FileCachedMapx;
			// 113: invokestatic 87
			// com/zving/framework/utility/FileCachedMapx:access$4
			// (Lcom/zving/framework/utility/FileCachedMapx;)[Lcom/zving/framework/utility/BufferedRandomAccessFile;
			// 116: aload 4
			// 118: getfield 128
			// com/zving/framework/utility/FileCachedMapx$Key:KeyFileIndex S
			// 121: aaload
			// 122: astore_2
			// 123: iconst_0
			// 124: istore_3
			// 125: aload 4
			// 127: iload_3
			// 128: putfield 108
			// com/zving/framework/utility/FileCachedMapx$Key:keyAddress I
			// 131: goto +122 -> 253
			// 134: aload_2
			// 135: iload_3
			// 136: i2l
			// 137: invokevirtual 50
			// com/zving/framework/utility/BufferedRandomAccessFile:seek (J)V
			// 140: aload_2
			// 141: invokevirtual 91
			// com/zving/framework/utility/BufferedRandomAccessFile:readByte ()B
			// 144: istore 5
			// 146: aload 4
			// 148: aload_2
			// 149: invokevirtual 95
			// com/zving/framework/utility/BufferedRandomAccessFile:readShort
			// ()S
			// 152: putfield 39
			// com/zving/framework/utility/FileCachedMapx$Key:DataFileIndex S
			// 155: aload 4
			// 157: aload_2
			// 158: invokevirtual 99
			// com/zving/framework/utility/BufferedRandomAccessFile:readInt ()I
			// 161: putfield 56
			// com/zving/framework/utility/FileCachedMapx$Key:DataLength I
			// 164: aload 4
			// 166: aload_2
			// 167: invokevirtual 99
			// com/zving/framework/utility/BufferedRandomAccessFile:readInt ()I
			// 170: putfield 43
			// com/zving/framework/utility/FileCachedMapx$Key:DataAddress I
			// 173: aload_2
			// 174: invokevirtual 95
			// com/zving/framework/utility/BufferedRandomAccessFile:readShort
			// ()S
			// 177: istore 6
			// 179: iload 5
			// 181: iconst_1
			// 182: if_icmpne +63 -> 245
			// 185: iload 6
			// 187: newarray byte
			// 189: astore 7
			// 191: aload_2
			// 192: aload 7
			// 194: invokevirtual 59
			// com/zving/framework/utility/BufferedRandomAccessFile:read ([B)I
			// 197: pop
			// 198: aload 4
			// 200: new 103 java/lang/String
			// 203: dup
			// 204: aload 7
			// 206: invokespecial 105 java/lang/String:<init> ([B)V
			// 209: putfield 25
			// com/zving/framework/utility/FileCachedMapx$Key:KeyString
			// Ljava/lang/String;
			// 212: aload 4
			// 214: iload 6
			// 216: bipush 22
			// 218: iadd
			// 219: i2s
			// 220: putfield 47
			// com/zving/framework/utility/FileCachedMapx$Key:KeyLength S
			// 223: aload 4
			// 225: iload_3
			// 226: putfield 108
			// com/zving/framework/utility/FileCachedMapx$Key:keyAddress I
			// 229: new 1 com/zving/framework/utility/FileCachedMapx$Entry
			// 232: dup
			// 233: aload_0
			// 234: getfield 15
			// com/zving/framework/utility/FileCachedMapx$Entry:fcm
			// Lcom/zving/framework/utility/FileCachedMapx;
			// 237: aload 4
			// 239: invokespecial 111
			// com/zving/framework/utility/FileCachedMapx$Entry:<init>
			// (Lcom/zving/framework/utility/FileCachedMapx;Lcom/zving/framework/utility/FileCachedMapx$Key;)V
			// 242: aload_1
			// 243: monitorexit
			// 244: areturn
			// 245: iload_3
			// 246: iload 6
			// 248: bipush 22
			// 250: iadd
			// 251: iadd
			// 252: istore_3
			// 253: aload_2
			// 254: invokevirtual 113
			// com/zving/framework/utility/BufferedRandomAccessFile:length ()J
			// 257: iload_3
			// 258: i2l
			// 259: lcmp
			// 260: ifgt -126 -> 134
			// 263: goto +8 -> 271
			// 266: astore_2
			// 267: aload_2
			// 268: invokevirtual 117 java/lang/Exception:printStackTrace ()V
			// 271: aload_1
			// 272: monitorexit
			// 273: goto +6 -> 279
			// 276: aload_1
			// 277: monitorexit
			// 278: athrow
			// 279: aconst_null
			// 280: areturn
			//
			// Exception table:
			// from to target type
			// 7 87 266 java/lang/Exception
			// 91 242 266 java/lang/Exception
			// 245 263 266 java/lang/Exception
			// 7 89 276 finally
			// 91 244 276 finally
			// 245 273 276 finally
			// 276 278 276 finally
		}
	}

	public static class Key {
		short KeyFileIndex;
		short KeyLength;
		int keyAddress;
		String KeyString;
		short DataFileIndex;
		int DataLength;
		int DataAddress;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.utility.FileCachedMapx JD-Core Version: 0.5.3
 */