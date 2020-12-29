package cn.edu.uestc.cac.simulator.service;

import cn.edu.uestc.cac.simulator.constants.CommandConstants;
import cn.edu.uestc.cac.simulator.factory.CommandFactory;
import cn.edu.uestc.cac.simulator.factory.SendCommandMethod;
import cn.edu.uestc.cac.simulator.utils.RandomUtils;
import org.springframework.context.annotation.Bean;

/**
 * 命令表 Service 层实现类
 *
 * @author maomao
 * @date 2020-12-27
 */

public class RandomGenerateCommand {
    /**
     * 随机生成CPU命令，并将执行结果插入数据库
     *
     * @param sendCommandMethod 发送命令的方式
     */
    public void randomGenerateCPUCommand(String sendCommandMethod) {
        StringBuilder cpuCommand = new StringBuilder(CommandConstants.CREATE_CPU_LOAD);

        // 1.随机生成CPU参数
        String[] cpuArgs = CommandConstants.CPU_ARGS_ARRAY;
        int idx = RandomUtils.randomGenerateInteger(0, cpuArgs.length - 1);
        cpuCommand.append(cpuArgs[idx]);

        // 2.根据参数随机生成对应的值
        if (CommandConstants.CPU_PERCENT.equals(cpuArgs[idx])) {
            // 随机生成 CPU 负载百分比
            int val = RandomUtils.randomGenerateInteger(CommandConstants.CPU_LIST_MIN_VALUE,
                    CommandConstants.CPU_PERCENT_MAX_VALUE);
            cpuCommand.append(val);
        }

        // 3.随机生成运行时长参数
        cpuCommand.append(randomGenerateTimeout());

        // 4.发送命令并将返回值存入数据库
        SendCommandMethod method = CommandFactory.getSendCommandMethod(sendCommandMethod);
        if (method != null) {
            method.sendCommand(cpuCommand.toString());
        }
    }

    /**
     * 随机生成磁盘命令，并将执行结果插入数据库
     *
     * @param sendCommandMethod 发送命令的方式
     */
    public void randomGenerateDiskCommand(String sendCommandMethod) {
        StringBuilder diskCommand = new StringBuilder(CommandConstants.CREATE_DISK_BURN);

        // 1.随机生成磁盘参数
        String[] diskArgs = CommandConstants.DISK_ARGS_ARRAY;
        int idx = RandomUtils.randomGenerateInteger(0, diskArgs.length - 1);
        diskCommand.append(diskArgs[idx]);

        // 2.根据参数随机生成对应的值，读写没有值

        // 3.随机生成运行时长参数
        diskCommand.append(randomGenerateTimeout());

        // 4.发送命令并将返回值存入数据库
        SendCommandMethod method = CommandFactory.getSendCommandMethod(sendCommandMethod);
        if (method != null) {
            method.sendCommand(diskCommand.toString());
        }
    }

    /**
     * 随机生成内存命令，并将执行结果插入数据库
     *
     * @param sendCommandMethod 发送命令的方式
     */
    public void randomGenerateMemCommand(String sendCommandMethod) {
        StringBuilder memCommand = new StringBuilder(CommandConstants.CREATE_MEM_LOAD);

        // 1.随机生成内存参数
        String[] memArgs = CommandConstants.MEM_ARGS_ARRAY;
        int idx = RandomUtils.randomGenerateInteger(0, memArgs.length - 1);
        memCommand.append(memArgs[idx]);

        // 2.根据参数随机生成对应的值
        if (CommandConstants.MEM_PERCENT.equals(memArgs[idx])) {
            int val = RandomUtils.randomGenerateInteger(CommandConstants.MEM_PERCENT_MIN_VALUE,
                    CommandConstants.MEM_PERCENT_MAX_VALUE);
            memCommand.append(val);
        }

        // 3.随机生成运行时长参数
        memCommand.append(randomGenerateTimeout());

        // 4.发送命令并将返回值存入数据库
        SendCommandMethod method = CommandFactory.getSendCommandMethod(sendCommandMethod);
        if (method != null) {
            method.sendCommand(memCommand.toString());
        }
    }

    /**
     * 随机生成运行时长参数，通用参数，且每次都必须生成
     *
     * @return 生成的运行时长参数
     */
    private String randomGenerateTimeout() {
        int val = RandomUtils.randomGenerateInteger(CommandConstants.TIMEOUT_MIN_VALUE,
                CommandConstants.TIMEOUT_MAX_VALUE);
        return CommandConstants.TIMEOUT + val;
    }
}
