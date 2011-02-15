package com.htsoft.core.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.servlet.CaptchaServletUtil;

public class SimpleCaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_HEIGHT = "height";
	private static final String PARAM_WIDTH = "width";
	protected int _width;
	protected int _height;

	public SimpleCaptchaServlet() {
		_width = 200;
		_height = 50;
	}

	public void init() throws ServletException {
		if (getInitParameter("height") != null)
			_height = Integer.valueOf(getInitParameter("height")).intValue();
		if (getInitParameter("width") != null)
			_width = Integer.valueOf(getInitParameter("width")).intValue();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		Captcha captcha = (new nl.captcha.Captcha.Builder(_width, _height)).addText()
				.addBackground().addNoise().build();
		CaptchaServletUtil.writeImage(resp, captcha.getImage());
		req.getSession().setAttribute("simpleCaptcha", captcha);
	}

}
