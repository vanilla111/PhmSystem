package cn.edu.uestc.cac.dao.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.annotation.DbType;

/**
 * @author wang
 */
public abstract class BaseDataSource {

    private static final Logger logger = LoggerFactory.getLogger(BaseDataSource.class);

    protected String user;

    protected String password;

    private String address;

    private String database;

    /**
     * other connection parameters for the data source
     */
    private String other;

    private String principal;

    public abstract String driverClassSelector();

    public abstract DbType dbTypeSelector();


    public static Logger getLogger() {
        return logger;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
