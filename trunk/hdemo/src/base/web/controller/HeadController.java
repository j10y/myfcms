/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：Reika</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-1-12</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * 类名: HeadController
 * </p>
 * <p>
 * 描述: 系统头部页面Controller
 * </p>
 */
public class HeadController extends BaseController {

  /*
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  protected ModelAndView handleRequestInternal(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    return new ModelAndView("common/head", "today", format.format(new Date()));
  }

}