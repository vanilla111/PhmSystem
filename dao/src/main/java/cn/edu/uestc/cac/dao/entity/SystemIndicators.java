package cn.edu.uestc.cac.dao.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author wang
 */
@Data
@TableName(value = "sys_indicators")
public class SystemIndicators {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer version;

    private Integer uptime;

    private Integer sessions;

    private Integer processes;

    private Integer fileHandles;

    private Integer fileHandlesLimit;

    private String osKernel;

    private String osName;

    private String osArch;

    private String cpuName;

    private Integer cpuCores;

    private Integer cpuFreq;

    private Long ramTotal;

    private Long ramUsage;

    private Long swapTotal;

    private Long swapUsage;

    private Long diskTotal;

    private Long diskUsage;

    private Integer diskRwTime;

    private Integer connections;

    @TableField(value = "ipv4")
    private String ipv4;

    private Long rx;

    private Long tx;

    @TableField(value = "load_avg_1")
    private Float loadAvg1;

    @TableField(value = "load_avg_5")
    private Float loadAvg5;

    @TableField(value = "load_avg_15")
    private Float loadAvg15;

    private Float loadCpu;

    private Float loadIo;

    private Integer label;

    private Date createTime;
}
