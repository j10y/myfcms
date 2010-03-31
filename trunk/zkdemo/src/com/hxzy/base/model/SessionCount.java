package com.hxzy.base.model;


/*
 * getVisitCount���� �õ��û������ܴ���
 * getLogonUser() �õ�Ӧ���ѵ�½��Ա�û�����
 * getLogonUserList���� �õ���½��Ա�û��б�
 */


import java.util.ArrayList;
import java.util.List;

import com.hxzy.common.user.model.User;


public class SessionCount {
     

    //��½�û��б�
    private static List logonUserList = new ArrayList();
    
    private static SessionCount instance = null;
    
    private SessionCount() {
    }
    
    public static synchronized SessionCount getInstance() {
        if (instance == null) {
            instance = new SessionCount();
        }
        return instance;
    }

    /**
     * ��������û��б�
     * @return Returns the logonUserList.
     */
    public static List getLogonUserList() {
        List temp = new ArrayList();
        temp.addAll(logonUserList);
        return temp;
    }


    /**
     * @param logonUserList
     *            The logonUserList to set.
     */
    private void setLogonUserList(List logonUserList) {
        SessionCount.logonUserList = logonUserList;
    }
    
    /**
     * �õ������û�����
     *
     */
    public static int getUserCount(){
        return logonUserList.size();
    }

    /**
     * ���ӵ�½��Ա
     */
    public void addLogonUser(User user) throws Exception {
        //����½��Ա���뵽�ѵ�½��Ա�б�
    	if (existUserVO(user.getId().toString()) == -1) {
    		logonUserList.add(user);
    	}
    }

    /**
     * ��Ա�˳���½
     */
    public void delLogonUser(String userId) {
        for (int i = 0; i < logonUserList.size(); i++) {
        	User user = (User) logonUserList.get(i);
        	if (user.getId().toString().equals(userId)) {
        		logonUserList.remove(i);
        		return;
        	}
        }
    }
    
   
    
    /**
     * �û��б��Ƿ���ڸ��û�
     * @param userinfoVO
     * @return
     */
    public static int existUserVO(String userId){
        for (int i = 0; i < logonUserList.size(); i++) {
        	User user = (User) logonUserList.get(i);
        	if (user.getId().toString().equals(userId)) {
        		return 1;
        	}
        }
        return -1;
    }
}
