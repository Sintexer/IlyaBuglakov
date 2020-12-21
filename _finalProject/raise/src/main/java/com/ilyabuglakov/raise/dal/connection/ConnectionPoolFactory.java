package com.ilyabuglakov.raise.dal.connection;

import lombok.Getter;

public class ConnectionPoolFactory {

    private ConnectionPool connectionPool;

    private ConnectionPoolFactory(){
        connectionPool = new StandardConnectionPool();
    }

    private static class InstanceHolder{
        public static ConnectionPoolFactory INSTANCE = new ConnectionPoolFactory();
    }

    public static ConnectionPoolFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static ConnectionPool getConnectionPool(){
        return getInstance().connectionPool;
    }

}
