package cn.edu.uestc.cac.simulator.common;

import ch.ethz.ssh2.Connection;
import cn.edu.uestc.cac.simulator.config.SimulatorConfig;
import cn.edu.uestc.cac.simulator.constants.CommandConstants;
import cn.edu.uestc.cac.simulator.utils.CommandUtils;

/**
 * @author wang
 */
public class SshCommand extends Command {

    private SimulatorConfig config;

    private Connection sshConnection;

    public SshCommand(CommandTypeEnum commandType, SimulatorConfig config) {
        super(commandType);
        reset();
        this.config = config;
        sshConnection = CommandUtils.sshLogin(config.getSshHost(), config.getUsername(), config.getPassword());
        if (sshConnection == null) {
            throw new IllegalStateException("SSH登陆失败，请检查地址、用户名和密码是否正确，网络是否通畅");
        }
    }

    @Override
    public boolean execute() {
        // 命令模式，真正的执行者会在这里执行命令
        String result = CommandUtils.sshExecute(this.sshConnection, this.command);
        return result.contains("success");
    }

    @Override
    public void reset() {
        this.command = CommandConstants.BLADE;
    }
}
