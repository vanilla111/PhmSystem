package cn.edu.uestc.cac.simulator.common;

/**
 * @author wang
 */
public abstract class Command implements CommandInterface {

    protected String command;

    protected int timeout;

    protected CommandTypeEnum commandType;

    public Command(CommandTypeEnum commandType) {
        this.commandType = commandType;
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

    public void setCommandType(CommandTypeEnum commandType) {
        this.commandType = commandType;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
