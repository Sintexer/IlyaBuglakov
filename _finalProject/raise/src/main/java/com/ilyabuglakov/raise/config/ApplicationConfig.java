package com.ilyabuglakov.raise.config;

import com.ilyabuglakov.raise.config.exception.PoolConfigurationException;
import com.ilyabuglakov.raise.dal.connection.pool.ConnectionPoolFactory;
import com.ilyabuglakov.raise.service.property.ApplicationProperties;
import com.ilyabuglakov.raise.service.property.PropertyParser;
import com.ilyabuglakov.raise.service.property.exception.PropertyFileException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;

@Log4j2
public class ApplicationConfig {

    @Getter
    private PropertyParser linksProperties;

    private ApplicationConfig() throws PropertyFileException {
        linksProperties = new PropertyParser("links.properties");
    }

    private static class InstanceHolder {
        public static ApplicationConfig INSTANCE;

        static {
            try {
                INSTANCE = new ApplicationConfig();
            } catch (PropertyFileException e) {
                log.error("Can't init links.properties parser");
            }
        }
    }

    public static ApplicationConfig getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static void initConnectionPool() throws PoolConfigurationException {
        try {
            String dbDriver = ApplicationProperties.getProperty("db.driver.name");
            String dbUrl = ApplicationProperties.getProperty("db.url");
            String dbUser = ApplicationProperties.getProperty("db.user.name");
            String dbPassword = ApplicationProperties.getProperty("db.user.password");
            String dbInitPoolSizeProperty = ApplicationProperties.getProperty("connectionPool.size.init");
            String dbMaxPoolSizeProperty = ApplicationProperties.getProperty("connectionPool.size.max");
            String dbCheckConnectionTimeoutProperty = ApplicationProperties.getProperty("connectionPool.checkConnection.timeout");

            if (ObjectUtils.anyNull(dbDriver, dbUrl, dbUser, dbPassword))
                throw new PoolConfigurationException("Any of required db init fields is null");

            int dbInitPoolSize;
            int dbMaxPoolSize;
            int dbCheckConnectionTimeout;

            if (dbInitPoolSizeProperty == null)
                dbInitPoolSize = 10;
            else
                dbInitPoolSize = Integer.parseInt(dbInitPoolSizeProperty);

            if (dbMaxPoolSizeProperty == null)
                dbMaxPoolSize = dbInitPoolSize;
            else {
                dbMaxPoolSize = Integer.parseInt(dbMaxPoolSizeProperty);
                if (dbMaxPoolSize < dbInitPoolSize)
                    throw new PoolConfigurationException("Max pool size is lesser, than init size");
            }

            if (dbCheckConnectionTimeoutProperty == null) {
                dbCheckConnectionTimeout = 0;
            } else
                dbCheckConnectionTimeout = Integer.parseInt(dbCheckConnectionTimeoutProperty);

            ConnectionPoolFactory.getConnectionPool().init(
                    dbDriver,
                    dbUrl,
                    dbUser,
                    dbPassword,
                    dbInitPoolSize,
                    dbMaxPoolSize,
                    dbCheckConnectionTimeout
            );
        } catch (NumberFormatException e) {
            throw new PoolConfigurationException("Wrong pool properties format");
        }
    }

}
