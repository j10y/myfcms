package com.hxzy.base.web.intercepter;

/**
 * @author xiacc
 *
 * ��������Ȩ�ӿ�
 */
public interface Authorizable {
    
  public boolean needAuthorization();

  public String getFunctionCode();
  
}