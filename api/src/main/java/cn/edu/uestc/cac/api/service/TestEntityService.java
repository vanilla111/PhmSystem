package cn.edu.uestc.cac.api.service;

import cn.edu.uestc.cac.dao.entity.TestEntity;

/**
 * @author wang
 */
public interface TestEntityService {

    boolean saveTestEntity(TestEntity entity);

    TestEntity getTestEntity(int id);

    boolean updateTestEntity(String data, int id);

    boolean deleteTestEntity(int id);

}
