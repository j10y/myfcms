/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 23, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.member.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.hxzy.common.dict.model.Dict;

/**
 * @author xiacc
 *
 * ��������ҵ��Ա��
 */
public class Member {
	
	private Long id;

	//��˾����
	private String companyName;
	
	// ��������
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Dict category;
	
	//��ϵ��ʽ
	private String contacts;
	
	//���ʱ��
	private Date joinTime;
	
	//����ʱ��
	private Date endTime;
	
	

}
