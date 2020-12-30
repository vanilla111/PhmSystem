package cn.edu.uestc.cac.simulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import cn.edu.uestc.cac.simulator.common.Command;
import cn.edu.uestc.cac.simulator.common.CommandTypeEnum;
import cn.edu.uestc.cac.simulator.common.LocalCommand;
import cn.edu.uestc.cac.simulator.common.SshCommand;
import cn.edu.uestc.cac.simulator.config.SimulatorConfig;
import cn.edu.uestc.cac.simulator.constants.CommandConstants;
import cn.edu.uestc.cac.simulator.runner.CommandExecutor;
import cn.edu.uestc.cac.utils.Stopper;

/**
 * 主启动类
 *
 * @author maomao
 * @date 2020-12-27
 */
@ComponentScan("cn.edu.uestc.cac.simulator")
public class SimulatorApplication {

    private static final Logger logger = LoggerFactory.getLogger(SimulatorApplication.class);

    @Autowired
    private SimulatorConfig simulatorConfig;

    public static void main(String[] args) {
        Thread.currentThread().setName("Simulator");
        new SpringApplicationBuilder(SimulatorApplication.class).web(WebApplicationType.NONE).run(args);
    }

    /**
     * Java注解，用来修饰一个非静态的void()方法
     * Spring执行顺序：Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)(仅执行一次)
     */
    @PostConstruct
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Command cpuCommand = null;
        Command memCommand = null;
        Command diskCommand = null;
        switch (this.simulatorConfig.getMode().trim().toUpperCase()) {
            case CommandConstants.LOCAL_MODE: {
                // 本地执行
                logger.info("localhost mode");
                cpuCommand = new LocalCommand(CommandTypeEnum.CPU);
                memCommand = new LocalCommand(CommandTypeEnum.MEM);
                diskCommand = new LocalCommand(CommandTypeEnum.DISK);
                break;
            }
            case CommandConstants.SSH_MODE: {
                // 远程登陆到目标机器上执行
                logger.info("ssh " + this.simulatorConfig.getUsername() + "@" + this.simulatorConfig.getSshHost()
                        + ":" + this.simulatorConfig.getSshHostPort());
                cpuCommand = new SshCommand(CommandTypeEnum.CPU, simulatorConfig);
                memCommand = new SshCommand(CommandTypeEnum.MEM, simulatorConfig);
                diskCommand = new SshCommand(CommandTypeEnum.DISK, simulatorConfig);
                break;
            }
            case CommandConstants.HTTP_MODE: {
                // 使用HTTP请求执行命令
                logger.info("http mode");
                break;
            }
            default: {
                logger.error("配置错误");
            }
        }
        CommandExecutor cpuExecutor = new CommandExecutor(cpuCommand);
        CommandExecutor memExecutor = new CommandExecutor(memCommand);
        CommandExecutor diskExecutor = new CommandExecutor(diskCommand);
        executorService.execute(cpuExecutor);
        executorService.execute(memExecutor);
        executorService.execute(diskExecutor);
        executorService.shutdown();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> close("shutdown hook")));
    }

    public void close(String cause) {
        if (Stopper.isStopped()) return ;
        Stopper.stop();
        logger.info("Simulator shutdown, cause: {}", cause);
    }

}
