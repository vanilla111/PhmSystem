package cn.edu.uestc.cac.simulator.factory;

import ch.ethz.ssh2.Connection;
import cn.edu.uestc.cac.simulator.constants.CommandConstants;
import cn.edu.uestc.cac.simulator.constants.SystemConstants;
import cn.edu.uestc.cac.simulator.entity.Command;
import cn.edu.uestc.cac.simulator.utils.CommandUtils;
import cn.edu.uestc.cac.simulator.utils.JsonUtil;

/**
 * SSH 远程执行命令
 *
 * @author maomao
 * @date 2020-12-29
 */
public class SSHCommandMethod implements SendCommandMethod{
    @Override
    public void sendCommand(String commandStr) {
        // 获取连接并执行命令
        Connection connection = CommandUtils.sshLogin(SystemConstants.SSH_IP,
                SystemConstants.SSH_USER, SystemConstants.SSH_PASSWORD);
        commandStr = CommandConstants.BLADE + commandStr;
        String result = CommandUtils.sshExecute(connection, commandStr);

        // 组装数据并存入数据库
        Command command = JsonUtil.toBean(result, Command.class);
        if (command != null) {
            command.setCommand(commandStr);
            command.insert();
        }
    }
}
