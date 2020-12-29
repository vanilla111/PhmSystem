package cn.edu.uestc.cac.simulator.service.impl;

import ch.ethz.ssh2.Connection;
import cn.edu.uestc.cac.simulator.base.SuperServiceImpl;
import cn.edu.uestc.cac.simulator.constants.CommandConstants;
import cn.edu.uestc.cac.simulator.constants.SystemConstants;
import cn.edu.uestc.cac.simulator.entity.Command;
import cn.edu.uestc.cac.simulator.mapper.CommandMapper;
import cn.edu.uestc.cac.simulator.service.CommandService;
import cn.edu.uestc.cac.simulator.utils.CommandUtils;
import cn.edu.uestc.cac.simulator.utils.JsonUtil;
import cn.edu.uestc.cac.simulator.utils.RandomUtils;
import org.springframework.stereotype.Service;

/**
 * 命令表 Service 层实现类
 *
 * @author maomao
 * @date 2020-12-27
 */
@Service
public class CommandServiceImpl extends SuperServiceImpl<CommandMapper, Command> implements CommandService {

    @Override
    public void randomGenerateCPUCommand() {
        StringBuilder cpuCommand = new StringBuilder(CommandConstants.BLADE_CREATE_CPU_LOAD);

        // 1.随机生成CPU参数
        String[] cpuArgs = CommandConstants.CPU_ARGS_ARRAY;
        int idx = RandomUtils.randomGenerateInteger(0, cpuArgs.length - 1);
        cpuCommand.append(cpuArgs[idx]);

        // 2.根据参数随机生成对应的值
        if (CommandConstants.CPU_COUNT.equals(cpuArgs[idx])) {
            // 随机生成 CPU 满载的个数
            int val = RandomUtils.randomGenerateInteger(CommandConstants.CPU_COUNT_MIN_VALUE,
                    CommandConstants.CPU_COUNT_MAX_VALUE);
            cpuCommand.append(val);

        } else if (CommandConstants.CPU_LIST.equals(cpuArgs[idx])) {
            // 随机生成 CPU 满载的具体核，首先生成具体核的个数，然后生成具体核的索引
            int val = RandomUtils.randomGenerateInteger(1, CommandConstants.CPU_LIST_MAX_VALUE
                    - CommandConstants.CPU_LIST_MIN_VALUE + 1);
            int[] nums = RandomUtils.randomGenerateDiffInteger(CommandConstants.CPU_LIST_MIN_VALUE,
                    CommandConstants.CPU_LIST_MAX_VALUE, val);

            for (int i = 0; i < nums.length; i++) {
                if (i != nums.length - 1) {
                    cpuCommand.append(nums[i]).append(CommandConstants.COMMA);
                } else {
                    cpuCommand.append(nums[i]);
                }
            }

        } else if (CommandConstants.CPU_PERCENT.equals(cpuArgs[idx])) {
            // 随机生成 CPU 负载百分比
            int val = RandomUtils.randomGenerateInteger(CommandConstants.CPU_LIST_MIN_VALUE,
                    CommandConstants.CPU_PERCENT_MAX_VALUE);
            cpuCommand.append(val);
        }

        // 3.随机生成运行时长参数
        cpuCommand.append(randomGenerateTimeout());

        // 4.发送命令并将返回值存入数据库
        sendCommandAndInsert(cpuCommand.toString());
    }

    @Override
    public void randomGenerateDiskCommand() {
        StringBuilder diskCommand = new StringBuilder(CommandConstants.BLADE_CREATE_DISK_BURN);

        // 1.随机生成磁盘参数
        String[] diskArgs = CommandConstants.DISK_ARGS_ARRAY;
        int idx = RandomUtils.randomGenerateInteger(0, diskArgs.length - 1);
        diskCommand.append(diskArgs[idx]);

        // 2.根据参数随机生成对应的值，读写没有值

        // 3.随机生成运行时长参数
        diskCommand.append(randomGenerateTimeout());

        // 4.发送命令并将返回值存入数据库
        sendCommandAndInsert(diskCommand.toString());
    }

    @Override
    public void randomGenerateMemCommand() {
        StringBuilder memCommand = new StringBuilder(CommandConstants.BLADE_CREATE_MEM_LOAD);

        // 1.随机生成内存参数
        String[] memArgs = CommandConstants.MEM_ARGS_ARRAY;
        int idx = RandomUtils.randomGenerateInteger(0, memArgs.length - 1);
        memCommand.append(memArgs[idx]);

        // 2.根据参数随机生成对应的值，读写没有值
        if (CommandConstants.MEM_PERCENT.equals(memArgs[idx])) {
            int val = RandomUtils.randomGenerateInteger(CommandConstants.MEM_PERCENT_MIN_VALUE,
                    CommandConstants.MEM_PERCENT_MAX_VALUE);
            memCommand.append(val);
        }

        // 3.随机生成运行时长参数
        memCommand.append(randomGenerateTimeout());

        // 4.发送命令并将返回值存入数据库
        sendCommandAndInsert(memCommand.toString());
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


    /**
     * 发送命令到远程，并组装返回的数据，存入数据库
     *
     * @param commandStr 命令字符串
     */
    private void sendCommandAndInsert(String commandStr) {
        // 获取连接并执行命令
        Connection connection = CommandUtils.login(SystemConstants.IP, SystemConstants.USER, SystemConstants.PASSWORD);
        String result = CommandUtils.execute(connection, commandStr);

        // 组装数据并存入数据库
        Command command = JsonUtil.toBean(result, Command.class);
        if (command != null) {
            command.setCommand(commandStr);
            command.insert();
        }
    }
}
