package cn.edu.uestc.cac.simulator.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.uestc.cac.simulator.common.Command;
import cn.edu.uestc.cac.simulator.utils.RandomCommandGenerator;
import cn.edu.uestc.cac.utils.Stopper;

/**
 * @author wang
 */
public class CommandExecutor implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandExecutor.class);

    private Command command;

    public CommandExecutor(Command command) {
        this.command = command;
    }

    @Override
    public void run() {
        while (Stopper.isRunning()) {
            // TODO 完善细节
            RandomCommandGenerator.generate(command);
            boolean execResult = command.execute();
            try {
                Thread.sleep(command.getTimeout() * 1000L);
            } catch (InterruptedException e) {
                logger.info("睡眠出现错误");
                e.printStackTrace();
            }
        }
    }
}
