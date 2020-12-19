package com.ilyabuglakov.raise.dal.connection;

public interface ConnectionPool {

    void init(String driverClass, String url, String user, String password,
              int initPoolSize, int poolSize, int connectionTimeout);

    ConnectionProxy getConnection();

    boolean releaseConnection(ConnectionProxy connection);

    void destroy();

    String getUrl();

    String getUser();

    String getPassword();


}
