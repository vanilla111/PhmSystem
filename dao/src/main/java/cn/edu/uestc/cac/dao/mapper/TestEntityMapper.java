package cn.edu.uestc.cac.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.edu.uestc.cac.dao.entity.TestEntity;

/**
 * @author wang
 */
@Mapper
public interface TestEntityMapper extends BaseMapper<TestEntity> {

    TestEntity selectWithId(@Param("id") int id);

    void changeDataById(@Param("newData") String newData, @Param("id") int id);

    int deleteWithId(@Param("id") int id);
}
