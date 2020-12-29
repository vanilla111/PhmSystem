package cn.edu.uestc.cac.simulator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 主启动类
 *
 * @author maomao
 * @date 2020-12-27
 */

@SpringBootApplication
@MapperScan("cn.edu.uestc.cac.simulator.mapper")
@ComponentScan(basePackages = {
        "cn.edu.uestc.cac.simulator.config",
        "cn.edu.uestc.cac.simulator.service"
})
public class SimulatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimulatorApplication.class, args);
    }
}
