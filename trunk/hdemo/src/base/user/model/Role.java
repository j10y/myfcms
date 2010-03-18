package base.user.model;

import java.io.Serializable;
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
 * ������Role
 * ��������ɫ��
 * @author xiacc
 *
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

	/**
	 * ����: ��ɫid
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * ����: ��ɫ����
	 */
	private String roleName;
	
	/**
	 * ��������ɫ��ӵ�е�Ȩ��
	 */
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)   
	@JoinTable(name="role_privilege",joinColumns={@JoinColumn(name="role_id")},
			inverseJoinColumns={@JoinColumn(name="privilege_id")})
	private Set<Privilege> privileges;
	
	private Long publicFlag;

	/**
	 * ���캯��
	 */
	public Role() {

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * ����: ���� roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * ����: ���� roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * ���� privileges
	 */
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	/**
	 * ���� privileges
	 */
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	/**
	 * ���� publicFlag
	 */
	public Long getPublicFlag() {
		return publicFlag;
	}

	/**
	 * ���� publicFlag
	 */
	public void setPublicFlag(Long publicFlag) {
		this.publicFlag = publicFlag;
	}
}
