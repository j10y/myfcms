package base.web.servlet;


import java.util.Map;

/**
 * <p>
 * ����: CommandInterpret
 * </p>
 * <p>
 * ����: 
 * </p>
 */
public interface CommandInterpret {
    public boolean doCommand(Map msgIn, Map msgOut);
}
