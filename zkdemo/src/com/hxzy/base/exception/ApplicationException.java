
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