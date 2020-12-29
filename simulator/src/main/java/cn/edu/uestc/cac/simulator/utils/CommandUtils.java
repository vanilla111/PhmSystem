package cn.edu.uestc.cac.simulator.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.*;

/**
 * 远程连接主机执行命令工具类
 *
 * @author maomao
 * @date 2020-12-27
 */
@Slf4j
public class CommandUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 登录远程主机
     *
     * @param ip       ip地址
     * @param user     用户名
     * @param password 密码
     * @return 登录成功返回连接，否则返回null
     */
    public static Connection login(String ip, String user, String password) {
        Connection connection = null;

        try {
            connection = new Connection(ip);
            connection.connect();
            // 使用用户名和密码进行认证
            if (connection.authenticateWithPassword(user, password)) {
                log.info("登录成功");
                return connection;
            }

        } catch (IOException e) {
            log.error("登录失败");
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 在远程主机上执行shell脚本或命令
     *
     * @param connection 与远程主机的连接
     * @param command    需要执行的脚本或命令
     * @return 命令执行返回的结果，若执行失败返回空字符串
     */
    public static String execute(Connection connection, String command) {
        String result = "";

        try {
            if (connection != null) {
                // 打开一个会话
                Session session = connection.openSession();
                // 执行命令
                session.execCommand(command);
                // 解析执行结果
                result = processStdout(session.getStdout());

                System.out.println("result = " + result);

                if (StringUtils.isBlank(result)) {
                    log.info("标准输出为空：connection = " + connection + ", command = " + command);
                    result = processStdout(session.getStderr());
                } else {
                    log.info("命令执行成功：connection = " + connection + ", command = " + command);
                }

                connection.close();
                session.close();
            }
        } catch (IOException e) {
            log.error("命令执行失败：connection = " + connection + ", command = " + command + ", " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 解析脚本或命令执行的返回结果
     *
     * @param inputStream 输入流对象
     * @return 解析的文本结果
     */
    private static String processStdout(InputStream inputStream) {
        InputStream stdout = new StreamGobbler(inputStream);
        StringBuffer buffer = new StringBuffer();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout, DEFAULT_CHARSET));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error("解析脚本出错" + e.getMessage());
            e.printStackTrace();
        }

        return buffer.toString();
    }
}
