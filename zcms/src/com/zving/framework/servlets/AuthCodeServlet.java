package com.zving.framework.servlets;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.zving.framework.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 20060808L;
	private static final String CONTENT_TYPE = "image/jpeg";
	private static final int DEFAULT_WIDTH = 50;
	private static final int DEFAULT_HEIGHT = 14;
	private static final int DEFAULT_LENGTH = 4;
	public static final String DEFAULT_CODETYPE = "2";
	private String CodeType;
	private String AuthKey;
	private int Width;
	private int Height;
	private int Length;
	private OutputStream out;
	private Random rand = new Random(System.currentTimeMillis());
	private String seed;
	private BufferedImage image;
	static char[] arr = "23456789qwertyuipasdfghjkzxcvbnm".toCharArray();

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.CodeType = request.getParameter("CodeType");
			this.AuthKey = request.getParameter("AuthKEY");
			String tWidth = request.getParameter("Width");
			String tHeight = request.getParameter("Height");
			String tLength = request.getParameter("Length");
			if ((this.CodeType == null) || (this.CodeType.equals(""))) {
				this.CodeType = "2";
			}
			if ((this.AuthKey == null) || (this.AuthKey.equals(""))) {
				this.AuthKey = "_ZVING_AUTHKEY";
			}
			if ((tWidth == null) || (tWidth.equals(""))) {
				this.Width = 50;
			}
			if ((tHeight == null) || (tHeight.equals(""))) {
				this.Height = 14;
			}
			if ((tLength == null) || (tLength.equals("")))
				this.Length = 4;
			try {
				this.Width = Integer.parseInt(tWidth);
			} catch (Exception ex) {
				this.Width = 50;
			}
			try {
				this.Height = Integer.parseInt(tHeight);
			} catch (Exception ex) {
				this.Height = 14;
			}
			try {
				this.Length = Integer.parseInt(tLength);
			} catch (Exception ex) {
				this.Length = 4;
			}
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0L);

			this.out = response.getOutputStream();
			this.seed = getSeed();
			Object o = request.getSession().getAttribute("_ZVING_USER");
			if (o != null) {
				User.setCurrent((User) o);
				User.setValue(this.AuthKey, this.seed);
			} else {
				User u = new User();
				User.setCurrent(u);
				User.setValue(this.AuthKey, this.seed);
				request.getSession().setAttribute("_ZVING_USER", u);
			}

			if (this.CodeType.equals("1"))
				code1(request, response);
			else if (this.CodeType.equals("2"))
				code2(request, response);
			else if (this.CodeType.equals("3"))
				code3(request, response);
			try {
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(this.out);
				JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(this.image);
				param.setQuality(1.0F, false);
				encoder.setJPEGEncodeParam(param);
				encoder.encode(this.image);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			this.out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private BufferedImage code1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.image = new BufferedImage(this.Width, this.Height, 1);
		Graphics g = this.image.getGraphics();
		g.setColor(new Color(245, 245, 245));
		g.fillRect(0, 0, this.Width, this.Height);
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial", 0, 12));
		g.drawString(this.seed, 3, 11);
		g.dispose();
		return this.image;
	}

	private BufferedImage code2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.image = new BufferedImage(this.Width, this.Height, 1);
		Graphics g = this.image.getGraphics();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, this.Width, this.Height);
		g.setFont(new Font("Arial", 0, 12));

		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 80; ++i)
			;
		for (int i = 0; i < this.Length; ++i) {
			String c = this.seed.substring(i, i + 1);
			g.setColor(new Color(20 + this.rand.nextInt(110), 20 + this.rand.nextInt(110),
					20 + this.rand.nextInt(110)));
			g.drawString(c, 11 * i + 3, 11);
		}
		g.dispose();
		return this.image;
	}

	private BufferedImage code3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.image = new BufferedImage(this.Width, this.Height, 1);
		Graphics2D g = (Graphics2D) this.image.getGraphics();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, this.Width, this.Height);
		g.setFont(new Font("Times New Roman", 0, 12));

		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; ++i) {
			int x = this.rand.nextInt(this.Width);
			int y = this.rand.nextInt(this.Height);
			int xl = this.rand.nextInt(12);
			int yl = this.rand.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		AffineTransform fontAT = new AffineTransform();
		for (int i = 0; i < this.Length; ++i) {
			String c = this.seed.substring(i, i + 1);
			g.setColor(new Color(20 + this.rand.nextInt(110), 20 + this.rand.nextInt(110),
					20 + this.rand.nextInt(110)));
			fontAT.shear(this.rand.nextFloat() * 0.6D - 0.3D, 0.0D);
			FontRenderContext frc = g.getFontRenderContext();
			Font theDerivedFont = g.getFont().deriveFont(fontAT);
			TextLayout tstring2 = new TextLayout(c, theDerivedFont, frc);
			tstring2.draw(g, 7 * i + 2, 11.0F);
		}
		g.dispose();
		return this.image;
	}

	private String getSeed() {
		StringBuffer sb = new StringBuffer(this.Length);
		for (int i = 0; i < this.Length; ++i) {
			sb.append(arr[this.rand.nextInt(arr.length)]);
		}

		return sb.toString();
	}

	private Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + this.rand.nextInt(bc - fc);
		int g = fc + this.rand.nextInt(bc - fc);
		int b = fc + this.rand.nextInt(bc - fc);

		return new Color(r, g, b);
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.servlets.AuthCodeServlet JD-Core Version: 0.5.3
 */