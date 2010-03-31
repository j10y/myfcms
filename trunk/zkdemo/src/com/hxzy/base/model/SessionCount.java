package com.hxzy.base.model;


/*
 * getVisitCount（） 得到用户访问总次数
 * getLogonUser() 得到应用已登陆会员用户总数
 * getLogonUserList（） 得到登陆会员用户列表
 */


import java.util.ArrayList;
import java.util.List;

import com.hxzy.common.user.model.User;


public class SessionCount {
     

    //登陆用户列表
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
     * 获得在线用户列表
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
     * 得到在线用户总数
     *
     */
    public static int getUserCount(){
        return logonUserList.size();
    }

    /**
     * 增加登陆会员
     */
    public void addLogonUser(User user) throws Exception {
        //将登陆会员加入到已登陆会员列表
    	if (existUserVO(user.getId().toString()) == -1) {
    		logonUserList.add(user);
    	}
    }

    /**
     * 会员退出登陆
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
     * 用户列表是否存在该用户
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
