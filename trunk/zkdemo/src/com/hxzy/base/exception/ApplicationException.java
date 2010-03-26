/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2005-11-9</p>
 * <p>更新：</p>
 */
package com.hxzy.base.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * <p>
 * 类名: ApplicationException
 * </p>
 * <p>
 * 描述: 应用程序异常类，所有的应用程序异常继承此类
 * </p>
 */
public class ApplicationException extends NestedRuntimeException {

  public ApplicationException(String msg) {
    super(msg);
  }

  public ApplicationException(String msg, Throwable ex) {
    super(msg, ex);
  }
}