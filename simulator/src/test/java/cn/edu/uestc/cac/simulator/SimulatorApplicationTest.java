package cn.edu.uestc.cac.simulator;

import ch.ethz.ssh2.Connection;
import cn.edu.uestc.cac.simulator.constants.CommandConstants;
import cn.edu.uestc.cac.simulator.service.CommandService;
import cn.edu.uestc.cac.simulator.service.impl.CommandServiceImpl;
import cn.edu.uestc.cac.simulator.utils.CommandUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author maomao
 * @date 2020-12-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimulatorApplicationTest {
    @Test
    public void RemoteCommandUtilsTest() {
        Connection conn = CommandUtils.login("192.168.1.191", "root", "111111");
        String res = CommandUtils.execute(conn, "blade create cpu load --cpu-percent 100 --timeout 40");
        System.out.println("res = " + res);
    }

    @Autowired
    private CommandService commandService;

    @Test
    public void randomGenerateCPUCommandTest() {
        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);
            commandService.randomGenerateCPUCommand();
            try {
                Thread.sleep(CommandConstants.TIMEOUT_MAX_VALUE * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void randomGenerateDiskCommandTest() {
        commandService.randomGenerateDiskCommand();
    }

    @Test
    public void randomGenerateMemCommandTest() {
        commandService.randomGenerateMemCommand();
    }
}
