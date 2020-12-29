package cn.edu.uestc.cac.dao.utils;

import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * @author wang
 */
public class TimeMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
