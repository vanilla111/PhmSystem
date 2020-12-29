package cn.edu.uestc.cac.simulator.constants;

/**
 * 命令常量类
 *
 * @author maomao
 * @date 2020-12-27
 */
public class CommandConstants {
    /*--------------------------全局命令常量-----------------------------------*/

    /**
     * 执行命令的方式：本地执行
     */
    public static final String LOCAL_SEND_METHOD = "LOCAL";

    /**
     * 执行命令的方式：SSH
     */
    public static final String SSH_SEND_METHOD = "SSH";

    /**
     * 执行命令的方式：HTTP
     */
    public static final String HTTP_SEND_METHOD = "HTTP";

    /**
     * blade命令
     */
    public static final String BLADE = "blade ";

    /**
     * curl 命令
     */
    public static final String CURL = "curl ";

    /**
     * 英文双引号
     */
    public static final String DOUBLE_QUOTES = "\"";

    /**
     * curl 用于替换空格
     */
    public static final String SPACE_REPLACER = "%20";

    /*--------------------------CPU相关命令和参数常量-----------------------------------*/

    /**
     * CPU 相关命令
     */
    public static final String CREATE_CPU_LOAD = "create cpu load";

    /**
     * 设定运行时长，单位是秒，通用参数
     */
    public static final String TIMEOUT = " --timeout ";

    /**
     * 运行时长的最小值，单位是秒
     */
    public static final int TIMEOUT_MIN_VALUE = 10;

    /**
     * 运行时长的最大值，单位是秒
     */
    public static final int TIMEOUT_MAX_VALUE = 120;

    /**
     * 空参，表示命令没有参数
     */
    public static final String EMPTY_ARGS = "";

    /**
     * 指定 CPU 满载的个数
     */
    public static final String CPU_COUNT = " --cpu-count ";

    /**
     * CPU 满载的个数的最小值
     */
    public static final int CPU_COUNT_MIN_VALUE = 1;

    /**
     * CPU 满载的个数的最大值
     */
    public static final int CPU_COUNT_MAX_VALUE = 2;

    /**
     * 指定 CPU 满载的具体核，核索引从 0 开始 (0-3 or 1,3)
     */
    public static final String CPU_LIST = " --cpu-list ";

    /**
     * CPU 满载的具体核的最小值
     */
    public static final int CPU_LIST_MIN_VALUE = 0;

    /**
     * CPU 满载的具体核的最大值
     */
    public static final int CPU_LIST_MAX_VALUE = 1;

    /**
     * 英文逗号
     */
    public static final String COMMA = ",";

    /**
     * 指定 CPU 负载百分比，取值在 0-100
     */
    public static final String CPU_PERCENT = " --cpu-percent ";

    /**
     * CPU 负载百分比的最小值
     */
    public static final int CPU_PERCENT_MIN_VALUE = 0;

    /**
     * CPU 负载百分比的最大值
     */
    public static final int CPU_PERCENT_MAX_VALUE = 100;

    /**
     * CPU 相关命令的随机参数数组
     */
    public static final String[] CPU_ARGS_ARRAY = {CPU_PERCENT};

    /*--------------------------磁盘相关命令和参数常量-----------------------------------*/

    /**
     * 磁盘相关命令
     */
    public static final String CREATE_DISK_BURN = "create disk burn";


    /**
     * 指定提升磁盘 io 的目录，会作用于其所在的磁盘上，默认值是 /
     */
    public static final String PATH = " --path ";

    /**
     * 触发提升磁盘读 IO 负载，会创建 600M 的文件用于读，销毁实验会自动删除
     */
    public static final String READ = " --read ";

    /**
     * 触发提升磁盘写 IO 负载，会根据块大小的值来写入一个文件，销毁实验会自动删除
     */
    public static final String WRITE = " --write ";

    /**
     * 块大小, 单位是 M, 默认值是 10，一般不需要修改，除非想更大的提高 io 负载
     */
    public static final String SIZE = " --size ";

    /**
     * 磁盘相关命令的随机参数数组
     */
    public static final String[] DISK_ARGS_ARRAY = {READ, WRITE};

    /*--------------------------内存相关命令和参数常量-----------------------------------*/

    /**
     * 内存相关命令（SSH）
     */
    public static final String CREATE_MEM_LOAD = "create mem load";

    /**
     * 内存使用率，取值是 0 到 100 的整数
     */
    public static final String MEM_PERCENT = " --mem-percent ";

    /**
     * 内存使用率的最小值
     */
    public static final int MEM_PERCENT_MIN_VALUE = 0;

    /**
     * 内存使用率的最大值
     */
    public static final int MEM_PERCENT_MAX_VALUE = 100;

    /**
     * 内存占用模式，有 ram 和 cache 两种
     * ram 采用代码实现，可控制占用速率，优先推荐此模式；cache 是通过挂载 tmpfs 实现，默认值是 --mode cache
     */
    public static final String MODE = " --mode ";

    /**
     * 内存占用模式ram
     */
    public static final String RAM = "ram";

    /**
     * 内存占用模式cache
     */
    public static final String CACHE = "cache";

    /**
     * 保留内存的大小，单位是MB，如果 mem-percent 参数存在，则优先使用 mem-percent 参数
     */
    public static final String RESERVE = " --reserve ";

    /**
     * 内存占用速率，单位是 MB/S，仅在 --mode ram 时生效
     */
    public static final String RATE = " --rate ";

    /**
     * 内存相关命令的随机参数数组
     */
    public static final String[] MEM_ARGS_ARRAY = {MEM_PERCENT};

}
