package com.ilyabuglakov.raise.dal.connection.pool;

import com.ilyabuglakov.raise.dal.connection.ConnectionProxy;

public interface ConnectionPool {

    void init(String driverClass, String url, String user, String password,
              int initPoolSize, int maxPoolSize, int checkConnectionTimeout);

    ConnectionProxy getConnection();

    boolean releaseConnection(ConnectionProxy connection);

    void destroy();

}
