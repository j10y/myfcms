/**
 * <p>��Ŀ���ƣ��������й���ϵͳ</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2005-11-9</p>
 * <p>���£�</p>
 */
package base.exception;


/**
 * <p>
 * ����: InvalidProcessRequestException
 * </p>
 * <p>
 * ����: ��Ч�����̴��������쳣��
 * </p>
 */
public class InvalidProcessRequestException extends ApplicationException {

  public InvalidProcessRequestException(String msg) {
    super(msg);
  }

  public InvalidProcessRequestException(String msg, Throwable ex) {
    super(msg, ex);
  }
  
  public InvalidProcessRequestException() {
    super("exception.msg.invalidProcessRequestException");
  }
}
