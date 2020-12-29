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

import cn.edu.uestc.cac.simulator.config.SimulatorConfig;

/**
 * 主启动类
 *
 * @author maomao
 * @date 2020-12-27
 */
@ComponentScan("cn.edu.uestc.cac")
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
    public void run() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        switch (this.simulatorConfig.getMode().trim().toLowerCase()) {
            case "localhost": {
                // 本地执行模拟
                logger.info("localhost mode");
                break;
            }
            case "ssh": {
                // 远程登陆到目标机器上执行
                logger.info("ssh " + this.simulatorConfig.getUsername() + "@" + this.simulatorConfig.getSshHost()
                        + ":" + this.simulatorConfig.getSshHostPort());
                break;
            }
            case "http": {
                // 使用HTTP请求执行命令
                logger.info("http mode");
                break;
            }
            default: {
                logger.error("配置错误");
            }
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> close("shutdown hook")));
    }

    public void close(String cause) {
        logger.info("Simulator shutdown, cause: {}", cause);
    }

}
