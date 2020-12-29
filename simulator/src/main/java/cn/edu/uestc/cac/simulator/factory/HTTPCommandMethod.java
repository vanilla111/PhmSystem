package cn.edu.uestc.cac.simulator.factory;

import cn.edu.uestc.cac.simulator.constants.CommandConstants;
import cn.edu.uestc.cac.simulator.constants.SystemConstants;
import cn.edu.uestc.cac.simulator.entity.Command;
import cn.edu.uestc.cac.simulator.utils.CommandUtils;
import cn.edu.uestc.cac.simulator.utils.JsonUtil;

/**
 * HTTP 远程执行命令
 *
 * @author maomao
 * @date 2020-12-29
 */
public class HTTPCommandMethod implements SendCommandMethod {
    @Override
    public void sendCommand(String commandStr) {
        StringBuilder sb = new StringBuilder();
        sb.append(CommandConstants.CURL)
                .append(CommandConstants.DOUBLE_QUOTES)
                .append(SystemConstants.HHTP_PREFIX)
                .append(commandStr.replace(" ", CommandConstants.SPACE_REPLACER))
                .append(CommandConstants.DOUBLE_QUOTES);

        String result = CommandUtils.httpExecute(sb.toString());
        System.out.println("result = " + result);

        // 组装数据并存入数据库
        Command command = JsonUtil.toBean(result, Command.class);
        if (command != null) {
            command.setCommand(commandStr);
            command.insert();
        }
    }
}
