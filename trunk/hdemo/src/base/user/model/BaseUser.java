package base.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * <p>
 * ����: Person
 * </p>
 * <p>
 * ����: ��Ա��
 * </p>
 */
@Entity
@Table(name = "base_user")
public class BaseUser implements Serializable {

	/**
	 * ����: ��ԱID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * ����: ��Ա����
	 */
	private String code;

	/**
	 * ����: ��Ա����
	 */
	private String name;

	/**
	 * ����: ��Ա����
	 */
	private String password;

	/**
	 * ����: ��Ա����
	 */
	private Long userFlag;

	/**
	 * ����: �û����ߴ���
	 */
	private Long loginFrequency;

	/**
	 * ����: �û��������ʱ��
	 */
	private Date lastTime;

	/**
	 * ����: �Ƿ���Ч�û� 0����Ч��1����Ч ����״̬
	 */
	private Long isLocked;

	/**
	 * ����: ����ʱ��
	 */
	private Date lockedTime;
	
	/**
	 * ����: �û�ӵ�еĽ�ɫ
	 */
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)   
	@JoinTable(name="user_role",joinColumns={@JoinColumn(name="user_id")},
			inverseJoinColumns={@JoinColumn(name="role_id")})
	private Set<Role> roles;
	

	public BaseUser() {
	}

	/**
	 * ����: ���� code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * ����: ���� code 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * ����: ���� id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * ����: ���� id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * ����: ���� isLocked
	 */
	public Long getIsLocked() {
		return isLocked;
	}

	/**
	 * ����: ���� isLocked 
	 */
	public void setIsLocked(Long isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * ����: ���� lastTime
	 */
	public Date getLastTime() {
		return lastTime;
	}

	/**
	 * ����: ���� lastTime 
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	/**
	 * ����: ���� lockedTime
	 */
	public Date getLockedTime() {
		return lockedTime;
	}

	/**
	 * ����: ���� lockedTime 
	 */
	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}

	/**
	 * ����: ���� loginFrequency
	 */
	public Long getLoginFrequency() {
		return loginFrequency;
	}

	/**
	 * ����: ���� loginFrequency 
	 */
	public void setLoginFrequency(Long loginFrequency) {
		this.loginFrequency = loginFrequency;
	}

	/**
	 * ����: ���� name
	 */
	public String getName() {
		return name;
	}

	/**
	 * ����: ���� name 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ����: ���� password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * ����: ���� password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ����: ���� userFlag
	 */
	public Long getUserFlag() {
		return userFlag;
	}

	/**
	 * ����: ���� userFlag 
	 */
	public void setUserFlag(Long userFlag) {
		this.userFlag = userFlag;
	}

	/**
	 * ���� roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * ���� roles
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}