package cn.edu.uestc.cac.simulator.utils;

import cn.edu.uestc.cac.simulator.common.Command;
import cn.edu.uestc.cac.simulator.constants.CommandConstants;

/**
 * 随机生成命令
 *
 * @author maomao
 * @date 2020-12-27
 */

public class RandomCommandGenerator {

    private RandomCommandGenerator() {}

    public static void generate(Command command) {
        switch (command.getCommandType()) {
            case CPU: generateCpuCommand(command); break;
            case MEM: generateMemCommand(command); break;
            case DISK: generateDiskCommand(command); break;
            case MEM_LEAK: generateMemLeakCommand(command); break;
        }
    }

    public static void generateMemLeakCommand(Command command) {
        command.setTimeout(120);
        command.setCommand(
                command.getConfig().getErrorGeneratorCommandPath() + " >> /etc/collector/error_generator.log 2>&1 &");
    }

    /**
     * 随机生成CPU命令
     *
     */
    public static void generateCpuCommand(Command command) {
        StringBuilder cpuCommand = new StringBuilder();

        cpuCommand.append(CommandConstants.CREATE_CPU_LOAD);
        // 随机生成 CPU 负载百分比
        int cpuPercent = RandomUtils.randomGenerateInteger(CommandConstants.CPU_PERCENT_MIN_VALUE,
                CommandConstants.CPU_PERCENT_MAX_VALUE);
        cpuCommand.append(CommandConstants.CPU_PERCENT);
        cpuCommand.append(cpuPercent);

        // 随机生成运行时长参数
        int timeout = randomTimeout();
        cpuCommand.append(CommandConstants.TIMEOUT);
        cpuCommand.append(timeout);

        command.setTimeout(timeout);
        command.setCommand(cpuCommand.toString());
    }

    /**
     * 随机生成磁盘命令
     *
     */
    public static void generateDiskCommand(Command command) {
        StringBuilder diskCommand = new StringBuilder();

        diskCommand.append(CommandConstants.CREATE_DISK_BURN);
        // 随机生成磁盘参数
        String[] diskArgs = CommandConstants.DISK_ARGS_ARRAY;
        int idx = RandomUtils.randomGenerateInteger(0, diskArgs.length - 1);
        diskCommand.append(diskArgs[idx]);

        // 随机生成运行时长参数
        int timeout = randomTimeout();
        diskCommand.append(CommandConstants.TIMEOUT);
        diskCommand.append(timeout);

        command.setTimeout(timeout);
        command.setCommand(diskCommand.toString());
    }

    /**
     * 随机生成内存命令
     *
     */
    public static void generateMemCommand(Command command) {
        StringBuilder memCommand = new StringBuilder();

        memCommand.append(CommandConstants.CREATE_MEM_LOAD);
        int memPercent = RandomUtils.randomGenerateInteger(CommandConstants.MEM_PERCENT_MIN_VALUE,
                CommandConstants.MEM_PERCENT_MAX_VALUE);
        memCommand.append(CommandConstants.MEM_PERCENT);
        memCommand.append(memPercent);

        // 必须使用 ram 模式
        memCommand.append(CommandConstants.MODE);
        memCommand.append(CommandConstants.RAM);

        // 随机生成运行时长参数
        int timeout = randomTimeout();
        memCommand.append(CommandConstants.TIMEOUT);
        memCommand.append(timeout);

        command.setTimeout(timeout);
        command.setCommand(memCommand.toString());
    }

    /**
     * 随机生成运行时长参数，通用参数，且每次都必须生成
     *
     * @return 生成的运行时长参数
     */
    private static int randomTimeout() {
        return RandomUtils.randomGenerateInteger(CommandConstants.TIMEOUT_MIN_VALUE,
                CommandConstants.TIMEOUT_MAX_VALUE);
    }
}
