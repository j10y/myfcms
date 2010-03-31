package com.hxzy.base.web.intercepter;

/**
 * @author xiacc
 *
 * ÃèÊö£ºÊÚÈ¨½Ó¿Ú
 */
public interface Authorizable {
    
  public boolean needAuthorization();

  public String getFunctionCode();
  
}