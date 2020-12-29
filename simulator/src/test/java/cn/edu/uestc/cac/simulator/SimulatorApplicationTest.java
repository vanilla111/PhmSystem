package cn.edu.uestc.cac.simulator;

import ch.ethz.ssh2.Connection;
import cn.edu.uestc.cac.simulator.constants.CommandConstants;
import cn.edu.uestc.cac.simulator.service.RandomGenerateCommand;
import cn.edu.uestc.cac.simulator.utils.CommandUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Test
    public void randomGenerateCPUCommandTest() {
//        new RandomGenerateCommand().randomGenerateCPUCommand(CommandConstants.SSH_SEND_METHOD);
        new RandomGenerateCommand().randomGenerateCPUCommand(CommandConstants.HTTP_SEND_METHOD);
    }

    @Test
    public void randomGenerateDiskCommandTest() {
//        new RandomGenerateCommand().randomGenerateDiskCommand(CommandConstants.SSH_SEND_METHOD);
        new RandomGenerateCommand().randomGenerateDiskCommand(CommandConstants.HTTP_SEND_METHOD);
    }

    @Test
    public void randomGenerateMemCommandTest() {
//        new RandomGenerateCommand().randomGenerateMemCommand(CommandConstants.SSH_SEND_METHOD);
        new RandomGenerateCommand().randomGenerateMemCommand(CommandConstants.HTTP_SEND_METHOD);
    }

}
