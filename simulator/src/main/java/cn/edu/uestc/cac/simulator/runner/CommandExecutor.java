package cn.edu.uestc.cac.simulator.runner;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.uestc.cac.simulator.common.Command;
import cn.edu.uestc.cac.simulator.common.CommandTypeEnum;
import cn.edu.uestc.cac.simulator.utils.RandomCommandGenerator;
import cn.edu.uestc.cac.utils.Stopper;

/**
 * @author wang
 */
public class CommandExecutor implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandExecutor.class);

    private Command command;

    public CommandExecutor(Command command) {
        if (command == null) throw new NullPointerException();
        this.command = command;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (Stopper.isRunning()) {
            try {
                // TODO 完善细节
                RandomCommandGenerator.generate(command);
                boolean randomExecute = true;
                int randomNum = random.nextInt(10);
                if (command.getCommandType() == CommandTypeEnum.DISK) {
                    // 80%的概率不执行
                    if (randomNum >= 2) {
                        randomExecute = false;
                    }
                } else if (command.getCommandType() == CommandTypeEnum.MEM_LEAK) {
                    // 50%的概率不执行
                    if (randomNum >= 5) {
                        randomExecute = false;
                    }
                }
                if (randomExecute) {
                    command.execute();
                }
                Thread.sleep((command.getTimeout() + 5) * 1000L);
            } catch (Exception e) {
                logger.error("执行命令时发生错误", e);
            }
        }
    }
}
