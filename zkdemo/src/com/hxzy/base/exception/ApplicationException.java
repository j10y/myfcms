
package com.hxzy.base.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * <p>
 * ����: ApplicationException
 * </p>
 * <p>
 * ����: Ӧ�ó����쳣�࣬���е�Ӧ�ó����쳣�̳д���
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