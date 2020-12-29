package cn.edu.uestc.cac.simulator.service;

import cn.edu.uestc.cac.simulator.base.SuperService;
import cn.edu.uestc.cac.simulator.entity.Command;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 命令表 Service 层
 *
 * @author maomao
 * @date 2020-12-27
 */
public interface CommandService extends SuperService<Command> {

    /**
     * 随机生成操作 CPU 的命令
     */
    void randomGenerateCPUCommand();

    /**
     * 随机生成操作磁盘的命令
     */
    void randomGenerateDiskCommand();

    /**
     * 随机生成操作内存的命令
     */
    void randomGenerateMemCommand();

}
