package cn.edu.uestc.cac.simulator.constants;

/**
 * 系统常量
 *
 * @author maomao
 * @date 2020-12-27
 */
public class SystemConstants {
    /**
     * SSH 远程主机的IP地址
     */
    public static final String SSH_IP = "192.168.1.191";

    /**
     * SSH 连接远程主机的用户名
     */
    public static final String SSH_USER = "root";

    /**
     * SSH 连接远程主机的密码
     */
    public static final String SSH_PASSWORD = "111111";

    /**
     * HTTP 远程主机的IP地址
     */
    public static final String HTTP_IP = "192.168.1.191";

    /**
     * HTTP 远程主机的端口
     */
    public static final String HTTP_PORT = "8080";


    /**
     * HTTP 命令的前缀
     */
    public static final String HHTP_PREFIX = "http://" + HTTP_IP + ":" + HTTP_PORT + "/chaosblade?cmd=";
}
