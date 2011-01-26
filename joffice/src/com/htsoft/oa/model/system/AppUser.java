 package com.htsoft.oa.model.system;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.Set;
 import org.jbpm.api.identity.User;
 import org.springframework.security.GrantedAuthority;
 import org.springframework.security.GrantedAuthorityImpl;
 import org.springframework.security.userdetails.UserDetails;
 
 public class AppUser extends BaseModel
   implements UserDetails, User
 {
   public static Long SYSTEM_USER = new Long(-1L);
 
   public static Long SUPER_USER = new Long(1L);
 
   @Expose
   protected Long userId;
 
   @Expose
   protected String username;
   protected String password;
 
   @Expose
   protected String email;
 
   @Expose
   protected Department department;
 
   @Expose
   protected String position;
 
   @Expose
   protected String phone;
 
   @Expose
   protected String mobile;
 
   @Expose
   protected String fax;
 
   @Expose
   protected String address;
 
   @Expose
   protected String zip;
 
   @Expose
   protected String photo;
 
   @Expose
   protected Date accessionTime;
 
   @Expose
   protected Short status;
 
   @Expose
   protected String education;
 
   @Expose
   protected Short title;
 
   @Expose
   protected String fullname;
 
   @Expose
   protected Short delFlag;
   private Set<AppRole> roles;
   private Set<String> rights = new HashSet();
 
   public Set<String> getRights() {
     return this.rights;
   }
 
   public String getFunctionRights()
   {
     StringBuffer sb = new StringBuffer();
 
     Iterator it = this.rights.iterator();
 
     while (it.hasNext()) {
       sb.append((String)it.next()).append(",");
     }
 
     if (this.rights.size() > 0) {
       sb.deleteCharAt(sb.length() - 1);
     }
 
     return sb.toString();
   }
 
   public void setRights(Set<String> rights) {
     this.rights = rights;
   }
 
   public AppUser()
   {
   }
 
   public AppUser(Long in_userId)
   {
     setUserId(in_userId);
   }
 
   public Long getUserId()
   {
     return this.userId;
   }
 
   public void setUserId(Long aValue)
   {
     this.userId = aValue;
   }
 
   public String getUsername()
   {
     return this.username;
   }
 
   public void setUsername(String aValue)
   {
     this.username = aValue;
   }
 
   public String getPassword()
   {
     return this.password;
   }
 
   public void setPassword(String aValue)
   {
     this.password = aValue;
   }
 
   public String getEmail()
   {
     return this.email;
   }
 
   public void setEmail(String aValue)
   {
     this.email = aValue;
   }
 
   public Department getDepartment()
   {
     return this.department;
   }
 
   public void setDepartment(Department department) {
     this.department = department;
   }
 
   public String getPosition()
   {
     return this.position;
   }
 
   public void setPosition(String aValue)
   {
     this.position = aValue;
   }
 
   public String getPhone()
   {
     return this.phone;
   }
 
   public void setPhone(String aValue)
   {
     this.phone = aValue;
   }
 
   public String getMobile()
   {
     return this.mobile;
   }
 
   public void setMobile(String aValue)
   {
     this.mobile = aValue;
   }
 
   public String getFax()
   {
     return this.fax;
   }
 
   public void setFax(String aValue)
   {
     this.fax = aValue;
   }
 
   public String getAddress()
   {
     return this.address;
   }
 
   public void setAddress(String aValue)
   {
     this.address = aValue;
   }
 
   public String getZip()
   {
     return this.zip;
   }
 
   public void setZip(String aValue)
   {
     this.zip = aValue;
   }
 
   public String getPhoto()
   {
     return this.photo;
   }
 
   public void setPhoto(String aValue)
   {
     this.photo = aValue;
   }
 
   public Date getAccessionTime()
   {
     return this.accessionTime;
   }
 
   public void setAccessionTime(Date aValue)
   {
     this.accessionTime = aValue;
   }
 
   public Short getStatus()
   {
     return this.status;
   }
 
   public void setStatus(Short aValue)
   {
     this.status = aValue;
   }
 
   public String getEducation()
   {
     return this.education;
   }
 
   public void setEducation(String aValue)
   {
     this.education = aValue;
   }
 
   public Short getTitle()
   {
     return this.title;
   }
 
   public void setTitle(Short aValue)
   {
     this.title = aValue;
   }
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String aValue)
   {
     this.fullname = aValue;
   }
 
   public Short getDelFlag()
   {
     return this.delFlag;
   }
 
   public void setDelFlag(Short delFlag) {
     this.delFlag = delFlag;
   }
 
   public String getFirstKeyColumnName()
   {
     return "userId";
   }
 
   public Set<AppRole> getRoles()
   {
     return this.roles;
   }
 
   public void setRoles(Set<AppRole> roles) {
     this.roles = roles;
   }
 
   public GrantedAuthority[] getAuthorities() {
     GrantedAuthority[] rights = (GrantedAuthority[])this.roles.toArray(new GrantedAuthority[this.roles.size() + 1]);
     rights[(rights.length - 1)] = new GrantedAuthorityImpl("ROLE_PUBLIC");
     return rights;
   }
 
   public boolean isAccountNonExpired() {
     return true;
   }
 
   public boolean isAccountNonLocked() {
     return true;
   }
 
   public boolean isCredentialsNonExpired() {
     return true;
   }
 
   public boolean isEnabled()
   {
     return this.status.shortValue() == 1;
   }
 
   public String getId()
   {
     return this.userId.toString();
   }
 
   public String getBusinessEmail() {
     return this.email;
   }
 
   public String getFamilyName()
   {
     return this.fullname;
   }
 
   public String getGivenName()
   {
     return this.fullname;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.system.AppUser
 * JD-Core Version:    0.5.4
 */