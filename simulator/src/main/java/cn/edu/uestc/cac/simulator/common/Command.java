package cn.edu.uestc.cac.simulator.common;

import cn.edu.uestc.cac.simulator.config.SimulatorConfig;
import cn.edu.uestc.cac.simulator.constants.CommandConstants;

/**
 * @author wang
 */
public abstract class Command implements CommandInterface {

    protected String command;

    protected int timeout;

    protected CommandTypeEnum commandType;

    protected SimulatorConfig config;

    public Command(CommandTypeEnum commandType, SimulatorConfig config) {
        this.commandType = commandType;
        this.config = config;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public CommandTypeEnum getCommandType() {
        return commandType;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public SimulatorConfig getConfig() {
        return config;
    }

    @Override
    public boolean execute() {
        if (this.command == null) {
            throw new IllegalArgumentException("无效的命令: NULL");
        }
        return true;
    }

    @Override
    public void reset() {
        this.command = CommandConstants.EMPTY;
    }
}
