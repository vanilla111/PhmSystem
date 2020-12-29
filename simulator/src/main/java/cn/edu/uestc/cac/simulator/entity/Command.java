package cn.edu.uestc.cac.simulator.entity;

import cn.edu.uestc.cac.simulator.base.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 命令表
 *
 * @author maomao
 * @date 2020-12-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("command")
public class Command extends SuperEntity<Command> {
    /**
     * 执行的命令
     */
    private String command;

    /**
     * 结果状态码
     */
    private String code;

    /**
     * 结果是否成功
     */
    private String success;

    /**
     * 结果具体内容
     */
    private String result;
}
