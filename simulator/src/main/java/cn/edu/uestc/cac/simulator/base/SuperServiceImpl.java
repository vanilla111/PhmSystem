package cn.edu.uestc.cac.simulator.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * SuperService 实现类（泛型 M 是 mapper(dao) 对象，T 是实体）
 *
 * @author maomao
 * @date 2020-12-27
 */
public class SuperServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements SuperService<T> {
}
