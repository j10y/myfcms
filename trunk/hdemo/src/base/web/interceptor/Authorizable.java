package base.web.interceptor;


public interface Authorizable {
    
  public boolean needAuthorization();

  public String getFunctionCode();

}