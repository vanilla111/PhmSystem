package cn.edu.uestc.cac.dao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.uestc.cac.common.Constants;

/**
 * @author wang
 */
public class PropertyUtils {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

    private static final Properties PROPERTIES = new Properties();

    private static final PropertyUtils PROPERTY_UTILS = new PropertyUtils();

    private PropertyUtils() {
        init();
    }

    private void init() {
        String[] propertyFiles = new String[]{Constants.DATASOURCE_PROPERTIES};
        for (String propertyFile : propertyFiles) {
            try (InputStream fis = PropertyUtils.class.getResourceAsStream(propertyFile)) {
                PROPERTIES.load(fis);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                System.exit(1);
            }
        }
    }

    public static String getString(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getString(String key, String defaultValue) {
        String value = PROPERTIES.getProperty(key);
        return key == null ? defaultValue : value;
    }

    public static int getInt(String key) {
        return getInt(key, -1);
    }

    public static int getInt(String key, int defaultValue) {
        String value = getString(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return defaultValue;
    }

    public static boolean getBoolean(String key) {
        String value = PROPERTIES.getProperty(key.trim());
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return false;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = PROPERTIES.getProperty(key.trim());
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    public static long getLong(String key, long defaultValue) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return defaultValue;
    }
}
