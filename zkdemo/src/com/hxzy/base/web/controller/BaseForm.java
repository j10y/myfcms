/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2005-10-18</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.controller;

import java.io.Serializable;

/**
 * <p>
 * 类名: BaseForm
 * </p>
 * <p>
 * 描述: 窗口基类，所有窗口类均从此类继承
 * </p>
 */
public class BaseForm implements Serializable {

  /**
   * 描述: 返回页面url
   */
  private String returnUrl;

  
  /**
   * 描述: 返回 returnUrl
   */
  public String getReturnUrl() {
    return returnUrl;
  }
  /**
   * 描述: 设置 returnUrl 
   */
  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }
}

