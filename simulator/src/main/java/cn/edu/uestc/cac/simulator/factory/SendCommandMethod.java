package cn.edu.uestc.cac.simulator.factory;

/**
 * 发送命令方法的接口
 *
 * @author maomao
 * @date 2020-12-29
 */
public interface SendCommandMethod {
    /**
     * 发送命令
     *
     * @param commandStr 需要发送的命令
     */
    void sendCommand(String commandStr);
}
