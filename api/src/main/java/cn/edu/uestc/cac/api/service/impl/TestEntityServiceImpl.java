package cn.edu.uestc.cac.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.uestc.cac.api.service.TestEntityService;
import cn.edu.uestc.cac.dao.entity.TestEntity;
import cn.edu.uestc.cac.dao.mapper.TestEntityMapper;

/**
 * @author wang
 */
@Service
public class TestEntityServiceImpl implements TestEntityService {
    @Autowired
    private TestEntityMapper testEntityMapper;


    @Override
    public boolean saveTestEntity(TestEntity entity) {
        int res = testEntityMapper.insert(entity);
        return res > 0;
    }

    @Override
    public TestEntity getTestEntity(int id) {
        return testEntityMapper.selectWithId(id);
    }

    @Override
    public boolean updateTestEntity(String data, int id) {
        testEntityMapper.changeDataById(data, id);
        return true;
    }

    @Override
    public boolean deleteTestEntity(int id) {
        return testEntityMapper.deleteWithId(id) > 0;
    }
}
