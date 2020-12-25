package cn.edu.uestc.cac.dao.datasource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

/**
 * @author wang
 */
public class ConnectionFactory extends SpringConnectionFactory {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    private static class ConnectionFactoryHolder {
        private static final ConnectionFactory connectionFactory = new ConnectionFactory();
    }

    public static ConnectionFactory getInstance() {
        return ConnectionFactoryHolder.connectionFactory;
    }

    private ConnectionFactory() {
        try {
            dataSource = buildDataSource();
            sqlSessionFactory = getSqlSessionFactory();
            sqlSessionTemplate = getSqlSessionTemplate();
        } catch (Exception e) {
            logger.error("Initializing ConnectionFactory error", e);
            throw new RuntimeException(e);
        }
    }

    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionTemplate sqlSessionTemplate;

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    private DataSource buildDataSource() {
        return dataSource();
    }

    private SqlSessionFactory getSqlSessionFactory() throws Exception {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, getDataSource());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setEnvironment(environment);
        configuration.setLazyLoadingEnabled(true);
        configuration.addMappers("cn.edu.uestc.cac.dao.mapper");
        configuration.addInterceptor(new PaginationInterceptor());

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setDataSource(getDataSource());

        sqlSessionFactoryBean.setTypeEnumsPackage("cn.edu.uestc.cac.*.enums");
        sqlSessionFactory = sqlSessionFactoryBean.getObject();

        return sqlSessionFactory;
    }

    private SqlSessionTemplate getSqlSessionTemplate() {
        sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    public SqlSession getSqlSession() {
        return sqlSessionTemplate;
    }

    public <T> T getMapper(Class<T> type) {
        try {
            return getSqlSession().getMapper(type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("get mapper failed");
        }
    }

}
