package cn.edu.uestc.cac.simulator.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * mapper 父类，注意这个类不要让 mybatis-plus 扫描到
 *
 * @author maomao
 * @date 2020-12-27
 */
public interface SuperMapper<T> extends BaseMapper<T> {
    // 这里可以放一些公共的方法
}
