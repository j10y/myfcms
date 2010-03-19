package com.hxzy.base.web.servlet;


import java.util.Map;

/**
 * <p>
 * ÀàÃû: CommandInterpret
 * </p>
 * <p>
 * ÃèÊö: 
 * </p>
 */
public interface CommandInterpret {
    public boolean doCommand(Map msgIn, Map msgOut);
}
