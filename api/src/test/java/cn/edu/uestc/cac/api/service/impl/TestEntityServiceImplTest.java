package cn.edu.uestc.cac.api.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.edu.uestc.cac.dao.entity.TestEntity;

/**
 * @author wang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEntityServiceImplTest {

    @Autowired
    TestEntityServiceImpl service;

    @Test
    public void saveTestEntity() {
        TestEntity entity = new TestEntity();
        entity.setData("first");
        boolean res = service.saveTestEntity(entity);
        Assert.assertTrue(res);
    }

    @Test
    public void getTestEntity() {
        TestEntity entity = service.getTestEntity(2);
        Assert.assertEquals("second", entity.getData());
    }

    @Test
    public void updateTestEntity() {
        Assert.assertTrue(service.updateTestEntity("second", 2));
    }

    @Test
    public void deleteTestEntity() {
        Assert.assertTrue(service.deleteTestEntity(3));
    }
}