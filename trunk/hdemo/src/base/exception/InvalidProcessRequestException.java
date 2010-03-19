/**
 * <p>项目名称：调度运行管理系统</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2005-11-9</p>
 * <p>更新：</p>
 */
package com.hxzy.base.exception;


/**
 * <p>
 * 类名: InvalidProcessRequestException
 * </p>
 * <p>
 * 描述: 无效的流程处理请求异常类
 * </p>
 */
public class InvalidProcessRequestException extends ApplicationException {

  public InvalidProcessRequestException(String msg) {
    super(msg);
  }

  public InvalidProcessRequestException(String msg, Throwable ex) {
    super(msg, ex);
  }
  
  public InvalidProcessRequestException() {
    super("exception.msg.invalidProcessRequestException");
  }
}
