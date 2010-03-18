/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2005-11-9</p>
 * <p>���£�</p>
 */
package base.exception;

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