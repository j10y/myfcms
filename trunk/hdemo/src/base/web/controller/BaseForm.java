/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2005-10-18</p>
 * <p>���£�</p>
 */
package base.web.controller;

import java.io.Serializable;

/**
 * <p>
 * ����: BaseForm
 * </p>
 * <p>
 * ����: ���ڻ��࣬���д�������Ӵ���̳�
 * </p>
 */
public class BaseForm implements Serializable {

  /**
   * ����: ����ҳ��url
   */
  private String returnUrl;

  
  /**
   * ����: ���� returnUrl
   */
  public String getReturnUrl() {
    return returnUrl;
  }
  /**
   * ����: ���� returnUrl 
   */
  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }
}

