package cn.edu.uestc.cac.simulator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author wang
 */
@Data
@PropertySource(value = "classpath:simulator.properties")
@Component
public class SimulatorConfig {

    @Value("${simulator.run.mode:localhost}")
    private String mode;

    @Value("${simulator.chaosblade.server.host:127.0.0.1}")
    private String chaosBladeServerHost;

    @Value("${simulator.chaosblade.server.port:80}")
    private int chaosBladeServerPort;

    @Value("${simulator.chaosblade.ssh.host:127.0.0.1}")
    private String sshHost;

    @Value("${simulator.chaosblade.ssh.port:22}")
    private int sshHostPort;

    @Value("${simulator.chaosblade.ssh.username:root}")
    private String username;

    @Value("${simulator.chaosblade.ssh.password:111111}")
    private String password;
}
