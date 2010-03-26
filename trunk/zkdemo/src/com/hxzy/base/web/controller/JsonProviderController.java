/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 16, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author xiacc
 * 
 * 描述：
 */
public abstract class JsonProviderController extends AbstractController {

	private static final Log log = LogFactory.getLog(JsonProviderController.class);

	@Override  
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)   
            throws Exception {   
        JSONObject jsonObject = handleAjaxRequestInternal(request, response);   
        response.setContentType("text/Xml;charset=gbk");   
        PrintWriter out = null;   
        try {   
            out = response.getWriter();   
            out.println(jsonObject.toString());   
        }   
        catch (IOException ex1) {   
            ex1.printStackTrace();   
        }   
        finally {   
            out.close();   
        }   
        return null;   
    }   
  
    protected abstract JSONObject handleAjaxRequestInternal(HttpServletRequest httpServletRequest,   
            HttpServletResponse httpServletResponse) throws Exception;   


}
