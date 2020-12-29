package cn.edu.uestc.cac.simulator.factory;

import cn.edu.uestc.cac.simulator.constants.CommandConstants;

/**
 * 命令的简单工厂模式
 *
 * @author maomao
 * @date 2020-12-29
 */
public class CommandFactory {
    /**
     * 使用简单工厂模式返回不同的发送命令方法类
     *
     * @param sendCommandMethod 发送命令方式类对应的字符串
     * @return 发送命令方式类
     */
    public static SendCommandMethod getSendCommandMethod(String sendCommandMethod) {
        switch (sendCommandMethod) {
            case CommandConstants.LOCAL_SEND_METHOD:
                return new LocalCommandMethod();
            case CommandConstants.SSH_SEND_METHOD:
                return new SSHCommandMethod();
            case CommandConstants.HTTP_SEND_METHOD:
                return new HTTPCommandMethod();
            default:
                return null;
        }
    }
}
