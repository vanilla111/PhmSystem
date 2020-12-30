package cn.edu.uestc.cac.simulator.common;

import cn.edu.uestc.cac.simulator.constants.CommandConstants;

/**
 * @author wang
 */
public class LocalCommand extends Command {

    public LocalCommand(CommandTypeEnum commandType) {
        super(commandType);
        reset();
    }

    @Override
    public boolean execute() {
        // 命令模式，真正的执行者会在这里执行命令
        return false;
    }

    @Override
    public void reset() {
        this.command = CommandConstants.BLADE;
    }
}
