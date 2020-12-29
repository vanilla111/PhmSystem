package cn.edu.uestc.cac.simulator.common;

/**
 * @author wang
 */
public class SshCommand extends Command {

    public SshCommand(CommandTypeEnum commandType) {
        super(commandType);
    }

    @Override
    public boolean execute() {
        // 命令模式，真正的执行者会在这里执行命令
        System.out.println(command);
        return false;
    }
}
