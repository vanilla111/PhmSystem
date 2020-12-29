package cn.edu.uestc.cac.simulator.base;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Entity父类
 *
 * @author maomao
 * @date 2020-12-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SuperEntity<T extends Model> extends Model {
    /**
     * 唯一id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 逻辑删除，0表示未删除，1表示删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}
