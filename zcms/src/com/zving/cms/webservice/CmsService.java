package com.zving.cms.webservice;

public abstract interface CmsService
{
  public abstract long addArticle(long paramLong, String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract boolean editArticle(long paramLong, String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract boolean deleteArticle(long paramLong);

  public abstract boolean publishArticle(long paramLong);

  public abstract long addCatalog(long paramLong, String paramString1, int paramInt, String paramString2);

  public abstract boolean editCatalog(long paramLong, String paramString1, String paramString2);

  public abstract boolean deleteCatalog(long paramLong);

  public abstract boolean publishCatalog(long paramLong);

  public abstract long addUser(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

  public abstract boolean editUser(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

  public abstract boolean deleteUser(String paramString);

  public abstract long addMember(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract boolean editMember(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract boolean deleteMember(String paramString);
}

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.webservice.CmsService
 * JD-Core Version:    0.5.3
 */