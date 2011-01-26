package com.htsoft.core.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;
import nl.captcha.Captcha.Builder;
import nl.captcha.servlet.CaptchaServletUtil;

public class SimpleCaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_HEIGHT = "height";
	private static final String PARAM_WIDTH = "width";
	protected int _width = 200;
	protected int _height = 50;

	public void init() throws ServletException {
		if (getInitParameter("height") != null) {
			this._height = Integer.valueOf(getInitParameter("height")).intValue();
		}

		if (getInitParameter("width") != null)
			this._width = Integer.valueOf(getInitParameter("width")).intValue();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		Captcha captcha = new Captcha.Builder(this._width, this._height).addText().addBackground()
				.addNoise().build();

		CaptchaServletUtil.writeImage(resp, captcha.getImage());
		req.getSession().setAttribute("simpleCaptcha", captcha);
	}
}
