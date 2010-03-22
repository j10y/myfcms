package com.zving.framework.utility;

import java.awt.Image;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

public class GIFEncoder extends ImageEncoder {
	private boolean interlace = false;
	int width;
	int height;
	int[][] rgbPixels;
	IntHashtable colorHash;
	int Width;
	int Height;
	boolean Interlace;
	int curx;
	int cury;
	int CountDown;
	int Pass = 0;
	static final int EOF = -1;
	static final int BITS = 12;
	static final int HSIZE = 5003;
	int n_bits;
	int maxbits = 12;
	int maxcode;
	int maxmaxcode = 4096;

	int[] htab = new int[5003];

	int[] codetab = new int[5003];

	int hsize = 5003;

	int free_ent = 0;

	boolean clear_flg = false;
	int g_init_bits;
	int ClearCode;
	int EOFCode;
	int cur_accum = 0;

	int cur_bits = 0;

	int[] masks = { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767,
			65535 };
	int a_count;
	byte[] accum = new byte[256];

	public GIFEncoder(Image img, OutputStream out) throws IOException {
		super(img, out);
	}

	public GIFEncoder(Image img, OutputStream out, boolean interlace) throws IOException {
		super(img, out);
		this.interlace = interlace;
	}

	public GIFEncoder(ImageProducer prod, OutputStream out) throws IOException {
		super(prod, out);
	}

	public GIFEncoder(ImageProducer prod, OutputStream out, boolean interlace) throws IOException {
		super(prod, out);
		this.interlace = interlace;
	}

	void encodeStart(int width, int height) throws IOException {
		this.width = width;
		this.height = height;
		this.rgbPixels = new int[height][width];
	}

	void encodePixels(int x, int y, int w, int h, int[] rgbPixels, int off, int scansize)
			throws IOException {
		for (int row = 0; row < h; ++row)
			System.arraycopy(rgbPixels, row * scansize + off, this.rgbPixels[(y + row)], x, w);
	}

	void encodeDone() throws IOException {
		int transparentIndex = -1;
		int transparentRgb = -1;

		this.colorHash = new IntHashtable();
		int index = 0;
		for (int row = 0; row < this.height; ++row)
			for (int col = 0; col < this.width; ++col) {
				int rgb = this.rgbPixels[row][col];
				boolean isTransparent = rgb >>> 24 < 128;
				if (isTransparent) {
					if (transparentIndex < 0) {
						transparentIndex = index;
						transparentRgb = rgb;
					} else if (rgb != transparentRgb) {
						this.rgbPixels[row][col] = (rgb = transparentRgb);
					}
				}
				GifEncoderHashitem item = (GifEncoderHashitem) this.colorHash.get(rgb);
				if (item == null) {
					if (index >= 256) {
						throw new IOException("too many colors for a GIF");
					}
					item = new GifEncoderHashitem(rgb, 1, index, isTransparent);
					++index;
					this.colorHash.put(rgb, item);
				} else {
					item.count += 1;
				}
			}
		int logColors;
		if (index <= 2)
			logColors = 1;
		else if (index <= 4)
			logColors = 2;
		else if (index <= 16)
			logColors = 4;
		else {
			logColors = 8;
		}

		int mapSize = 1 << logColors;
		byte[] reds = new byte[mapSize];
		byte[] grns = new byte[mapSize];
		byte[] blus = new byte[mapSize];
		for (Enumeration e = this.colorHash.elements(); e.hasMoreElements();) {
			GifEncoderHashitem item = (GifEncoderHashitem) e.nextElement();
			reds[item.index] = (byte) (item.rgb >> 16 & 0xFF);
			grns[item.index] = (byte) (item.rgb >> 8 & 0xFF);
			blus[item.index] = (byte) (item.rgb & 0xFF);
		}

		GIFEncode(this.out, this.width, this.height, this.interlace, (byte) 0, transparentIndex,
				logColors, reds, grns, blus);
	}

	byte GetPixel(int x, int y) throws IOException {
		GifEncoderHashitem item = (GifEncoderHashitem) this.colorHash.get(this.rgbPixels[y][x]);
		if (item == null) {
			throw new IOException("color not found");
		}
		return (byte) item.index;
	}

	static void writeString(OutputStream out, String str) throws IOException {
		byte[] buf = str.getBytes();
		out.write(buf);
	}

	void GIFEncode(OutputStream outs, int Width, int Height, boolean Interlace, byte Background,
			int Transparent, int BitsPerPixel, byte[] Red, byte[] Green, byte[] Blue)
			throws IOException {
		this.Width = Width;
		this.Height = Height;
		this.Interlace = Interlace;
		int ColorMapSize = 1 << BitsPerPixel;
		int TopOfs;
		int LeftOfs = TopOfs = 0;

		this.CountDown = (Width * Height);

		this.Pass = 0;
		int InitCodeSize;
		if (BitsPerPixel <= 1)
			InitCodeSize = 2;
		else {
			InitCodeSize = BitsPerPixel;
		}

		this.curx = 0;
		this.cury = 0;

		writeString(outs, "GIF89a");

		Putword(Width, outs);
		Putword(Height, outs);

		byte B = -128;

		B = (byte) (B | 0x70);

		B = (byte) (B | (byte) (BitsPerPixel - 1));

		Putbyte(B, outs);

		Putbyte(Background, outs);

		Putbyte((byte) 0, outs);

		for (int i = 0; i < ColorMapSize; ++i) {
			Putbyte(Red[i], outs);
			Putbyte(Green[i], outs);
			Putbyte(Blue[i], outs);
		}

		if (Transparent != -1) {
			Putbyte((byte) 33, outs);
			Putbyte((byte) -7, outs);
			Putbyte((byte) 4, outs);
			Putbyte((byte) 1, outs);
			Putbyte((byte) 0, outs);
			Putbyte((byte) 0, outs);
			Putbyte((byte) Transparent, outs);
			Putbyte((byte) 0, outs);
		}

		Putbyte((byte) 44, outs);

		Putword(LeftOfs, outs);
		Putword(TopOfs, outs);
		Putword(Width, outs);
		Putword(Height, outs);

		if (Interlace)
			Putbyte((byte) 64, outs);
		else {
			Putbyte((byte) 0, outs);
		}

		Putbyte((byte) InitCodeSize, outs);

		compress(InitCodeSize + 1, outs);

		Putbyte((byte) 0, outs);

		Putbyte((byte) 59, outs);
	}

	void BumpPixel() {
		this.curx += 1;

		if (this.curx == this.Width) {
			this.curx = 0;

			if (!(this.Interlace))
				this.cury += 1;
			else
				switch (this.Pass) {
				case 0:
					this.cury += 8;
					if (this.cury < this.Height)
						return;
					this.Pass += 1;
					this.cury = 4;

					break;
				case 1:
					this.cury += 8;
					if (this.cury < this.Height)
						return;
					this.Pass += 1;
					this.cury = 2;

					break;
				case 2:
					this.cury += 4;
					if (this.cury < this.Height)
						return;
					this.Pass += 1;
					this.cury = 1;

					break;
				case 3:
					this.cury += 2;
				}
		}
	}

	int GIFNextPixel() throws IOException {
		if (this.CountDown == 0) {
			return -1;
		}

		this.CountDown -= 1;

		byte r = GetPixel(this.curx, this.cury);

		BumpPixel();

		return (r & 0xFF);
	}

	void Putword(int w, OutputStream outs) throws IOException {
		Putbyte((byte) (w & 0xFF), outs);
		Putbyte((byte) (w >> 8 & 0xFF), outs);
	}

	void Putbyte(byte b, OutputStream outs) throws IOException {
		outs.write(b);
	}

	final int MAXCODE(int n_bits) {
		return ((1 << n_bits) - 1);
	}

	void compress(int init_bits, OutputStream outs) throws IOException {
		g_init_bits = init_bits;
		clear_flg = false;
		n_bits = g_init_bits;
		maxcode = MAXCODE(n_bits);
		ClearCode = 1 << init_bits - 1;
		EOFCode = ClearCode + 1;
		free_ent = ClearCode + 2;
		char_init();
		int ent = GIFNextPixel();
		int hshift = 0;
		for (int fcode = hsize; fcode < 65536; fcode *= 2)
			hshift++;

		hshift = 8 - hshift;
		int hsize_reg = hsize;
		cl_hash(hsize_reg);
		output(ClearCode, outs);
		int c;
		label0: while ((c = GIFNextPixel()) != -1) {
			int fcode = (c << maxbits) + ent;
			int i = c << hshift ^ ent;
			if (htab[i] == fcode) {
				ent = codetab[i];
				continue;
			}
			if (htab[i] >= 0) {
				int disp = hsize_reg - i;
				if (i == 0)
					disp = 1;
				do {
					if ((i -= disp) < 0)
						i += hsize_reg;
					if (htab[i] == fcode) {
						ent = codetab[i];
						continue label0;
					}
				} while (htab[i] >= 0);
			}
			output(ent, outs);
			ent = c;
			if (free_ent < maxmaxcode) {
				codetab[i] = free_ent++;
				htab[i] = fcode;
			} else {
				cl_block(outs);
			}
		}
		output(ent, outs);
		output(EOFCode, outs);
	}

	void output(int code, OutputStream outs) throws IOException {
		this.cur_accum &= this.masks[this.cur_bits];

		if (this.cur_bits > 0)
			this.cur_accum |= code << this.cur_bits;
		else {
			this.cur_accum = code;
		}

		this.cur_bits += this.n_bits;

		while (this.cur_bits >= 8) {
			char_out((byte) (this.cur_accum & 0xFF), outs);
			this.cur_accum >>= 8;
			this.cur_bits -= 8;
		}

		if ((this.free_ent > this.maxcode) || (this.clear_flg)) {
			if (this.clear_flg) {
				this.maxcode = MAXCODE(this.n_bits = this.g_init_bits);
				this.clear_flg = false;
			} else {
				this.n_bits += 1;
				if (this.n_bits == this.maxbits)
					this.maxcode = this.maxmaxcode;
				else {
					this.maxcode = MAXCODE(this.n_bits);
				}
			}
		}

		if (code != this.EOFCode)
			return;
		while (this.cur_bits > 0) {
			char_out((byte) (this.cur_accum & 0xFF), outs);
			this.cur_accum >>= 8;
			this.cur_bits -= 8;
		}

		flush_char(outs);
	}

	void cl_block(OutputStream outs) throws IOException {
		cl_hash(this.hsize);
		this.free_ent = (this.ClearCode + 2);
		this.clear_flg = true;

		output(this.ClearCode, outs);
	}

	void cl_hash(int hsize) {
		for (int i = 0; i < hsize; ++i)
			this.htab[i] = -1;
	}

	void char_init() {
		this.a_count = 0;
	}

	void char_out(byte c, OutputStream outs) throws IOException {
		this.accum[(this.a_count++)] = c;
		if (this.a_count >= 254)
			flush_char(outs);
	}

	void flush_char(OutputStream outs) throws IOException {
		if (this.a_count > 0) {
			outs.write(this.a_count);
			outs.write(this.accum, 0, this.a_count);
			this.a_count = 0;
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.utility.GIFEncoder JD-Core Version: 0.5.3
 */