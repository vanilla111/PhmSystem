package cn.edu.uestc.cac.dao.datasource;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

/**
 * @author wang
 */
public class ConnectionFactoryTest {


    @Test
    public void testConnection() throws Exception {
        Connection connection = ConnectionFactory.getInstance().getDataSource().getConnection();
        assertNotNull(connection);
    }
}